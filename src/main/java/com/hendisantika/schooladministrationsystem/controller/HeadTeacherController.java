package com.hendisantika.schooladministrationsystem.controller;

import com.hendisantika.schooladministrationsystem.dto.ClassroomCourseResultDTO;
import com.hendisantika.schooladministrationsystem.dto.FailedStudentDTO;
import com.hendisantika.schooladministrationsystem.service.HeadTeacherService;
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
 * Date: 19/12/20
 * Time: 05.40
 */
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class HeadTeacherController {

    @Autowired
    private HeadTeacherService headTeacherService;

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_HEADTEACHER')")
    @ApiOperation(value = "${HeadTeacherController.findFailedStudentsInClass}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 404, message = "failed students don't found"),
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    @GetMapping(value = "/headteacher/find-failed/{classroomId}")
    public List<FailedStudentDTO> findFailedStudentsInClass(@PathVariable Long classroomId) {
        return headTeacherService.findFailedStudentsInClass(classroomId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_HEADTEACHER')")
    @ApiOperation(value = "${HeadTeacherController.findFailedStudentsInClass}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 404, message = "failed students don't found"),
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    @GetMapping(value = "/headteacher/show-result/{classroomId}")
    public List<ClassroomCourseResultDTO> showResultByCourse(@PathVariable Long classroomId) {
        return headTeacherService.showResultByCourse(classroomId);
    }
}
