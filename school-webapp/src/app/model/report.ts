import {Student} from './student';
import {Course} from './course';

export class Report {

  id: number;
  student: Student;
  year: number;
  semester: number;
  course: Course;
  mark: number;
}
