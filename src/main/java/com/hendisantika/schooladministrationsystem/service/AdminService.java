package com.hendisantika.schooladministrationsystem.service;

import com.hendisantika.schooladministrationsystem.entity.Classroom;
import com.hendisantika.schooladministrationsystem.entity.archive.Archive;
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

import java.util.List;

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
}
