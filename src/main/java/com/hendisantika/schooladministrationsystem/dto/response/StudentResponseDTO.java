package com.hendisantika.schooladministrationsystem.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
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
 * Time: 05.44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponseDTO {
    private String username;

    private LocalDate dateOfBirth;

    private int startYear;

    private String address;

    private String gender;

    private String educationId;

    private String healthCareId;

    private String parent1Name;

    private String parent2Name;

    private String parent1Phone;

    private String parent2Phone;

    @JsonProperty("classroom_id")
    private Long classroomId;
}
