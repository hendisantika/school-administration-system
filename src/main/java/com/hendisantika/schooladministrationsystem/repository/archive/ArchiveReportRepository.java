package com.hendisantika.schooladministrationsystem.repository.archive;

import com.hendisantika.schooladministrationsystem.entity.archive.ArchiveReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * Project : school-administration-system
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/12/20
 * Time: 05.39
 */
@Repository
public interface ArchiveReportRepository extends JpaRepository<ArchiveReport, Long> {
}