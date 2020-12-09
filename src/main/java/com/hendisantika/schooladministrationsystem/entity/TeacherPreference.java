package com.hendisantika.schooladministrationsystem.entity;

import com.hendisantika.schooladministrationsystem.entity.user.group.Teacher;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Created by IntelliJ IDEA.
 * Project : school-administration-system
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 09/12/20
 * Time: 08.10
 */
@Entity
@Table(name = "teacher_preferences")
@Data
@NoArgsConstructor
public class TeacherPreference {

    /**
     * Id field [GENERATED AUTOMATICALLY].
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Teacher who own the preferences.
     */
    @OneToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    /**
     * Weight of the test.
     */
    private double testWeight;

    /**
     * Weight of the topic test.
     */
    private double topicTestWeight;

    /**
     * Weight of the repetition.
     */
    private double repetitionWeight;

    /**
     * Weight of the homework.
     */
    private double homeworkWeight;

    /**
     * Constructor to make a new instance.
     *
     * @param teacher          Teacher who own the preferences.
     * @param testWeight       Weight of the test.
     * @param topicTestWeight  Weight of the topic test.
     * @param repetitionWeight Weight of the repetition.
     * @param homeworkWeight   Weight of the homework.
     */
    public TeacherPreference(Teacher teacher, double testWeight, double topicTestWeight, double repetitionWeight,
                             double homeworkWeight) {
        this.teacher = teacher;
        this.testWeight = testWeight;
        this.topicTestWeight = topicTestWeight;
        this.repetitionWeight = repetitionWeight;
        this.homeworkWeight = homeworkWeight;
    }

}
