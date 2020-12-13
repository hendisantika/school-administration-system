package com.hendisantika.schooladministrationsystem.service;

import com.hendisantika.schooladministrationsystem.entity.Message;
import com.hendisantika.schooladministrationsystem.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * Returns a List of Message.
     *
     * @return messages from database.
     */
    public List<Message> findAll() {
        return messageRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Message::getCreatedAt).reversed())
                .collect(Collectors.toList());
    }
}
