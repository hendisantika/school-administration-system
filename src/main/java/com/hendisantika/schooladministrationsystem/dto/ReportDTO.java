package com.hendisantika.schooladministrationsystem.dto;

import com.hendisantika.schooladministrationsystem.entity.user.group.Student;
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
 * Time: 08.18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportDTO {
    private Student student;
    private int mark;

    public ReportDTO(Student student) {
        this.student = student;
        this.mark = 0;
    }
}
