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
 * Time: 05.47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeTableEntityResponseDTO {
    private int day;

    private int lessonNumber;

    private Long roomId;

    private Long courseId;

    private Long classroomId;
}
