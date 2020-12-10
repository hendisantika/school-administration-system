package com.hendisantika.schooladministrationsystem.service;

import com.hendisantika.schooladministrationsystem.entity.Classroom;
import com.hendisantika.schooladministrationsystem.repository.ClassroomRepository;
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
 * Date: 11/12/20
 * Time: 05.49
 */
@Service
public class ClassroomService {
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    private ClassroomRepository classroomRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private AuthorityService authService;

    /**
     * Returns a List of Classroom.
     *
     * @return classrooms from database.
     */
    public List<Classroom> findAll() {
        return classroomRepository.findAll();
    }

    /**
     * Returns a Classroom object by id, if classroom exist
     * or returns a null value.
     *
     * @param id Id of the classroom.
     * @return a classroom object by id.
     * @see Classroom
     */
    public Classroom findById(Long id) {
        return classroomRepository.findById(id).orElse(null);
    }

    /**
     * Returns a Classroom object by Headteacher Id if classroom exist
     * or returns a null value.
     *
     * @param id Id of the headteacher
     * @return a classroom object by headteacher id.
     */
    public Classroom findByHeadteacher(Long id) {
        return classroomRepository.findAll()
                .stream()
                .filter(classroom -> classroom.getHeadTeacher().getId().equals(id))
                .findAny()
                .orElse(null);
    }
}
