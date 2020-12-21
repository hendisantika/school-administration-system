import {User} from './user';
import {Classroom} from './classroom';

export class Student {

  id: number;
  student: User;
  dateOfBirth: string;
  start_year: number;
  address: string;
  gender: string;
  educationId: string;
  healthCareId: string;
  parent1Name: string;
  parent2Name: string;
  parent1Phone: string;
  parent2Phone: string;
  classroom: Classroom;
}
