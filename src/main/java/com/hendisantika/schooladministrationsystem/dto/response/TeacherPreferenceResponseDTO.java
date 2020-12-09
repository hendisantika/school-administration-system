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
 * Time: 05.45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherPreferenceResponseDTO {
    private Long teacherId;

    private double testWeight;

    private double topicTestWeight;

    private double repetitionWeight;

    private double homeworkWeight;
}
