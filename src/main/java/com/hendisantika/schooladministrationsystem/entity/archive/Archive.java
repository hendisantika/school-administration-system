package com.hendisantika.schooladministrationsystem.entity.archive;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : school-administration-system
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 09/12/20
 * Time: 07.58
 */
@Entity
@Table(name = "archives")
@Data
@NoArgsConstructor
public class Archive {

    /**
     * Id field [GENERATED AUTOMATICALLY].
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Student Login username.
     */
    @Column(name = "username", nullable = false)
    private String username;

    /**
     * Student full name.
     */
    @Column(name = "studentName", nullable = false)
    private String studentName;

    /**
     * Student date of birth.
     */
    @Column(name = "dob", nullable = false)
    private LocalDate dateOfBirth;
    /**
     * All reports to student.
     */
    @OneToMany(mappedBy = "archive", cascade = CascadeType.ALL)
    private List<ArchiveReport> reports;

    /**
     * Constructor to make a new instance.
     *
     * @param username    Student login username.
     * @param studentName Student full name.
     * @param dateOfBirth Student date of birth.
     */
    public Archive(String username, String studentName, LocalDate dateOfBirth) {
        this.username = username;
        this.studentName = studentName;
        this.dateOfBirth = dateOfBirth;
    }

}