package com.hendisantika.schooladministrationsystem.service;

import com.hendisantika.schooladministrationsystem.dto.ClassroomCourseResultDTO;
import com.hendisantika.schooladministrationsystem.dto.FailedStudentDTO;
import com.hendisantika.schooladministrationsystem.entity.Course;
import com.hendisantika.schooladministrationsystem.entity.user.group.Student;
import com.hendisantika.schooladministrationsystem.repository.CourseRepository;
import com.hendisantika.schooladministrationsystem.repository.user.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    /**
     * Returns a list of ClassroomCourseResultDTO. This function create an
     * average for each course by class id.
     *
     * @param classroomId Id of the class.
     * @return List of ClassroomCourseResultDTO by classroom id.
     */
    public List<ClassroomCourseResultDTO> showResultByCourse(Long classroomId) {
        List<ClassroomCourseResultDTO> result = new ArrayList<>();
        List<Student> students = getStudentsFromClassroom(classroomId);
        List<Long> courses = new ArrayList<>();
        for (Student student : students) {
            for (Course course : courseRepository.findAll()) {
                if (student.getCourses().contains(course) && !courses.contains(course.getId())) {
                    result.add(new ClassroomCourseResultDTO(
                            course,
                            collectResultByCourse(classroomId, course.getId())
                    ));
                    courses.add(course.getId());
                }
            }
        }
        return result;
    }

    private List<Student> getStudentsFromClassroom(Long id) {
        return studentRepository
                .findAll()
                .stream()
                .filter(student -> student.getClassroom().getId().equals(id))
                .collect(Collectors.toList());
    }
}
