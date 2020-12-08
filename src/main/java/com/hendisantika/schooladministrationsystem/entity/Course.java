package com.hendisantika.schooladministrationsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : school-administration-system
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 08/12/20
 * Time: 15.03
 */

@Entity
@Table(name = "courses")
@Data
@NoArgsConstructor
public class Course {

    /**
     * Id field [GENERATED AUTOMATICALLY].
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Subject title.
     */
    @Column(name = "title", nullable = false, length = 24)
    private String title;

    /**
     * Subject year.
     */
    @Column(name = "year", nullable = false)
    private int year;

    /**
     * Empty constructor.
     */
    public Course() {}

    /**
     * Constructor to make a new instance.
     *
     * @param title Subject title.
     * @param year Subject year.
     * @param teacher Teacher who teach this subject.
     */
    public Course(String title, int year, Teacher teacher) {
        this.title = title;
        this.year = year;
        this.teacher = teacher;
    }

    /**
     * Teacher who teach this subject.
     */
    @ManyToOne
    @JoinColumn(name="teacher_id")
    private Teacher teacher;

    /**
     * Student who learn this subject.
     */
    @JsonIgnore
    @ManyToMany(mappedBy = "courses", fetch = FetchType.EAGER)
    private List<Student> students = new ArrayList<>();

    /**
     * Exams that written by students.
     */
    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private List<Exam> exams = new ArrayList<>();

    /**
     * Course time in Timetable.
     */
    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private List<TimeTableEntity> lessons = new ArrayList<>();

    /**
     * Reports from this subject.
     */
    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private List<Report> reports = new ArrayList<>();
}