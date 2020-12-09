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

/**
 * Created by IntelliJ IDEA.
 * Project : school-administration-system
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 08/12/20
 * Time: 15.10
 */
@Entity
@Table(name = "reports")
@Data
@NoArgsConstructor
public class Report {

    /**
     * Id field [GENERATED AUTOMATICALLY].
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * School year when semester was.
     */
    @Column(name = "year", nullable = false)
    private int year;

    /**
     * The semester.
     */
    @Column(name = "semester", nullable = false)
    private int semester;

    /**
     * Course result in the semester.
     */
    @Column(name = "mark", nullable = false)
    private int mark;
    /**
     * Student who owe the result.
     */
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
    /**
     * Report which belongs to this course.
     */
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    /**
     * Constructor to make a new instance.
     *
     * @param student  Student who owe the result.
     * @param year     School year when semester was.
     * @param semester The semester.
     * @param course   The course.
     * @param mark     Course result in the semester.
     */
    public Report(Student student, int year, int semester, Course course, int mark) {
        this.student = student;
        this.year = year;
        this.semester = semester;
        this.course = course;
        this.mark = mark;
    }
}
