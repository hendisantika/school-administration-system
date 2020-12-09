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
 * Date: 10/12/20
 * Time: 05.37
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceResponseDTO {
    private Long studentId;

    private boolean miss;

    private int lesson;

    private LocalDate dateOfMiss;
}
