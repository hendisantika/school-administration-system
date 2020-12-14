package com.hendisantika.schooladministrationsystem.service;

import com.hendisantika.schooladministrationsystem.entity.Report;
import com.hendisantika.schooladministrationsystem.repository.CourseRepository;
import com.hendisantika.schooladministrationsystem.repository.ReportRepository;
import com.hendisantika.schooladministrationsystem.repository.user.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
