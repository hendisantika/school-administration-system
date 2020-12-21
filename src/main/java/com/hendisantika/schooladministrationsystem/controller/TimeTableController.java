package com.hendisantika.schooladministrationsystem.controller;

import com.hendisantika.schooladministrationsystem.entity.TimeTableEntity;
import com.hendisantika.schooladministrationsystem.service.TimeTableService;
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
 * Date: 21/12/20
 * Time: 09.17
 */
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class TimeTableController {

    @Autowired
    private TimeTableService timeTableService;

    @PreAuthorize("hasRole('ROLE_ADMIN') or @securityService.hasStudentAccess(principal.id, #id)")
    @ApiOperation(value = "${TimeTableController.getTimeTableByTeacher}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 404, message = "Timetable doesn't found"),
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    @GetMapping(value = "/timetables/student/{id}")
    public TimeTableEntity[][] getTimeTableByStudent(@PathVariable Long id) {
        return timeTableService.getTimeTableByStudent(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or @securityService.hasTeacherAccess(principal.id, #id)")
    @ApiOperation(value = "${TimeTableController.getTimeTableByTeacher}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 404, message = "Timetable doesn't found"),
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    @GetMapping(value = "/timetables/teacher/{id}")
    public TimeTableEntity[][] getTimeTableByTeacher(@PathVariable Long id) {
        return timeTableService.getTimeTableByTeacher(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${TimeTableController.getTimeTableEntitiesByCourse}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 404, message = "Timetable doesn't found to course"),
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    @GetMapping(value = "/timetables/course/{course_id}")
    public List<TimeTableEntity> getTimeTableEntitiesByCourse(@PathVariable Long course_id) {
        return timeTableService.getTimeTableEntitiesByCourse(course_id);
    }
}
