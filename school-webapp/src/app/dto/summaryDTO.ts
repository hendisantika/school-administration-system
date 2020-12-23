import {Exam} from '../model/exam';

export class SummaryDTO {

  courseName: string;
  exams: Exam[];
  average: number;
}
