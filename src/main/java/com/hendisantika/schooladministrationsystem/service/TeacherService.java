package com.hendisantika.schooladministrationsystem.service;

import com.hendisantika.schooladministrationsystem.entity.user.group.Teacher;
import com.hendisantika.schooladministrationsystem.repository.CourseRepository;
import com.hendisantika.schooladministrationsystem.repository.TeacherPreferenceRepository;
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
 * Date: 14/12/20
 * Time: 06.00
 */
@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TeacherPreferenceRepository teacherPreferenceRepository;

    @Autowired
    private AuthorityService authService;

    /**
     * Returns a List of Teachers.
     *
     * @return teachers from database.
     */
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }
}
