package com.hendisantika.schooladministrationsystem.repository;

import com.hendisantika.schooladministrationsystem.entity.TeacherPreference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by IntelliJ IDEA.
 * Project : school-administration-system
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 10/12/20
 * Time: 06.04
 */
@Repository
public interface TeacherPreferenceRepository extends JpaRepository<TeacherPreference, Long> {

    @Modifying
    @Query(value = "UPDATE teacher_preferences SET homework_weight = :homework, repetition_weight = :repetition, " +
            "test_weight = :test, topic_test_weight = :topicTest  WHERE teacher_id = :teacher_id", nativeQuery = true)
    @Transactional
    void setPreferences(@Param("teacher_id") Long teacher_id,
                        @Param("test") double test,
                        @Param("topicTest") double topicTest,
                        @Param("repetition") double repetition,
                        @Param("homework") double homework);
}