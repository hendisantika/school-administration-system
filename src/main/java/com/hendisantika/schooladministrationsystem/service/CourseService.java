package com.hendisantika.schooladministrationsystem.service;

import com.hendisantika.schooladministrationsystem.dto.response.CourseResponseDTO;
import com.hendisantika.schooladministrationsystem.entity.Course;
import com.hendisantika.schooladministrationsystem.entity.user.group.Student;
import com.hendisantika.schooladministrationsystem.entity.user.group.Teacher;
import com.hendisantika.schooladministrationsystem.repository.CourseRepository;
import com.hendisantika.schooladministrationsystem.repository.user.StudentRepository;
import com.hendisantika.schooladministrationsystem.repository.user.TeacherRepository;
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
 * Time: 05.56
 */
@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    /**
     * Returns a List of Courses.
     *
     * @return courses from database.
     */
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    /**
     * Returns a Course object by id, if course exist
     * or returns a null value.
     *
     * @param id Id of the course.
     * @return a course object by id.
     * @see Course
     */
    public Course findById(Long id) {
        return courseRepository
                .findById(id)
                .orElse(null);
    }

    /**
     * Returns a List of Courses by Teacher id.
     *
     * @param teacher_id Id of the Teacher.
     * @return a list of courses.
     */
    public List<Course> getCoursesByTeacherId(Long teacher_id) {
        return courseRepository.findAll()
                .stream()
                .filter(course -> course.getTeacher().getId().equals(teacher_id))
                .collect(Collectors.toList());
    }

    /**
     * Creates a new course and save into the database.
     *
     * @param courseResponseDTO Submitted DTO from web application.
     * @return a new Course object.
     * @see Course
     */
    public Course create(CourseResponseDTO courseResponseDTO) {
        /* Finds teacher by id. */
        Teacher teacher = teacherRepository.getOne(courseResponseDTO.getTeacherId());
        return courseRepository.save(new Course(
                courseResponseDTO.getTitle(),
                courseResponseDTO.getYear(),
                teacher
        ));
    }

    /**
     * Updates a course from database by id.
     *
     * @param id                Id of the course.
     * @param courseResponseDTO Submitted DTO from web application.
     * @return an updated course.
     * @see Course
     */
    public Course update(Long id, CourseResponseDTO courseResponseDTO) {
        /* Finds course by id. */
        Course course = courseRepository.getOne(id);
        /* Finds teacher by id. */
        Teacher teacher = teacherRepository.getOne(courseResponseDTO.getTeacherId());

        /* Updates the old course with a new data. */
        course.setTeacher(teacher);
        course.setTitle(course.getTitle());
        course.setYear(course.getYear());
        courseRepository.setTeacher(course.getId(), teacher.getId());

        return courseRepository.save(course);
    }

    /**
     * Deletes a course from database by id.
     *
     * @param id Id of the course.
     */
    public void delete(Long id) {
        courseRepository.deleteFromStudentCourse(id);
        courseRepository.delete(courseRepository.getOne(id));
    }

    /**
     * Sets a course to student by ids.
     *
     * @param studentId Id of the Course.
     * @param courseId  Id of the Student.
     */
    public void setCourse(Long studentId, Long courseId) {
        /* Finds student by id. */
        Student student = studentRepository.getOne(studentId);
        /* Finds course by id. */
        Course course = courseRepository.getOne(courseId);
        if (courseRepository.courseIsAlreadyTaken(studentId, courseId) == 0) {
            course.getStudents().add(student);
            courseRepository.save(course);
        }
    }

    /**
     * Unsets a course to student by ids.
     *
     * @param studentId Id of the Student.
     * @param courseId  Id of the Course.
     */
    public void unsetCourse(Long studentId, Long courseId) {
        courseRepository.unsetStudentFromCourse(courseId, studentId);
    }
}
