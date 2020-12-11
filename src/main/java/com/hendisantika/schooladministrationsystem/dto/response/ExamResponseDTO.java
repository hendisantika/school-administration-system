package com.hendisantika.schooladministrationsystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Created by IntelliJ IDEA.
 * Project : school-administration-system
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/12/20
 * Time: 06.02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamResponseDTO {
    private int mark;

    private LocalDate writtenAt;

    private String examType;

    private Long courseId;

    private Long studentId;
}
