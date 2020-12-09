package com.hendisantika.schooladministrationsystem.dto;

import com.hendisantika.schooladministrationsystem.entity.Exam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : school-administration-system
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 09/12/20
 * Time: 08.19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SummaryDTO {
    private String courseName;
    private List<Exam> exams;
    private double average;
}
