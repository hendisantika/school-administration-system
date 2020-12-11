package com.hendisantika.schooladministrationsystem.service;

import com.hendisantika.schooladministrationsystem.dto.ExamDTO;
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

import java.time.LocalDate;
import java.util.ArrayList;
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

    /**
     * Deletes a exam from database by id.
     *
     * @param id Id of the exam.
     */
    public void delete(Long id) {
        examRepository.delete(examRepository.getOne(id));
    }

    /**
     * Returns a form that contains a list of students
     * and mark field for each student.
     *
     * @param classroomId Id of the classroom.
     * @param written_at  The exam date.
     * @return A form table to create exams to all student in classroom.
     */
    public List<ExamDTO> makeExamsFormToClassroom(Long classroomId, LocalDate written_at, String examType) {
        List<Student> students = getStudentFromClassroom(classroomId);
        List<ExamDTO> result = new ArrayList<>();
        for (Student student : students) {
            result.add(new ExamDTO(student, written_at, examType));
        }
        return result;
    }

    /**
     * Creates a new exams and save into the database.
     *
     * @param examResponseDTOS Submitted DTOs from web application.
     * @return a new Exam objects.
     * @see Exam
     */
    public List<Exam> createExamsFromForm(List<ExamResponseDTO> examResponseDTOS) {
        List<Exam> result = new ArrayList<>();
        /* Finds course by id. */
        Course course = courseRepository.getOne(examResponseDTOS.get(0).getCourseId());

        for (ExamResponseDTO examResponseDTO : examResponseDTOS) {
            /* Finds student by id. */
            if (examResponseDTO.getMark() >= 1 && examResponseDTO.getMark() <= 5) {
                Student student = studentRepository.getOne(examResponseDTO.getStudentId());
                Exam exam = new Exam(
                        examResponseDTO.getMark(),
                        examResponseDTO.getWrittenAt(),
                        ExamType.valueOf(examResponseDTO.getExamType()),
                        course,
                        student
                ); // Creates a new exam.
                result.add(exam);
                examRepository.save(exam); //Saves the exam.
            }

        }
        return result;
    }

    /**
     * Collect all exam type.
     *
     * @return exam types.
     */
    public List<ExamType> getAllExamType() {
        List<ExamType> result = new ArrayList<>();
        result.add(ExamType.TOPIC_TEST);
        result.add(ExamType.TEST);
        result.add(ExamType.REPETITION);
        result.add(ExamType.HOMEWORK);
        return result;
    }

    /**
     * Returns a List of Students, who are in the class.
     *
     * @param classroomId Id of the classroom.
     * @return List of students.
     */
    private List<Student> getStudentFromClassroom(Long classroomId) {
        return studentRepository
                .findAll()
                .stream()
                .filter(student -> student.getClassroom().getId().equals(classroomId))
                .collect(Collectors.toList());
    }
}
