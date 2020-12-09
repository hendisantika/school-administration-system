package com.hendisantika.schooladministrationsystem.dto;

import com.hendisantika.schooladministrationsystem.entity.user.group.Student;
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
 * Date: 09/12/20
 * Time: 08.15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamDTO {
    private Student student;

    private int mark;

    private LocalDate writtenAt;

    private String examType;

    public ExamDTO(Student student, LocalDate writtenAt, String examType) {
        this.student = student;
        this.mark = 0;
        this.writtenAt = writtenAt;
    }
}
