package com.hendisantika.schooladministrationsystem.dto;

import com.hendisantika.schooladministrationsystem.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 * Project : school-administration-system
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 09/12/20
 * Time: 08.14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassroomCourseResultDTO {
    private Course course;

    private double result;
}
