import {Injectable} from '@angular/core';
import {ApiService} from './api.service';
import {ConfigService} from './config.service';
import {TeacherResponseDTO} from 'src/app/dto/response/teacherResponseDTO';
import {TeacherPreferenceResponseDTO} from '../dto/response/teacherPreferenceResponseDTO';

@Injectable({
  providedIn: 'root'
})
export class TeacherService {

  constructor(private apiService: ApiService, private configService: ConfigService) {

  }

  findById(id: number) {
    return this.apiService.get(this.configService.getFindTeacherById + '/' + id);
  }

  findByUserId(user_id: number) {
    return this.apiService.get(this.configService.getFindTeacherByUserId + '/' + user_id);
  }

  findAll() {
    return this.apiService.get(this.configService.getFindAllTeacherUrl);
  }

  create(teacher: TeacherResponseDTO) {
    return this.apiService.post(this.configService.getCreateTeacherUrl, teacher);
  }

  update(id: number, teacher: TeacherResponseDTO) {
    return this.apiService.put(this.configService.getUpdateTeacherUrl + '/' + id, teacher);
  }

  setCourse(teacher_id: number) {
    return this.apiService.put(this.configService.getSetCourseToTeacherUrl + '/' + teacher_id, teacher_id);
  }

  delete(id: number) {
    return this.apiService.delete(this.configService.getDeleteTeacherUrl + '/' + id, id);
  }

  setTeacherPreferences(teacherPreferenceResponseDTO: TeacherPreferenceResponseDTO) {
    return this.apiService.put(this.configService.getSetTeacherPreferencesUrl, teacherPreferenceResponseDTO);
  }

  getAllTeacherPreferences(teacher_id: number) {
    return this.apiService.get(this.configService.getGetAllTeacherPreferencesUrl + '/' + teacher_id, teacher_id);
  }
}
