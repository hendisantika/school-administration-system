package com.hendisantika.schooladministrationsystem.service;

import com.hendisantika.schooladministrationsystem.dto.response.RoomResponseDTO;
import com.hendisantika.schooladministrationsystem.entity.Room;
import com.hendisantika.schooladministrationsystem.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : school-administration-system
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 13/12/20
 * Time: 17.55
 */
@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    /**
     * Returns a List of Room.
     *
     * @return Rooms from database.
     */
    public List<Room> findAll() {
        return roomRepository.findAll();
    }


    /**
     * Returns a Room object by id, if course exist
     * or returns a null value.
     *
     * @param id Id of the Room.
     * @return a Room object by id.
     * @see Room
     */
    public Room findById(Long id) {
        return roomRepository.getOne(id);
    }

    /**
     * Creates a new Room and save into the database.
     *
     * @param roomResponseDTO Submitted DTO from web application.
     * @return a new Room object.
     * @see Room
     */
    public Room create(RoomResponseDTO roomResponseDTO) {
        return roomRepository.save(new Room(
                roomResponseDTO.getClassroomNumber()
        ));
    }
}
