package com.hendisantika.schooladministrationsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : school-administration-system
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 08/12/20
 * Time: 15.01
 */
@Entity
@Table(name = "rooms")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {

    /**
     * Id field [GENERATED AUTOMATICALLY].
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Classroom where course hold.
     */
    @Column(name = "classroomNumber", nullable = false, length = 15)
    private String classroomNumber;
    @JsonIgnore
    @OneToMany(mappedBy = "room")
    private List<TimeTableEntity> timeTableEntities = new ArrayList<>();

    /**
     * Constructor to make a new instance.
     *
     * @param classroomNumber The room number.
     */
    public Room(String classroomNumber) {
        this.classroomNumber = classroomNumber;
    }

    public List<TimeTableEntity> getTimeTableEntities() {
        return timeTableEntities;
    }

    public void setTimeTableEntities(List<TimeTableEntity> timeTableEntities) {
        this.timeTableEntities = timeTableEntities;
    }
}
