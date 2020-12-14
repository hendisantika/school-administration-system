package com.hendisantika.schooladministrationsystem.config;

import com.hendisantika.schooladministrationsystem.dto.ExamDTO;
import com.hendisantika.schooladministrationsystem.dto.response.ClassroomResponseDTO;
import com.hendisantika.schooladministrationsystem.dto.response.CourseResponseDTO;
import com.hendisantika.schooladministrationsystem.dto.response.ExamResponseDTO;
import com.hendisantika.schooladministrationsystem.dto.response.ReportResponseDTO;
import com.hendisantika.schooladministrationsystem.dto.response.StudentResponseDTO;
import com.hendisantika.schooladministrationsystem.dto.response.TeacherResponseDTO;
import com.hendisantika.schooladministrationsystem.dto.response.UserResponseDTO;
import com.hendisantika.schooladministrationsystem.entity.Course;
import com.hendisantika.schooladministrationsystem.entity.ExamType;
import com.hendisantika.schooladministrationsystem.entity.user.UserRoleName;
import com.hendisantika.schooladministrationsystem.entity.user.group.Student;
import com.hendisantika.schooladministrationsystem.service.*;
import com.hendisantika.schooladministrationsystem.service.auth.AuthorityService;
import com.hendisantika.schooladministrationsystem.service.auth.UserService;
import org.pmw.tinylog.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * Project : school-administration-system
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 15/12/20
 * Time: 05.47
 */
@Service
public class InitData {
    @Autowired
    private UserService userService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private TimeTableService timeTableService;

    @Autowired
    private ExamService examService;

    @Autowired
    private ReportService reportService;

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private RoomService roomService;

    public void init() {
        String username = "admin";
        String password = "admin";
        if (userService.findAll().isEmpty()) {
            authorityService.save(UserRoleName.ROLE_ADMIN);
            authorityService.save(UserRoleName.ROLE_STUDENT);
            authorityService.save(UserRoleName.ROLE_TEACHER);
            authorityService.save(UserRoleName.ROLE_HEADTEACHER);
            userService.save(new UserResponseDTO(username, password, "admin", "ROLE_ADMIN"));
            Logger.info("Username: {0}\nPassword: {1}", username, password);
        }
        //testData();
    }

    private void testData() {
        testDataTeacher();
        testDataClassroom();
        testDataStudent();
        testDataCourse();
        testDataRoom();
        testDataTimeTable();
        testDataAttendance();
        testDataExam();
        testDataReport();
    }

    private void testDataStudent() {
        List<String> usernames = new ArrayList<>();
        for (int i = 1; i < 21; i++) {
            UserResponseDTO userResponseDTO = new UserResponseDTO(
                    "student" + i,
                    "student",
                    "student" + i + "'s fullname",
                    "ROLE_STUDENT"
            );
            userService.save(userResponseDTO);
            usernames.add(userResponseDTO.getUsername());
        }

        for (String username : usernames) {
            Random random = new Random();
            int randYear = random.nextInt(2) + 2010;
            int randMonth = random.nextInt(12) + 1;
            int randDay = random.nextInt(28) + 1;
            int randHealthCare = random.nextInt(900) + 100;
            studentService.create(new StudentResponseDTO(
                    username,
                    LocalDate.of(randYear, randMonth, randDay),
                    2015,
                    username + "' address",
                    "FEMALE",
                    random.nextInt(100000) + 900000 + "",
                    randHealthCare + "/" + randHealthCare + "/" + randHealthCare,
                    "Parent 1 name",
                    "Parent 2 name",
                    "+36 00 000 0000",
                    "+36 00 000 0000",
                    (long) (random.nextInt(2) + 1)
            ));
        }
    }

    private void testDataTeacher() {
        List<String> usernames = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            UserResponseDTO userResponseDTO = new UserResponseDTO(
                    "teacher" + i,
                    "teacher",
                    "teacher" + i + "'s fullname",
                    "ROLE_TEACHER"
            );
            userService.save(userResponseDTO);
            usernames.add(userResponseDTO.getUsername());
        }

        for (String username : usernames) {
            teacherService.create(new TeacherResponseDTO(
                    username,
                    username + "@school.com",
                    "+36 00 000 0000"
            ));
        }
    }

    private void testDataClassroom() {
        classroomService.create(new ClassroomResponseDTO(
                2025,
                2028,
                9,
                'a',
                2L
        ));
        classroomService.create(new ClassroomResponseDTO(
                2025,
                2028,
                9,
                'b',
                3L
        ));
    }

    private void testDataCourse() {
        courseService.create(new CourseResponseDTO(
                "Irodalom",
                9,
                1L
        ));
        courseService.create(new CourseResponseDTO(
                "Nyelvtan",
                9,
                2L
        ));
        courseService.create(new CourseResponseDTO(
                "Matematika",
                9,
                3L
        ));
        courseService.create(new CourseResponseDTO(
                "Történelem",
                9,
                4L
        ));
        courseService.create(new CourseResponseDTO(
                "Testnevelés",
                9,
                5L
        ));
        courseService.create(new CourseResponseDTO(
                "Angol",
                9,
                6L
        ));
        courseService.create(new CourseResponseDTO(
                "Kémia",
                9,
                7L
        ));
        courseService.create(new CourseResponseDTO(
                "Biológia",
                9,
                7L
        ));
        courseService.create(new CourseResponseDTO(
                "Fizika",
                9,
                8L
        ));
        courseService.create(new CourseResponseDTO(
                "Informatika",
                9,
                9L
        ));
        courseService.create(new CourseResponseDTO(
                "Földrajz",
                9,
                10L
        ));

        for (Long i = 1L; i < 3L; i++) {
            for (long j = 1L; j < 12L; j++) {
                classroomService.setCourse(i, j);
            }
        }
    }

    private void testDataExam() {
        List<Course> courses = courseService.findAll();
        List<ExamType> examTypes = examService.getAllExamType();
        Random random = new Random();

        for (int i = 0; i < 40; i++) {
            int randYear = random.nextInt(2) + 2025;
            int randMonth = randYear == 2025 ? random.nextInt(4) + 9
                    : random.nextInt(6) + 1;
            int randDay = random.nextInt(28) + 1;
            Long classroom_id = i % 2 == 0 ? 1L : 2L;
            Course course = courses.get(random.nextInt(courses.size()));
            List<ExamDTO> examDTOS = examService.makeExamsFormToClassroom(
                    classroom_id,
                    LocalDate.of(randYear, randMonth, randDay), ""
            );
            List<ExamResponseDTO> examResponseDTOS = new ArrayList<>();

            for (ExamDTO examDTO : examDTOS) {
                int mark = random.nextInt(5) + 1;
                examResponseDTOS.add(new ExamResponseDTO(
                        mark,
                        examDTO.getWrittenAt(),
                        examTypes.get(random.nextInt(examTypes.size())).toString(),
                        course.getId(),
                        examDTO.getStudent().getId()
                ));
            }
            examService.createExamsFromForm(examResponseDTOS);
        }
    }

    private void testDataReport() {
        List<Course> courses = courseService.findAll();
        List<Student> students = studentService.findAll();
        Random random = new Random();

        for (Student student : students) {
            for (Course course : courses) {
                int mark = random.nextInt(5) + 1;
                reportService.create(new ReportResponseDTO(
                        9,
                        1,
                        mark,
                        student.getId(),
                        course.getId()

                ));
            }
        }
        for (Student student : students) {
            for (Course course : courses) {
                int mark = random.nextInt(5) + 1;
                reportService.create(new ReportResponseDTO(
                        9,
                        2,
                        mark,
                        student.getId(),
                        course.getId()
                ));
            }
        }
    }

}
