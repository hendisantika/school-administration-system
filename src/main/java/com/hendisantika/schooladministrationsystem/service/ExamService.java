package com.hendisantika.schooladministrationsystem.service;

import com.hendisantika.schooladministrationsystem.entity.Exam;
import com.hendisantika.schooladministrationsystem.repository.CourseRepository;
import com.hendisantika.schooladministrationsystem.repository.ExamRepository;
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
 * Date: 12/12/20
 * Time: 06.00
 */
@Service
public class ExamService {
    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    /**
     * Returns a List of Exams that written by student on the course.
     *
     * @param studentId Id of the Student.
     * @param courseId  Id of the Course.
     * @return List of the exams.
     */
    public List<Exam> findAllByStudent(Long studentId, Long courseId) {
        return examRepository.findAll()
                .stream()
                .filter(exam -> exam.getCourse().getId().equals(courseId))
                .filter(exam -> exam.getStudent().getId().equals(studentId))
                .collect(Collectors.toList());
    }

    /**
     * Returns an Exam object by id.
     *
     * @param id Id of the Exam.
     * @return an exam object.
     */
    public Exam findById(Long id) {
        return examRepository.getOne(id);
    }
}
