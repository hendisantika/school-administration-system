package com.hendisantika.schooladministrationsystem.service;

import com.hendisantika.schooladministrationsystem.entity.Classroom;
import com.hendisantika.schooladministrationsystem.entity.Report;
import com.hendisantika.schooladministrationsystem.entity.archive.Archive;
import com.hendisantika.schooladministrationsystem.entity.archive.ArchiveReport;
import com.hendisantika.schooladministrationsystem.entity.user.Authority;
import com.hendisantika.schooladministrationsystem.entity.user.group.Student;
import com.hendisantika.schooladministrationsystem.repository.AttendanceRepository;
import com.hendisantika.schooladministrationsystem.repository.ClassroomRepository;
import com.hendisantika.schooladministrationsystem.repository.ExamRepository;
import com.hendisantika.schooladministrationsystem.repository.RemarkRepository;
import com.hendisantika.schooladministrationsystem.repository.ReportRepository;
import com.hendisantika.schooladministrationsystem.repository.TimeTableRepository;
import com.hendisantika.schooladministrationsystem.repository.archive.ArchiveReportRepository;
import com.hendisantika.schooladministrationsystem.repository.archive.ArchiveRepository;
import com.hendisantika.schooladministrationsystem.repository.user.StudentRepository;
import com.hendisantika.schooladministrationsystem.repository.user.TeacherRepository;
import com.hendisantika.schooladministrationsystem.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * Project : school-administration-system
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/12/20
 * Time: 05.40
 */
@Service
public class AdminService {
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    private ClassroomRepository classroomRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private TimeTableRepository timeTableRepository;
    @Autowired
    private AttendanceRepository attendanceRepository;
    @Autowired
    private ArchiveReportRepository archiveReportRepository;
    @Autowired
    private ArchiveRepository archiveRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorityService authService;

    @Autowired
    private RemarkRepository remarkRepository;

    /**
     * When the school year ends, the admin able to update the
     * entire database for new year and clear out the previous data.
     * +!+ WARNING +!+ THIS FUNCTION DOESN'T CREATE ANY BACKUP. +!+ WARNING +!+
     */
    public void newYear() {
        attendanceRepository.deleteAll();
        timeTableRepository.deleteAll();
        reportRepository.deleteAll();
        examRepository.deleteAll();
        remarkRepository.deleteAll();
        for (Classroom classroom : classroomRepository.findAll()) {
            int newYear = classroom.getYear() + 1;
            classroom.setYear(newYear);
            classroomRepository.save(classroom);
        }
    }

    /**
     * Creates an archive file, that contains all student reports.
     */
    public String createArchive() {
        for (Student student : studentRepository.findAll()) {
            saveReportsByStudent(student.getId(), new Archive(
                    student.getStudent().getUsername(),
                    student.getStudent().getFullName(),
                    student.getDateOfBirth()
            ));
        }
        return "Archived";
    }

    /**
     * Returns a List of Archive Report.
     *
     * @return a list of archive report.
     */
    public List<Archive> getArchive() {
        return archiveRepository.findAll();
    }

    /**
     * Returns an Archive  by student id.
     *
     * @param id Id of the archive;
     * @return a list of archive report.
     */
    public Archive getArchiveById(Long id) {
        return archiveRepository.getOne(id);
    }

    /**
     * If, the class finished the school, this function delete the class
     * and also all student from the class.
     *
     * @param classroom_id Id of the classroom.
     */
    public void finished(Long classroom_id) {
        List<Student> students = getStudentsFromClassroom(classroom_id);
        for (Student student : students) {
            studentRepository.delete(student);
            userRepository.delete(student.getStudent());
            deleteClassroomById(classroom_id);
        }
        classroomRepository.delete(classroomRepository.getOne(classroom_id));
    }

    /**
     * This function finds all report by student and returns with a list of
     * ArchiveReport. !IMPORTANT! The collected reports are from 2nd Semester.
     *
     * @param student_id Id of the student.
     * @return a List of ArchiveReport.
     */
    private List<ArchiveReport> saveReportsByStudent(Long student_id, Archive archive) {
        List<ArchiveReport> result = new ArrayList<>();
        for (Report report : reportRepository.findAll()) {
            if (report.getStudent().getId().equals(student_id) && report.getSemester() == 2) {
                ArchiveReport archiveReport = new ArchiveReport(
                        report.getCourse().getTitle(),
                        report.getYear(),
                        report.getMark(),
                        archiveRepository.save(archive)
                );
                for (ArchiveReport archiver : archiveReportRepository.findAll()) {
                    if (!archiver.equals(archiveReport)) {
                        result.add(archiveReport);
                        archiveReportRepository.save(archiveReport);
                    }
                }
            }
        }
        return result;
    }

    /**
     * Returns a List of Students, who are in the class.
     *
     * @param id Id of the classroom.
     * @return List of students.
     */
    private List<Student> getStudentsFromClassroom(Long id) {
        return studentRepository
                .findAll()
                .stream()
                .filter(student -> student.getClassroom().getId().equals(id))
                .collect(Collectors.toList());
    }

    /**
     * Deletes a classroom from database by id.
     *
     * @param id Id of the classroom.
     */
    private void deleteClassroomById(Long id) {
        Classroom classroom = classroomRepository.getOne(id);
        List<Authority> authorities = authService.findByName("ROLE_TEACHER");
        classroom.getHeadTeacher().getTeacher().setAuthorities(authorities);
        classroomRepository.delete(classroomRepository.getOne(id));
    }

}
