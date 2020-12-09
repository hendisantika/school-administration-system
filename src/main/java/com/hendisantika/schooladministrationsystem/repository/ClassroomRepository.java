package com.hendisantika.schooladministrationsystem.repository;

import com.hendisantika.schooladministrationsystem.entity.Classroom;
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
 * Time: 06.01
 */
@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Long> {

    @Modifying
    @Query(value = "INSERT INTO student_course (student_id, course_id) VALUES (:student_id, :course_id)",
            nativeQuery = true)
    @Transactional
    void setCourseForClassroom(@Param("student_id") Long student_id, @Param("course_id") Long course_id);

    @Modifying
    @Query(value = "UPDATE user_authority SET authority_id = 4 WHERE user_id = :teacher_id", nativeQuery = true)
    @Transactional
    void setHeadteacherFromTeacher(@Param("teacher_id") Long teacher_id);

    @Modifying
    @Query(value = "UPDATE user_authority SET authority_id = 3 WHERE user_id = :teacher_id", nativeQuery = true)
    @Transactional
    void setTeacherFromHeadteacher(@Param("teacher_id") Long teacher_id);
}