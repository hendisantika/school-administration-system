package com.hendisantika.schooladministrationsystem.service;

import com.hendisantika.schooladministrationsystem.entity.user.group.Student;
import com.hendisantika.schooladministrationsystem.repository.AttendanceRepository;
import com.hendisantika.schooladministrationsystem.repository.ClassroomRepository;
import com.hendisantika.schooladministrationsystem.repository.CourseRepository;
import com.hendisantika.schooladministrationsystem.repository.ExamRepository;
import com.hendisantika.schooladministrationsystem.repository.ReportRepository;
import com.hendisantika.schooladministrationsystem.repository.TeacherPreferenceRepository;
import com.hendisantika.schooladministrationsystem.repository.user.StudentRepository;
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
 * Time: 05.55
 */
@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClassroomRepository classroomRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private TeacherPreferenceRepository teacherPreferenceRepository;

    /**
     * Returns a List of Students.
     *
     * @return students from database.
     */
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    /**
     * Returns a Students object by id, if classroom exist
     * or returns a null value.
     *
     * @param id Id of the student.
     * @return a student object by id.
     * @see Student
     */
    public Student findById(Long id) {
        return studentRepository
                .findById(id).orElse(null);
    }

    /**
     * Returns a Students object by username, if classroom exist
     * or returns a null value.
     *
     * @param userId Id of the student user.
     * @return a student object by user id.
     * @see Student
     */
    public Student findByUserId(Long userId) {
        return studentRepository
                .findAll()
                .stream()
                .filter(student -> student.getStudent()
                        .getId().equals(userId))
                .findAny()
                .orElse(null);
    }

}
