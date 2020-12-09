package com.hendisantika.schooladministrationsystem.repository;

import com.hendisantika.schooladministrationsystem.entity.Course;
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
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query(value = "SELECT COUNT(*) FROM student_course WHERE student_id=:student_id AND course_id=:course_id",
            nativeQuery = true)
    @Transactional
    int courseIsAlreadyTaken(@Param("student_id") Long student_id, @Param("course_id") Long course_id);

    @Modifying
    @Query(value = "DELETE FROM student_course WHERE course_id=:course_id", nativeQuery = true)
    @Transactional
    void deleteFromStudentCourse(@Param("course_id") Long course_id);

    @Modifying
    @Query(value = "DELETE FROM student_course WHERE course_id=:course_id ad student_id=:student_id", nativeQuery =
            true)
    @Transactional
    void unsetStudentFromCourse(@Param("course_id") Long course_id, @Param("student_id") Long student_id);

    @Modifying
    @Query(value = "UPDATE courses SET teacher_id = :teacher_id WHERE id = :course_id", nativeQuery = true)
    @Transactional
    void setTeacher(@Param("course_id") Long course_id, @Param("teacher_id") Long teacher_id);
}