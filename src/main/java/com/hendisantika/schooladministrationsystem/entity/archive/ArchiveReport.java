package com.hendisantika.schooladministrationsystem.entity.archive;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

/**
 * Created by IntelliJ IDEA.
 * Project : school-administration-system
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 09/12/20
 * Time: 07.59
 */
@Entity
@Table(name = "archiveReports")
@Data
@NoArgsConstructor
public class ArchiveReport {

    /**
     * Id field [GENERATED AUTOMATICALLY].
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Course name.
     */
    @Column(name = "courseName", nullable = false)
    private String courseName;

    /**
     * Yeaar when course happens.
     */
    @Column(name = "year", nullable = false)
    private int year;

    /**
     * Final result at the end.
     */
    @Column(name = "mark", nullable = false)
    private int mark;
    /**
     * Connects the report to the student.
     */
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne
    @JoinColumn(name = "archive_id")
    private Archive archive;

    /**
     * Constructor to make a new instance.
     *
     * @param courseName Course name.
     * @param year       Year when course happens.
     * @param mark       Final result at the end.
     * @param archive    Student object to collect reports.
     */
    public ArchiveReport(String courseName, int year, int mark, Archive archive) {
        this.courseName = courseName;
        this.year = year;
        this.mark = mark;
        this.archive = archive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArchiveReport that = (ArchiveReport) o;
        return year == that.year &&
                mark == that.mark &&
                Objects.equals(courseName, that.courseName) &&
                Objects.equals(archive, that.archive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseName, year, mark, archive);
    }
}