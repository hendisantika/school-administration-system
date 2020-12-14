package com.hendisantika.schooladministrationsystem.config;

import com.hendisantika.schooladministrationsystem.service.*;
import com.hendisantika.schooladministrationsystem.service.auth.AuthorityService;
import com.hendisantika.schooladministrationsystem.service.auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
