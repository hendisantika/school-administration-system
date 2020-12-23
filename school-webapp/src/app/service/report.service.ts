import {Injectable} from '@angular/core';
import {ApiService} from './api.service';
import {ConfigService} from './config.service';
import {ReportResponseDTO} from 'src/app/dto/response/reportResponseDTO';

@Injectable({
  providedIn: 'root'
})
export class ReportService {

  constructor(private apiService: ApiService, private configService: ConfigService) {

  }

  getSemesterResultByStudent(student_id: number, year: number, semester: number) {
    return this.apiService.post(this.configService.getGetSemesterResultByStudentUrl + '/' + student_id + '/' + year, semester);
  }

  findById(id: number) {
    return this.apiService.get(this.configService.getFindReportByIdUrl + '/' + id);
  }

  create(report: ReportResponseDTO) {
    return this.apiService.post(this.configService.getCreateReportUrl, report);
  }

  update(id: number, report: ReportResponseDTO) {
    return this.apiService.put(this.configService.getUpdateReportUrl + '/' + id, report);
  }

  delete(id: number) {
    return this.apiService.delete(this.configService.getDeleteReportUrl + '/' + id, id);
  }

  makeReportFormToClassroom(classroom_id: number) {
    return this.apiService.get(this.configService.getMakeReportFormToClassroomUrl + '/' + classroom_id);
  }

  createReportsToClassroom(reports: ReportResponseDTO[]) {
    return this.apiService.post(this.configService.getCreateReportsToClassroomUrl, reports);
  }
}
