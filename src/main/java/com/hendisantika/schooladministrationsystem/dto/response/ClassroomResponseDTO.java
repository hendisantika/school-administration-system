package com.hendisantika.schooladministrationsystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 * Project : school-administration-system
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 10/12/20
 * Time: 05.38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassroomResponseDTO {
    private int startYear;

    private int endYear;

    private int year;

    private char letter;

    private Long headTeacherId;
}
