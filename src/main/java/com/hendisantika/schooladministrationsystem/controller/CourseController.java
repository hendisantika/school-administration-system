package com.hendisantika.schooladministrationsystem.controller;

import com.hendisantika.schooladministrationsystem.dto.response.CourseResponseDTO;
import com.hendisantika.schooladministrationsystem.entity.Course;
import com.hendisantika.schooladministrationsystem.service.CourseService;
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
 * Time: 06.24
 */
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class CourseController {

    @Autowired
    private CourseService courseService;

    @ApiOperation(value = "${CourseController.findAll}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 404, message = "Courses don't found"),
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    @GetMapping(value = "/courses/all")
    public List<Course> findAll() {
        return courseService.findAll();
    }

    @ApiOperation(value = "${CourseController.FindById}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 404, message = "Course doesn't found"),
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    @GetMapping(value = "/courses/{id}")
    public Course findById(@PathVariable Long id) {
        return courseService.findById(id);
    }

    @ApiOperation(value = "${CourseController.getCoursesByTeacherId}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 404, message = "Course doesn't found"),
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    @GetMapping(value = "/courses/teacher/{teacherId}")
    public List<Course> getCoursesByTeacherId(@PathVariable Long teacherId) {
        return courseService.getCoursesByTeacherId(teacherId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${CourseController.create}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 404, message = "Course cannot created"),
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    @PostMapping(value = "/courses/create")
    public Course create(@RequestBody CourseResponseDTO courseResponseDTO) {
        return courseService.create(courseResponseDTO);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${CourseController.update}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 404, message = "Course doesn't found"),
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    @PutMapping(value = "/courses/update/{id}")
    public Course update(@PathVariable Long id,
                         @RequestBody CourseResponseDTO courseResponseDTO) {
        return courseService.update(id, courseResponseDTO);
    }
}
