package com.hendisantika.schooladministrationsystem.controller;

import com.hendisantika.schooladministrationsystem.dto.response.ExamResponseDTO;
import com.hendisantika.schooladministrationsystem.entity.Exam;
import com.hendisantika.schooladministrationsystem.service.ExamService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : school-administration-system
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 18/12/20
 * Time: 06.29
 */
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class ExamController {

    @Autowired
    private ExamService examService;

    @PreAuthorize("hasRole('ROLE_TEACHER') or hasRole('ROLE_HEADTEACHER')")
    @ApiOperation(value = "${ExamController.findAllByStudent}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 404, message = "Exams don't found"),
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    @PostMapping(value = "/exams/student/{studentId}")
    public List<Exam> findAllByStudent(@PathVariable Long studentId,
                                       @RequestBody Long courseId) {
        return examService.findAllByStudent(studentId, courseId);
    }

    @PreAuthorize("hasRole('ROLE_TEACHER') or hasRole('ROLE_HEADTEACHER')")
    @ApiOperation(value = "${ExamController.findById}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 404, message = "Exam doesn't found"),
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    @GetMapping(value = "/exams/{id}")
    public Exam findById(@PathVariable Long id) {
        return examService.findById(id);
    }

    @PreAuthorize("hasRole('ROLE_TEACHER') or hasRole('ROLE_HEADTEACHER')")
    @ApiOperation(value = "${ExamController.create}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 404, message = "Exam cannot created"),
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    @PostMapping(value = "/exams/create")
    public Exam create(@RequestBody ExamResponseDTO examResponseDTO) {
        return examService.create(examResponseDTO);
    }

    @PreAuthorize("hasRole('ROLE_TEACHER') or hasRole('ROLE_HEADTEACHER')")
    @ApiOperation(value = "${ExamController.update}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 404, message = "Exam doesn't found"),
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    @PutMapping(value = "/exams/update/{id}")
    public Exam update(@PathVariable Long id,
                       @RequestBody ExamResponseDTO examResponseDTO) {
        return examService.update(id, examResponseDTO);
    }
}
