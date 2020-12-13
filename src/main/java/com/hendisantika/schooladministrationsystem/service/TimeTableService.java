package com.hendisantika.schooladministrationsystem.service;

import com.hendisantika.schooladministrationsystem.entity.TimeTableEntity;
import com.hendisantika.schooladministrationsystem.entity.user.group.Student;
import com.hendisantika.schooladministrationsystem.entity.user.group.Teacher;
import com.hendisantika.schooladministrationsystem.repository.ClassroomRepository;
import com.hendisantika.schooladministrationsystem.repository.CourseRepository;
import com.hendisantika.schooladministrationsystem.repository.RoomRepository;
import com.hendisantika.schooladministrationsystem.repository.TimeTableRepository;
import com.hendisantika.schooladministrationsystem.repository.user.StudentRepository;
import com.hendisantika.schooladministrationsystem.repository.user.TeacherRepository;
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
 * Date: 14/12/20
 * Time: 06.06
 */
@Service
public class TimeTableService {
    @Autowired
    private TimeTableRepository timeTableRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ClassroomRepository classroomRepository;

    @Autowired
    private RoomRepository roomRepository;

    /**
     * Returns a TimeTable 2d array to student. The table size
     * is 12x5. The y diagonal represents the days and x diagonal
     * shows the lecture.
     *
     * @param id Id of the Student.
     * @return a TimeTable 2d matrix.
     */
    public TimeTableEntity[][] getTimeTableByStudent(Long id) {
        TimeTableEntity[][] result = new TimeTableEntity[12][5];
        Student student = studentRepository.getOne(id);
        List<TimeTableEntity> timeTableEntities = getLessonsByStudent(student.getId());
        for (TimeTableEntity tableEntity : timeTableEntities) {
            result[tableEntity.getLessonNumber()][tableEntity.getDay()] = tableEntity;
        }
        return result;
    }

    /**
     * Returns a TimeTable 2d array to teacher. The table size
     * is 12x5. The y diagonal represents the days and x diagonal
     * shows the lecture.
     *
     * @param id Id of the Teacher.
     * @return a TimeTable 2d matrix.
     */
    public TimeTableEntity[][] getTimeTableByTeacher(Long id) {
        TimeTableEntity[][] result = new TimeTableEntity[12][5];
        Teacher teacher = teacherRepository.getOne(id);
        List<TimeTableEntity> timeTableEntities = getLessonsByTeacher(teacher.getId());
        for (TimeTableEntity tableEntity : timeTableEntities) {
            result[tableEntity.getLessonNumber()][tableEntity.getDay()] = tableEntity;
        }
        return result;
    }

    /**
     * Returns a List of TimeTableEntity, which are connected with a course.
     * The course get by id. If the course id not valid or a list empty returns null.
     *
     * @param course_id Id of the Course.
     * @return a List of the TimeTableEntity.
     */
    public List<TimeTableEntity> getTimeTableEntitiesByCourse(Long course_id) {
        return timeTableRepository.findAll()
                .stream()
                .filter(timeTableEntity -> timeTableEntity.getCourse().getId().equals(course_id))
                .collect(Collectors.toList());
    }

}
