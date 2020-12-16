package com.hendisantika.schooladministrationsystem.controller;

import com.hendisantika.schooladministrationsystem.entity.Attendance;
import com.hendisantika.schooladministrationsystem.service.AttendanceService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : school-administration-system
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 17/12/20
 * Time: 05.47
 */
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PreAuthorize("hasRole('ROLE_TEACHER') or hasRole('ROLE_HEADTEACHER') or" +
            "@securityService.hasStudentAccess(principal.id, #studentId)")
    @ApiOperation(value = "${AttendanceController.getAllByStudent}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 404, message = "Attendances don't found"),
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    @GetMapping(value = "/attendances/all/{studentId}")
    public List<Attendance> getAllByStudent(@PathVariable Long studentId) {
        return attendanceService.getAllByStudent(studentId);
    }
}
