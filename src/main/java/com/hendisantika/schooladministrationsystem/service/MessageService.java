package com.hendisantika.schooladministrationsystem.service;

import com.hendisantika.schooladministrationsystem.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * Project : school-administration-system
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 13/12/20
 * Time: 17.22
 */
@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

}
