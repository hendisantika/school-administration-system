package com.hendisantika.schooladministrationsystem.repository.user;

import com.hendisantika.schooladministrationsystem.entity.user.group.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
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
 * Date: 11/12/20
 * Time: 05.38
 */

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    @Query(value = "SELECT teacher_id FROM teachers WHERE :teacher_id = id LIMIT 1", nativeQuery = true)
    @Transactional
    Long GetUserIdByTeacherId(@Param("teacher_id") Long teacher_id);
}