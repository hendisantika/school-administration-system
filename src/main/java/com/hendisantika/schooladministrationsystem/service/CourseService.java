package com.hendisantika.schooladministrationsystem.service;

import com.hendisantika.schooladministrationsystem.entity.Course;
import com.hendisantika.schooladministrationsystem.repository.CourseRepository;
import com.hendisantika.schooladministrationsystem.repository.user.StudentRepository;
import com.hendisantika.schooladministrationsystem.repository.user.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

}
