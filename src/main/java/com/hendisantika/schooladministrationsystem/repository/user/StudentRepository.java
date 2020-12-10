package com.hendisantika.schooladministrationsystem.repository.user;

import com.hendisantika.schooladministrationsystem.entity.user.group.Student;
import org.springframework.data.jpa.repository.JpaRepository;
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
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Transactional
    void deleteById(Long id);
}