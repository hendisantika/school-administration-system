package com.hendisantika.schooladministrationsystem.service;

import com.hendisantika.schooladministrationsystem.dto.response.RemarkResponseDTO;
import com.hendisantika.schooladministrationsystem.entity.Remark;
import com.hendisantika.schooladministrationsystem.entity.user.group.Student;
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

    /**
     * Returns a Remark object by id, if course exist
     * or returns a null value.
     *
     * @param id Id of the Remark.
     * @return a Remark object by id.
     * @see Remark
     */
    public Remark findById(Long id) {
        return remarkRepository.getOne(id);
    }

    /**
     * Creates a new remark and save into the database.
     *
     * @param remarkResponseDTO Submitted DTO from web application.
     * @return a new Remark object.
     * @see Remark
     */
    public Remark create(RemarkResponseDTO remarkResponseDTO) {
        /* Finds student by id. */
        Student student = studentRepository.getOne(remarkResponseDTO.getStudentId());
        return remarkRepository.save(new Remark(
                remarkResponseDTO.getText(),
                student));
    }

    /**
     * Updates a remark from database by id.
     *
     * @param id                Id of the remark.
     * @param remarkResponseDTO Submitted DTO from web application.
     * @return an updated remark.
     * @see Remark
     */
    public Remark update(Long id, RemarkResponseDTO remarkResponseDTO) {
        Remark remark = remarkRepository.getOne(id);
        remark.setText(remarkResponseDTO.getText());
        return remarkRepository.save(remark);
    }
}
