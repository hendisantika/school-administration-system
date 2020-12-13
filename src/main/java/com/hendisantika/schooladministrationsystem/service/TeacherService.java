package com.hendisantika.schooladministrationsystem.service;

import com.hendisantika.schooladministrationsystem.dto.response.TeacherPreferenceResponseDTO;
import com.hendisantika.schooladministrationsystem.dto.response.TeacherResponseDTO;
import com.hendisantika.schooladministrationsystem.entity.Course;
import com.hendisantika.schooladministrationsystem.entity.TeacherPreference;
import com.hendisantika.schooladministrationsystem.entity.user.Authority;
import com.hendisantika.schooladministrationsystem.entity.user.User;
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

    /**
     * Returns a Teacher object by id, if teacher exist
     * or returns a null value.
     *
     * @param id Id of the teacher.
     * @return a teacher object by id.
     * @see Teacher
     */
    public Teacher findById(Long id) {
        return teacherRepository
                .findById(id).orElse(null);
    }

    /**
     * Returns a Teacher object by username, if teacher exist
     * or returns a null value.
     *
     * @param userId Id of the teacher user.
     * @return a teacher object by user id.
     * @see Teacher
     */
    @Override
    public Teacher findByUserId(Long userId) {
        return teacherRepository
                .findAll()
                .stream()
                .filter(teacher -> teacher.getTeacher()
                        .getId().equals(userId))
                .findAny()
                .orElse(null);
    }

    /**
     * Creates a new teacher and save into the database.
     *
     * @param teacherResponseDTO Submitted DTO from web application.
     * @return a new Teacher object.
     * @see Teacher
     */
    public Teacher create(TeacherResponseDTO teacherResponseDTO) {
        User user = userRepository.findByUsername(teacherResponseDTO.getUsername());
        Teacher teacher = new Teacher();
        teacher.setEmail(teacherResponseDTO.getEmail());
        teacher.setPhone(teacherResponseDTO.getPhone());
        List<Authority> authorities = authService.findByName("ROLE_TEACHER");
        user.setAuthorities(authorities);
        teacher.setTeacher(user);
        teacherRepository.save(teacher);
        teacherPreferenceRepository.save(new TeacherPreference(teacher, 1, 1, 1, 1));
        return teacher;
    }

    /**
     * Updates a teacher from database by id.
     *
     * @param id                 Id of the teacher.
     * @param teacherResponseDTO Submitted DTO from web application.
     * @return an updated teacher.
     * @see Teacher
     */
    public Teacher update(Long id, TeacherResponseDTO teacherResponseDTO) {
        Teacher teacher = teacherRepository.getOne(id);
        teacher.setEmail(teacherResponseDTO.getEmail());
        teacher.setPhone(teacherResponseDTO.getPhone());

        return teacherRepository.save(teacher);
    }

    /**
     * Deletes a teacher from database by id.
     *
     * @param id Id of the teacher.
     */
    public void delete(Long id) {
        teacherRepository.delete(teacherRepository.getOne(id));
    }

    /**
     * Sets a course to teacher by ids.
     *
     * @param teacherId Id of the Teacher.
     * @param courseId  Id of the Course.
     */
    public void setCourse(Long teacherId, Long courseId) {
        Teacher teacher = teacherRepository.getOne(teacherId);
        Course course = courseRepository.getOne(courseId);
        course.setTeacher(teacher);
        courseRepository.save(course);
    }

    /**
     * Set all weights for exam types.
     *
     * @param teacherPreferenceResponseDTO Submitted DTO from web application.
     */
    public void setTeacherPreferences(TeacherPreferenceResponseDTO teacherPreferenceResponseDTO) {
        teacherPreferenceRepository.setPreferences(
                teacherPreferenceResponseDTO.getTeacherId(),
                teacherPreferenceResponseDTO.getTestWeight(),
                teacherPreferenceResponseDTO.getTopicTestWeight(),
                teacherPreferenceResponseDTO.getRepetitionWeight(),
                teacherPreferenceResponseDTO.getHomeworkWeight()
        );
    }
}
