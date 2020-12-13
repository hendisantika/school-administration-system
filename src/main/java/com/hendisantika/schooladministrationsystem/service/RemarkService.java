package com.hendisantika.schooladministrationsystem.service;

import com.hendisantika.schooladministrationsystem.entity.Remark;
import com.hendisantika.schooladministrationsystem.repository.RemarkRepository;
import com.hendisantika.schooladministrationsystem.repository.user.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * Project : school-administration-system
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 13/12/20
 * Time: 17.32
 */
@Service
public class RemarkService {

    @Autowired
    private RemarkRepository remarkRepository;

    @Autowired
    private StudentRepository studentRepository;

    /**
     * Returns a List of Remark by student id.
     *
     * @param student_id
     * @return Remarks from database by student id.
     */
    public List<Remark> findAllByStudent(Long student_id) {
        return remarkRepository.findAll()
                .stream()
                .filter(remark -> remark.getStudent().getId().equals(student_id))
                .collect(Collectors.toList());
    }
}
