package com.hendisantika.schooladministrationsystem.entity;

import com.hendisantika.schooladministrationsystem.entity.user.group.Student;
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
import java.time.LocalDate;

/**
 * Created by IntelliJ IDEA.
 * Project : school-administration-system
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 08/12/20
 * Time: 15.13
 */
@Entity
@Table(name = "attendances")
@Data
@NoArgsConstructor
public class Attendance {

    /**
     * Id field [GENERATED AUTOMATICALLY].
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Lecture when student missed.
     */
    @Column(name = "lecture", nullable = false)
    private int lecture;

    /**
     * Date when student missed.
     */
    @Column(name = "dateOfMiss", nullable = false)
    private LocalDate dateOfMiss;

    /**
     * Attendance is verified or not.
     */
    @Column(name = "isVerified")
    private boolean isVerified;

    /**
     * Constructor to make a new instance.
     *
     * @param student Student who missed the lecture.
     * @param lecture Lecture when student missed.
     * @param dateOfMiss Date when student missed.
     */
    public Attendance(Student student, int lecture, LocalDate dateOfMiss) {
        this.student = student;
        this.lecture = lecture;
        this.dateOfMiss = dateOfMiss;
        this.isVerified = false;
    }

    /**
     * Student who missed the lecture.
     */
    @ManyToOne
    @JoinColumn(name="student_id", nullable = false)
    private Student student;
}