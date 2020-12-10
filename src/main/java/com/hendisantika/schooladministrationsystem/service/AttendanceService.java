package com.hendisantika.schooladministrationsystem.service;

import com.hendisantika.schooladministrationsystem.repository.AttendanceRepository;
import com.hendisantika.schooladministrationsystem.repository.user.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * Project : school-administration-system
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/12/20
 * Time: 05.45
 */
@Service
public class AttendanceService {
    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private StudentRepository studentRepository;
}
