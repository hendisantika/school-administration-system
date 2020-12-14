package com.hendisantika.schooladministrationsystem.config;

import com.hendisantika.schooladministrationsystem.dto.response.StudentResponseDTO;
import com.hendisantika.schooladministrationsystem.dto.response.UserResponseDTO;
import com.hendisantika.schooladministrationsystem.entity.user.UserRoleName;
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
}
