package com.hendisantika.schooladministrationsystem.dto;

import com.hendisantika.schooladministrationsystem.entity.user.group.Student;
import lombok.Data;

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
public class AttendanceDTO {
    private Student student;
    private boolean isMiss;

    public AttendanceDTO(Student student) {
        this.student = student;
        this.isMiss = false;
    }
}
