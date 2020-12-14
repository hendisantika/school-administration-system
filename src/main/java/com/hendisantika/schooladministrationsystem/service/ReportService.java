package com.hendisantika.schooladministrationsystem.service;

import com.hendisantika.schooladministrationsystem.dto.ReportDTO;
import com.hendisantika.schooladministrationsystem.dto.response.ReportResponseDTO;
import com.hendisantika.schooladministrationsystem.entity.Course;
import com.hendisantika.schooladministrationsystem.entity.Report;
import com.hendisantika.schooladministrationsystem.entity.user.group.Student;
import com.hendisantika.schooladministrationsystem.repository.CourseRepository;
import com.hendisantika.schooladministrationsystem.repository.ReportRepository;
import com.hendisantika.schooladministrationsystem.repository.user.StudentRepository;
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
 * Date: 15/12/20
 * Time: 05.48
 */
@Service
public class ReportService {
    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    /**
     * Returns a Summary of semester for student. One
     * Report object is a course-mark pair. Each report made by
     * Teacher.
     *
     * @param studentId Id of the student.
     * @param year      The year when semester was.
     * @param semester  Semester when report made.
     * @return List of the reports.
     */
    public List<Report> getSemesterResultByStudent(Long studentId, int year, int semester) {
        return reportRepository.findAll()
                .stream()
                .filter(report -> report.getStudent().getId().equals(studentId)
                        && report.getYear() == year
                        && report.getSemester() == semester)
                .collect(Collectors.toList());
    }

    /**
     * Returns a Report object by id.
     *
     * @param id Id of the Course.
     * @return a report object.
     */
    public Report findById(Long id) {
        return reportRepository.getOne(id);
    }

    /**
     * Creates a new report and save into the database.
     *
     * @param reportResponseDTO Submitted DTO from web application.
     * @return a new Report object.
     * @see Report
     */
    public Report create(ReportResponseDTO reportResponseDTO) {
        /* Finds student by id. */
        Student student = studentRepository.getOne(reportResponseDTO.getStudentId());
        /* Finds course by id. */
        Course course = courseRepository.getOne(reportResponseDTO.getCourseId());
        return reportRepository.save(new Report(
                student,
                reportResponseDTO.getYear(),
                reportResponseDTO.getSemester(),
                course,
                reportResponseDTO.getMark()
        ));
    }

    /**
     * Updates a report from database by id.
     *
     * @param id                Id of the report.
     * @param reportResponseDTO Submitted DTO from web application.
     * @return an updated report.
     * @see Report
     */
    public Report update(Long id, ReportResponseDTO reportResponseDTO) {
        /* Finds report by id. */
        Report report = reportRepository.getOne(id);
        /* Finds course by id. */
        Course course = courseRepository.getOne(reportResponseDTO.getCourseId());
        report.setMark(reportResponseDTO.getMark());
        report.setSemester(reportResponseDTO.getSemester());
        report.setYear(reportResponseDTO.getYear());
        report.setCourse(course);

        return reportRepository.save(report);
    }

    /**
     * Deletes a report from database by id.
     *
     * @param id Id of the report.
     */
    public void delete(Long id) {
        reportRepository.delete(reportRepository.getOne(id));
    }

    /**
     * Returns a form that contains a list of students
     * and mark field for each student.
     *
     * @param classroomId Id of the classroom.
     * @return A form table to create reports to all student in classroom.
     */
    public List<ReportDTO> makeReportFormToClassroom(Long classroomId) {
        List<Student> students = getStudentFromClassroom(classroomId);
        List<ReportDTO> reportDTOS = new ArrayList<>();
        for (Student student : students) {
            reportDTOS.add(new ReportDTO(student));
        }
        return reportDTOS;
    }

    /**
     * Creates a new reports and save into the database.
     *
     * @param reportResponseDTOS Submitted DTOs from web application.
     * @return a new Report objects.
     * @see Report
     */
    public List<Report> createReportsToClassroom(List<ReportResponseDTO> reportResponseDTOS) {
        List<Report> reports = new ArrayList<>();
        for (ReportResponseDTO reportResponseDTO : reportResponseDTOS) {
            /* Finds student by id. */
            Student student = studentRepository.getOne(reportResponseDTO.getStudentId());
            /* Finds course by id. */
            Course course = courseRepository.getOne(reportResponseDTO.getCourseId());
            Report report = new Report(
                    student,
                    reportResponseDTO.getYear(),
                    reportResponseDTO.getSemester(),
                    course,
                    reportResponseDTO.getMark()
            );
            reports.add(report);
            reportRepository.save(report);
        }
        return reports;
    }
}
