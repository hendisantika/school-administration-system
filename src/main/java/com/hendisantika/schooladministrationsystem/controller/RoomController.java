package com.hendisantika.schooladministrationsystem.controller;

import com.hendisantika.schooladministrationsystem.dto.response.RoomResponseDTO;
import com.hendisantika.schooladministrationsystem.entity.Room;
import com.hendisantika.schooladministrationsystem.service.RoomService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
 * Date: 20/12/20
 * Time: 05.24
 */
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class RoomController {

    @Autowired
    private RoomService roomService;

    @ApiOperation(value = "${RoomController.findAll}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 404, message = "Rooms don't found"),
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    @GetMapping(value = "/rooms/all")
    public List<Room> findAll() {
        return roomService.findAll();
    }

    @ApiOperation(value = "${RoomController.FindById}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 404, message = "Room doesn't found"),
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    @GetMapping(value = "/rooms/{id}")
    public Room findById(@PathVariable Long id) {
        return roomService.findById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${RoomController.create}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 404, message = "Room cannot created"),
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    @PostMapping(value = "/rooms/create")
    public Room create(@RequestBody RoomResponseDTO roomResponseDTO) {
        return roomService.create(roomResponseDTO);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${RoomController.delete}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 404, message = "Room doesn't found"),
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    @DeleteMapping(value = "/rooms/{id}")
    public String delete(@PathVariable Long id) {
        roomService.delete(id);
        return id.toString();
    }
}
