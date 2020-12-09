package com.hendisantika.schooladministrationsystem.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * Created by IntelliJ IDEA.
 * Project : school-administration-system
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 09/12/20
 * Time: 08.07
 */
@Entity
@Table(name = "messages")
@Data
@NoArgsConstructor
public class Message {

    /**
     * Id field [GENERATED AUTOMATICALLY].
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Message to Home page.
     */
    @Column(name = "text", nullable = false, length = 255)
    private String text;

    /**
     * Text created at this time.
     */
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    /**
     * Constructor to make a new instance.
     *
     * @param text Text.
     */
    public Message(String text) {
        this.text = text;
        this.createdAt = LocalDateTime.now();
    }
}
