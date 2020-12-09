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
import java.time.LocalDateTime;

/**
 * Created by IntelliJ IDEA.
 * Project : school-administration-system
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 09/12/20
 * Time: 08.09
 */
@Entity
@Table(name = "remarks")
@Data
@NoArgsConstructor
public class Remark {

    /**
     * Id field [GENERATED AUTOMATICALLY].
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Message to student.
     */
    @Column(name = "text", nullable = false, length = 255)
    private String text;

    /**
     * remark created at this time.
     */
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    /**
     * Student who get the remark.
     */
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    /**
     * Constructor to make a new instance.
     *
     * @param text    Message to student.
     * @param student Student who get the remark.
     */
    public Remark(String text, Student student) {
        this.text = text;
        this.student = student;
    }
}