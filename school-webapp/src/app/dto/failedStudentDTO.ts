import {Student} from '../model/student';
import {Course} from '../model/course';

export class FailedStudentDTO {

  student: Student;
  courses: Course[];
}
