package com.hendisantika.schooladministrationsystem.repository;

import com.hendisantika.schooladministrationsystem.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * Project : school-administration-system
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 10/12/20
 * Time: 06.03
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

}