package com.hendisantika.schooladministrationsystem.security;

import com.hendisantika.schooladministrationsystem.entity.user.group.Student;
import com.hendisantika.schooladministrationsystem.repository.user.StudentRepository;
import com.hendisantika.schooladministrationsystem.repository.user.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * Project : school-administration-system
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 16/12/20
 * Time: 05.22
 */
@Service
public class SecurityService {

        @Autowired
        private StudentRepository studentRepository;

        @Autowired
        private TeacherRepository teacherRepository;

        public boolean hasStudentAccess(Long currentUserId, Long studentId) {
                Student student = studentRepository.getOne(studentId);
                return student.getStudent().getId().equals(currentUserId);
        }
}
