package com.hendisantika.schooladministrationsystem.entity;

import com.hendisantika.schooladministrationsystem.entity.user.group.Student;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by IntelliJ IDEA.
 * Project : school-administration-system
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 08/12/20
 * Time: 15.12
 */
@Entity
@Table(name = "exams")
@Data
@NoArgsConstructor
public class Exam {

    /**
     * Id field [GENERATED AUTOMATICALLY].
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Exam result.
     */
    @Column(name = "mark", nullable = false)
    private int mark;

    /**
     * Time when exam written.
     */
    @Column(name = "written_at", nullable = false)
    private LocalDate writtenAt;

    /**
     * Role name.
     */
    @Enumerated(EnumType.STRING)
    @Column(name="exam_type")
    private ExamType examType;

    /**
     * Constructor to make a new instance.
     *
     * @param mark Exam result.
     * @param writtenAt Time when exam written.
     * @param examType Type of the exam.
     * @param course Course where student wrote the exam.
     * @param student Student who wrote the exam.
     */
    public Exam(int mark, LocalDate writtenAt, ExamType examType, Course course, Student student) {
        this.mark = mark;
        this.writtenAt = writtenAt;
        this.examType = examType;
        this.course = course;
        this.student = student;
    }

    /**
     * Course where student wrote the exam.
     */
    @ManyToOne
    @JoinColumn(name="course_id")
    private Course course;

    /**
     * Student who wrote the exam.
     */
    @ManyToOne
    @JoinColumn(name="student_id")
    private Student student;
}