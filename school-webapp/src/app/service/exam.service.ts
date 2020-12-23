import {Injectable} from '@angular/core';
import {ApiService} from './api.service';
import {ConfigService} from './config.service';
import {ExamResponseDTO} from '../dto/response/examResponseDTO';

@Injectable({
  providedIn: 'root'
})
export class ExamService {

  constructor(private apiService: ApiService, private configService: ConfigService) {

  }

  findAllByStudent(student_id: number, course_id: number) {
    return this.apiService.post(this.configService.getExamfindAllByStudentUrl + '/' + student_id, course_id);
  }

  findById(id: number) {
    return this.apiService.get(this.configService.getFindExamByIdUrl + '/' + id);
  }

  create(exam: ExamResponseDTO) {
    return this.apiService.post(this.configService.getCreateExamUrl, exam);
  }

  update(id: number, exam: ExamResponseDTO) {
    return this.apiService.put(this.configService.getUpdateExamUrl + '/' + id, exam);
  }

  delete(id: number) {
    return this.apiService.delete(this.configService.getDeleteExamUrl + '/' + id, id);
  }

  makeExamsFormToClassroom(classroom_id: number, written_at: string, etype: string) {
    return this.apiService.post(this.configService.getMakeExamsFormToClassroomUrl + '/' + classroom_id + '/' + etype, written_at);
  }

  createExamsFromForm(examResponseDTOS: ExamResponseDTO[]) {
    return this.apiService.post(this.configService.getCreateExamsFromFormUrl, examResponseDTOS);
  }

  getAllExamType() {
    return this.apiService.get(this.configService.getGetAllExamTypeUrl);
  }
}
