package com.hendisantika.schooladministrationsystem.service;

import com.hendisantika.schooladministrationsystem.dto.response.TimeTableEntityResponseDTO;
import com.hendisantika.schooladministrationsystem.entity.Classroom;
import com.hendisantika.schooladministrationsystem.entity.Course;
import com.hendisantika.schooladministrationsystem.entity.Room;
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

import java.util.ArrayList;
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

    /**
     * Returns an TimeTableEntity Object by id.
     *
     * @param id Id of the TimeTableEntity
     * @return a TimeTableEntity object.
     */
    public TimeTableEntity findById(Long id) {
        return timeTableRepository.getOne(id);
    }

    /**
     * Creates a new TimeTableEntity and save into the database.
     *
     * @param timeTableEntityResponseDTO Submitted DTO from web application.
     * @return a new TimeTableEntity object.
     * @see TimeTableEntity
     */
    public TimeTableEntity create(TimeTableEntityResponseDTO timeTableEntityResponseDTO) {
        /* Finds classroom by id. */
        Classroom classroom = classroomRepository.getOne(timeTableEntityResponseDTO.getClassroomId());
        /* Finds course by id. */
        Course course = courseRepository.getOne(timeTableEntityResponseDTO.getCourseId());
        /* Finds room by id. */
        Room room = roomRepository.getOne(timeTableEntityResponseDTO.getRoomId());

        return timeTableRepository.save(new TimeTableEntity(
                timeTableEntityResponseDTO.getDay(),
                timeTableEntityResponseDTO.getLessonNumber(),
                room,
                course,
                classroom
        ));
    }

    /**
     * Updates a TimeTableEntity from database by id.
     *
     * @param id                         Id of the TimeTableEntity.
     * @param timeTableEntityResponseDTO Submitted DTO from web application.
     * @return an updated TimeTableEntity.
     * @see TimeTableEntity
     */
    public TimeTableEntity update(Long id, TimeTableEntityResponseDTO timeTableEntityResponseDTO) {
        /* Finds timeTableEntity by id. */
        TimeTableEntity tableEntity = timeTableRepository.getOne(id);
        /* Finds classroom by id. */
        Classroom classroom = classroomRepository.getOne(timeTableEntityResponseDTO.getClassroomId());
        /* Finds room by id. */
        Room room = roomRepository.getOne(timeTableEntityResponseDTO.getRoomId());

        tableEntity.setRoom(room);
        tableEntity.setDay(timeTableEntityResponseDTO.getDay());
        tableEntity.setLessonNumber(timeTableEntityResponseDTO.getLessonNumber());
        tableEntity.setClassroom(classroom);

        return timeTableRepository.save(tableEntity);
    }

    /**
     * Deletes a TimeTableEntity from database by id.
     *
     * @param id Id of the TimeTableEntity.
     */
    public void delete(Long id) {
        timeTableRepository.delete(timeTableRepository.getOne(id));
    }

    /**
     * Returns a List of Courses that Student has.
     *
     * @param id Id of the Student.
     * @return List of course.
     */
    private List<Course> getCourseByStudent(Long id) {
        List<Course> result = new ArrayList<>();
        for (Course course : courseRepository.findAll()) {
            for (Student student : course.getStudents()) {
                if (student.getId().equals(id)) {
                    result.add(course);
                }
            }
        }
        return result;
    }

    /**
     * Returns a List of TimeTableEntity. Each element
     * represents a lecture.
     *
     * @param id Id of the Student.
     * @return List of timeTableEntity.
     */
    private List<TimeTableEntity> getLessonsByStudent(Long id) {
        List<Course> courses = getCourseByStudent(id);
        List<TimeTableEntity> result = new ArrayList<>();
        for (TimeTableEntity tableEntity : timeTableRepository.findAll()) {
            for (Course course : courses) {
                if (tableEntity.getCourse().getId().equals(course.getId())) {
                    result.add(tableEntity);
                }
            }
        }
        return result;
    }

    /**
     * Returns a List of Courses that Teacher has.
     *
     * @param id Id of the Teacher.
     * @return List of course.
     */
    private List<Course> getCourseByTeacher(Long id) {
        List<Course> result = new ArrayList<>();
        for (Course course : courseRepository.findAll()) {
            if (course.getTeacher().getId().equals(id)) {
                result.add(course);
            }
        }
        return result;
    }

}
