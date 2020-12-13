package com.hendisantika.schooladministrationsystem.service;

import com.hendisantika.schooladministrationsystem.dto.SummaryDTO;
import com.hendisantika.schooladministrationsystem.dto.response.StudentResponseDTO;
import com.hendisantika.schooladministrationsystem.entity.Classroom;
import com.hendisantika.schooladministrationsystem.entity.Course;
import com.hendisantika.schooladministrationsystem.entity.Exam;
import com.hendisantika.schooladministrationsystem.entity.ExamType;
import com.hendisantika.schooladministrationsystem.entity.TeacherPreference;
import com.hendisantika.schooladministrationsystem.entity.user.User;
import com.hendisantika.schooladministrationsystem.entity.user.group.Gender;
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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * Creates a new student and save into the database.
     *
     * @param studentResponseDTO Submitted DTO from web application.
     * @return a new Student object.
     * @see Student
     */
    public Student create(StudentResponseDTO studentResponseDTO) {
        User user = userRepository.findByUsername(studentResponseDTO.getUsername());
        Classroom classroom = classroomRepository.getOne(studentResponseDTO.getClassroomId());
        Student student = new Student();

        student.setAddress(studentResponseDTO.getAddress());
        student.setClassroom(classroom);
        student.setDateOfBirth(studentResponseDTO.getDateOfBirth());
        student.setGender(Gender.valueOf(studentResponseDTO.getGender()));
        student.setEducationId(studentResponseDTO.getEducationId());
        student.setHealthCareId(studentResponseDTO.getHealthCareId());
        student.setStart_year(studentResponseDTO.getStartYear());
        student.setParent1Name(studentResponseDTO.getParent1Name());
        student.setParent2Name(studentResponseDTO.getParent2Name());
        student.setParent1Phone(studentResponseDTO.getParent1Phone());
        student.setParent2Phone(studentResponseDTO.getParent2Phone());
        student.setStudent(user);
        studentRepository.save(student);
        return student;
    }

    /**
     * Updates a student from database by id.
     *
     * @param id                 Id of the student.
     * @param studentResponseDTO Submitted DTO from web application.
     * @return an updated student.
     * @see Student
     */
    public Student update(Long id, StudentResponseDTO studentResponseDTO) {
        Classroom classroom = classroomRepository.getOne(studentResponseDTO.getClassroomId());
        Student student = studentRepository.getOne(id);

        student.setAddress(studentResponseDTO.getAddress());
        student.setClassroom(classroom);
        student.setDateOfBirth(studentResponseDTO.getDateOfBirth());
        student.setGender(Gender.valueOf(studentResponseDTO.getGender()));
        student.setEducationId(studentResponseDTO.getEducationId());
        student.setHealthCareId(studentResponseDTO.getHealthCareId());
        student.setStart_year(studentResponseDTO.getStartYear());
        student.setParent1Name(studentResponseDTO.getParent1Name());
        student.setParent2Name(studentResponseDTO.getParent2Name());
        student.setParent1Phone(studentResponseDTO.getParent1Phone());
        student.setParent2Phone(studentResponseDTO.getParent2Phone());

        return studentRepository.save(student);
    }

    /**
     * Deletes a student from database by id.
     *
     * @param id Id of the student.
     */
    public void delete(Long id) {
        deleteStudentData(id);
        studentRepository.deleteById(id);
    }

    /**
     * Returns a List of course-marks pairs by student id.
     *
     * @param id Id of the student.
     * @return List of results for each course.
     */
    public List<SummaryDTO> getSummary(Long id) {
        Student student = studentRepository.getOne(id);
        List<SummaryDTO> summaryDTOList = new ArrayList<>();
        for (Course course : courseRepository.findAll()) {
            if (student.getCourses().contains(course)) {
                List<Exam> exams = student.getExams()
                        .stream()
                        .filter(exam -> exam.getCourse().getId().equals(course.getId()))
                        .collect(Collectors.toList());
                summaryDTOList.add(new SummaryDTO(course.getTitle(), exams, weightedAverage(exams, course)));
            }
        }
        return summaryDTOList;
    }

    private double weightedAverage(List<Exam> exams, Course course) {
        TeacherPreference teacherPreference = teacherPreferenceRepository.getOne(course.getTeacher().getId());
        double sum = 0;
        int examsNumber = exams.size();
        for (Exam exam : exams) {
            if (exam.getExamType().equals(ExamType.TEST)) {
                sum += exam.getMark() * teacherPreference.getTestWeight();
            }
            if (exam.getExamType().equals(ExamType.TOPIC_TEST)) {
                sum += exam.getMark() * teacherPreference.getTopicTestWeight();
            }
            if (exam.getExamType().equals(ExamType.REPETITION)) {
                sum += exam.getMark() * teacherPreference.getRepetitionWeight();
            }
            if (exam.getExamType().equals(ExamType.HOMEWORK)) {
                sum += exam.getMark() * teacherPreference.getHomeworkWeight();
            }
        }
        return sum / examsNumber;
    }

    /**
     * Deletes all data, which connected to Student.
     *
     * @param studentId Id of the student.
     */
    private void deleteStudentData(Long studentId) {
        deleteAllAttendanceByStudent(studentId);
        deleteAllExamByStudent(studentId);
        deleteAllReportByStudent(studentId);
    }

    /**
     * Deletes all exam which student wrote.
     *
     * @param studentId Id of the student.
     */
    private void deleteAllExamByStudent(Long studentId) {
        for (Exam exam : examRepository.findAll()) {
            if (exam.getStudent().getId().equals(studentId)) {
                examRepository.delete(exam);
            }
        }
    }
}
