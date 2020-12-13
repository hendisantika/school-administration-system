package com.hendisantika.schooladministrationsystem.service;

import com.hendisantika.schooladministrationsystem.dto.response.MessageResponseDTO;
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

    /**
     * Returns a Message object by id, if course exist
     * or returns a null value.
     *
     * @param id Id of the message.
     * @return a message object by id.
     * @see Message
     */
    public Message findById(Long id) {
        return messageRepository.getOne(id);
    }

    /**
     * Creates a new message and save into the database.
     *
     * @param messageResponseDTO Submitted DTO from web application.
     * @return a new Message object.
     * @see Message
     */
    public Message create(MessageResponseDTO messageResponseDTO) {
        return messageRepository.save(new Message(messageResponseDTO.getText()));
    }

    /**
     * Updates a message from database by id.
     *
     * @param id                 Id of the message.
     * @param messageResponseDTO Submitted DTO from web application.
     * @return an updated message.
     * @see Message
     */
    public Message update(Long id, MessageResponseDTO messageResponseDTO) {
        Message message = messageRepository.getOne(id);
        message.setText(messageResponseDTO.getText());
        return messageRepository.save(message);
    }
}
