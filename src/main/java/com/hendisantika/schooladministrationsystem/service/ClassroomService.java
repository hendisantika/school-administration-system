package com.hendisantika.schooladministrationsystem.service;

import com.hendisantika.schooladministrationsystem.dto.response.ClassroomResponseDTO;
import com.hendisantika.schooladministrationsystem.entity.Classroom;
import com.hendisantika.schooladministrationsystem.entity.user.Authority;
import com.hendisantika.schooladministrationsystem.entity.user.group.Teacher;
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

    /**
     * Creates a new classroom and save into the database.
     *
     * @param classroomResponseDTO Submitted DTO from web application.
     * @return a new Classroom object.
     * @see Classroom
     */
    public Classroom create(ClassroomResponseDTO classroomResponseDTO) {
        /* Finds teacher by id. */
        Teacher teacher = teacherRepository.getOne(classroomResponseDTO.getHeadTeacherId());
        Classroom classroom = new Classroom(
                classroomResponseDTO.getStartYear(),
                classroomResponseDTO.getEndYear(),
                classroomResponseDTO.getYear(),
                classroomResponseDTO.getLetter(),
                teacher
        ); // Creates a new classroom.
        /* sets back a teacher role from ROLE_TEACHER to ROLE_HEADTEACHER. */
        classroomRepository.setHeadteacherFromTeacher(teacherRepository.GetUserIdByTeacherId(teacher.getId()));
        return classroomRepository.save(classroom);
    }

    /**
     * Updates a classroom from database by id.
     *
     * @param id                   Id of the classroom.
     * @param classroomResponseDTO Submitted DTO from web application.
     * @return an updated classroom.
     * @see Classroom
     */
    public Classroom update(Long id, ClassroomResponseDTO classroomResponseDTO) {
        /* Finds classroom by id. */
        Classroom classroom = classroomRepository.getOne(id);
        /* Finds teacher by id. */
        Teacher teacher = teacherRepository.getOne(classroomResponseDTO.getHeadTeacherId());

        /* Updates the old classroom with a new data. */
        classroom.setStart_year(classroomResponseDTO.getStartYear());
        classroom.setEnd_year(classroomResponseDTO.getEndYear());
        classroom.setHeadTeacher(teacher);
        classroom.setLetter(classroomResponseDTO.getLetter());
        classroom.setYear(classroomResponseDTO.getYear());

        /* sets  a teacher role from ROLE_HEADTEACHER to ROLE_TEACHER. */
        classroomRepository.setTeacherFromHeadteacher(classroom.getHeadTeacher().getTeacher().getId());
        /* sets  a teacher role from ROLE_TEACHER to ROLE_HEADTEACHER. */
        classroomRepository.setHeadteacherFromTeacher(teacher.getTeacher().getId());

        return classroomRepository.save(classroom);
    }

    /**
     * Deletes a classroom from database by id.
     *
     * @param id Id of the classroom.
     */
    public void delete(Long id) {
        Classroom classroom = classroomRepository.getOne(id);
        List<Authority> authorities = authService.findByName("ROLE_TEACHER");
        classroom.getHeadTeacher().getTeacher().setAuthorities(authorities);
        classroomRepository.delete(classroomRepository.getOne(id));
    }
}
