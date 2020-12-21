import {Student} from '../model/student';

export class ExamDTO {

  student: Student;
  mark: number;
  written_at: string;
  examType: string;
}
