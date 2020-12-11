package com.hendisantika.schooladministrationsystem.service;

import com.hendisantika.schooladministrationsystem.dto.FailedStudentDTO;
import com.hendisantika.schooladministrationsystem.entity.Course;
import com.hendisantika.schooladministrationsystem.entity.user.group.Student;
import com.hendisantika.schooladministrationsystem.repository.CourseRepository;
import com.hendisantika.schooladministrationsystem.repository.user.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : school-administration-system
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/12/20
 * Time: 06.08
 */
@Service
public class HeadTeacherService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    /**
     * Find all student who failed at least one subject.
     *
     * @param classroomId Id of the class.
     * @return List of Failed Student with courses.
     */
    public List<FailedStudentDTO> findFailedStudentsInClass(Long classroomId) {
        List<FailedStudentDTO> result = new ArrayList<>();
        List<Student> students = getStudentsFromClassroom(classroomId);
        for (Student student : students) {
            List<Course> failedCourses = new ArrayList<>();
            for (Course course : courseRepository.findAll()) {
                if (student.getCourses().contains(course)) {
                    double avg = calcAverageByStudent(student, course.getId());
                    if (avg < 2 && avg >= 1) {
                        failedCourses.add(course);
                    }
                }
            }
            if (!failedCourses.isEmpty()) {
                result.add(new FailedStudentDTO(
                        student,
                        failedCourses
                ));
            }
        }
        return result;
    }
}
