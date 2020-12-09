package com.hendisantika.schooladministrationsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hendisantika.schooladministrationsystem.entity.user.group.Student;
import com.hendisantika.schooladministrationsystem.entity.user.group.Teacher;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
 * Time: 15.09
 */
@Entity
@Table(name = "classrooms")
@Data
@NoArgsConstructor
public class Classroom {

    /**
     * Id field [GENERATED AUTOMATICALLY].
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Year when class started.
     */
    @Column(name = "start_year", nullable = false, length = 4)
    private int start_year;

    /**
     * Year when class will end.
     */
    @Column(name = "end_year", nullable = false, length = 4)
    private int end_year;

    /**
     * Current school year.
     */
    @Column(name = "year", nullable = false)
    private int year;

    /**
     * Class letter to identify the class in the school.
     */
    @Column(name = "letter", nullable = false, length = 1)
    private char letter;
    /**
     * The headteacher of the class.
     */
    @OneToOne
    private Teacher headTeacher;
    /**
     * Students who are taught together at school.
     */
    @JsonIgnore
    @OneToMany(mappedBy = "classroom")
    private List<Student> students = new ArrayList<>();
    /**
     * Classroom Timetable.
     */
    @JsonIgnore
    @OneToMany(mappedBy = "classroom")
    private List<TimeTableEntity> timeTableEntities = new ArrayList<>();

    /**
     * Constructor to make a new instance.
     *
     * @param start_year  Year when class will end.
     * @param end_year    Year when class will end.
     * @param year        Current school year.
     * @param letter      Class letter to identify the class in the school.
     * @param headTeacher The headteacher of the class.
     */
    public Classroom(int start_year, int end_year, int year, char letter, Teacher headTeacher) {
        this.start_year = start_year;
        this.end_year = end_year;
        this.year = year;
        this.letter = letter;
        this.headTeacher = headTeacher;
    }
}
