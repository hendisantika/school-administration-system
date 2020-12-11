package com.hendisantika.schooladministrationsystem.service;

import com.hendisantika.schooladministrationsystem.dto.response.ExamResponseDTO;
import com.hendisantika.schooladministrationsystem.entity.Course;
import com.hendisantika.schooladministrationsystem.entity.Exam;
import com.hendisantika.schooladministrationsystem.entity.ExamType;
import com.hendisantika.schooladministrationsystem.entity.user.group.Student;
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

    /**
     * Creates a new exam and save into the database.
     *
     * @param examResponseDTO Submitted DTO from web application.
     * @return a new Exam object.
     * @see Exam
     */
    public Exam create(ExamResponseDTO examResponseDTO) {
        /* Finds student by id. */
        Student student = studentRepository.getOne(examResponseDTO.getStudentId());
        /* Finds course by id. */
        Course course = courseRepository.getOne(examResponseDTO.getCourseId());


        if (examResponseDTO.getMark() <= 1 && examResponseDTO.getMark() >= 5) {
            return examRepository.save(new Exam(
                    examResponseDTO.getMark(),
                    examResponseDTO.getWrittenAt(),
                    ExamType.valueOf(examResponseDTO.getExamType()),
                    course,
                    student
            ));
        }
        return null;
    }

    /**
     * Updates a exam from database by id.
     *
     * @param id              Id of the exam.
     * @param examResponseDTO Submitted DTO from web application.
     * @return an updated exam.
     * @see Exam
     */
    public Exam update(Long id, ExamResponseDTO examResponseDTO) {
        /* Finds exam by id. */
        Exam exam = examRepository.getOne(id);

        if (examResponseDTO.getMark() >= 1 && examResponseDTO.getMark() <= 5) {
            exam.setMark(examResponseDTO.getMark());
            exam.setWrittenAt(examResponseDTO.getWrittenAt());
        }
        return examRepository.save(exam);
    }
}
