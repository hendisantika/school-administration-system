package com.hendisantika.schooladministrationsystem.service;

import com.hendisantika.schooladministrationsystem.dto.AttendanceDTO;
import com.hendisantika.schooladministrationsystem.dto.response.AttendanceResponseDTO;
import com.hendisantika.schooladministrationsystem.entity.Attendance;
import com.hendisantika.schooladministrationsystem.entity.user.group.Student;
import com.hendisantika.schooladministrationsystem.repository.AttendanceRepository;
import com.hendisantika.schooladministrationsystem.repository.user.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * Project : school-administration-system
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/12/20
 * Time: 05.45
 */
@Service
public class AttendanceService {
    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private StudentRepository studentRepository;

    /**
     * Returns a form that contains a list of students
     * and boolean field for each student.
     *
     * @param classroom_id Id of the classroom.
     * @return A form table to collect the missing students.
     */
    public List<AttendanceDTO> makeAttendanceFormToClassroom(Long classroom_id) {
        List<AttendanceDTO> result = new ArrayList<>();
        for (Student student : getAllStudentByClassroom(classroom_id)) {
            result.add(new AttendanceDTO(student));
        }
        return result;
    }

    /**
     * Creates new attendances for the missing students.
     *
     * @param attendanceResponseDTOS Submitted DTOs from web application.
     * @return List of Attendances.
     * @see Attendance
     */
    public List<Attendance> create(List<AttendanceResponseDTO> attendanceResponseDTOS) {
        List<Attendance> result = new ArrayList<>();
        for (AttendanceResponseDTO attendanceResponseDTO : attendanceResponseDTOS) {
            if (attendanceResponseDTO.isMiss()) {
                /* Finds student by id. */
                Student student = studentRepository.getOne(attendanceResponseDTO.getStudentId());
                Attendance attendance = new Attendance(
                        student,
                        attendanceResponseDTO.getLesson(),
                        attendanceResponseDTO.getDateOfMiss()
                );
                result.add(attendance);
                attendanceRepository.save(attendance);
            }
        }
        return result;
    }

    /**
     * Deletes an attendances by id.
     *
     * @param id Id of the Attendance.
     */
    public void delete(Long id) {
        Attendance attendance = attendanceRepository.getOne(id);
        attendanceRepository.delete(attendance);
    }

    /**
     * Verifies a missed lecture by id.
     *
     * @param id Id of the Attendance.
     */
    public void verify(Long id) {
        Attendance attendance = attendanceRepository.getOne(id);
        attendance.setVerified(true);
        attendanceRepository.save(attendance);
    }

    /**
     * Returns a List of non verified attendances by classroom.
     *
     * @param classroomId Id of the Classroom.
     * @return a list of attendances.
     */
    public List<Attendance> getAllAttendancesByClassroom(Long classroomId) {
        return attendanceRepository
                .findAll()
                .stream()
                .filter(attendance -> attendance.getStudent().getClassroom().getId().equals(classroomId))
                .sorted(Comparator.comparing(Attendance::getDateOfMiss).reversed())
                .collect(Collectors.toList());
    }

    /**
     * Returns a List of Attendances by student id.
     *
     * @param studentId Id of the Student.
     * @return a list of the attendances.
     */
    public List<Attendance> getAllByStudent(Long studentId) {
        return attendanceRepository
                .findAll()
                .stream()
                .filter(attendance -> attendance.getStudent().getId().equals(studentId) && !attendance.isVerified())
                .sorted(Comparator.comparing(Attendance::getDateOfMiss).reversed())
                .collect(Collectors.toList());
    }

    /**
     * Returns a List of Students, who are in the class.
     *
     * @param classroomId Id of the classroom.
     * @return List of student.
     */
    private List<Student> getAllStudentByClassroom(Long classroomId) {
        return studentRepository
                .findAll()
                .stream()
                .filter(student -> student.getClassroom().getId().equals(classroomId))
                .collect(Collectors.toList());
    }
}
