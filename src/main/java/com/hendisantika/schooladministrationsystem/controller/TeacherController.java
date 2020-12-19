package com.hendisantika.schooladministrationsystem.controller;

import com.hendisantika.schooladministrationsystem.entity.user.group.Teacher;
import com.hendisantika.schooladministrationsystem.service.TeacherService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : school-administration-system
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 20/12/20
 * Time: 05.32
 */
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${TeacherController.findAll}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 404, message = "Teachers don't found"),
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    @GetMapping(value = "/teachers/all")
    public List<Teacher> findAll() {
        return teacherService.findAll();
    }
}
