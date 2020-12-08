package com.hendisantika.schooladministrationsystem.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by IntelliJ IDEA.
 * Project : school-administration-system
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 08/12/20
 * Time: 15.00
 */
@Entity
@Table(name = "timeTables")
@Data
@NoArgsConstructor
public class TimeTableEntity {

    /**
     * Id field [GENERATED AUTOMATICALLY].
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Day when course hold.
     */
    @Column(name = "day", nullable = false)
    private int day;

    /**
     * Time when course hold.
     */
    @Column(name = "lesson", nullable = false)
    private int lessonNumber;
    /**
     * The course.
     */
    @ManyToOne
    private Course course;
    /**
     * The class.
     */
    @ManyToOne
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;
    /**
     * User object to room. This property connects the room to
     * the lesson.
     */
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    /**
     * Constructor to make a new instance.
     *
     * @param day          Day when course hold.
     * @param lessonNumber Time when course hold.
     * @param course       The course.
     * @param classroom    The class.
     */
    public TimeTableEntity(int day, int lessonNumber, Room room, Course course, Classroom classroom) {
        this.day = day;
        this.lessonNumber = lessonNumber;
        this.room = room;
        this.course = course;
        this.classroom = classroom;
    }
}