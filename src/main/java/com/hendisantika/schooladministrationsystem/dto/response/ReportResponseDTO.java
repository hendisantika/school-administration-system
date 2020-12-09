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
 * Time: 05.43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportResponseDTO {
    private int year;

    private int semester;

    private int mark;

    private Long studentId;

    private Long courseId;
}
