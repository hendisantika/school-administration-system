import {Injectable} from '@angular/core';
import {ApiService} from './api.service';
import {ConfigService} from './config.service';
import {CourseResponseDTO} from '../dto/response/courseResponseDTO';

@Injectable({
  providedIn: 'root'
})
export class CourseService {

  constructor(private apiService: ApiService, private configService: ConfigService) {

  }

  findAll() {
    return this.apiService.get(this.configService.getGetAllCoursesUrl);
  }

  findById(id: number) {
    return this.apiService.get(this.configService.getGetCourseByIdUrl + '/' + id);
  }

  getCoursesByTeacherId(teacher_id: number) {
    return this.apiService.get(this.configService.getGetCoursesByTeacherIdUrl + '/' + teacher_id);
  }

  create(course: CourseResponseDTO) {
    return this.apiService.post(this.configService.getCreateCourseUrl, course);
  }

  update(id: number, course: CourseResponseDTO) {
    return this.apiService.put(this.configService.getUpdateCourseUrl + '/' + id, course);
  }

  delete(id: number) {
    return this.apiService.delete(this.configService.getDeleteCourseUrl + '/' + id, id);
  }

  setCourse(student_id: number, course_id: number) {
    return this.apiService.put(this.configService.getSetCourseToStudentUrl + '/' + student_id, course_id);
  }

  unsetCourse(student_id: number, course_id: number) {
    return this.apiService.put(this.configService.getUnsetCourseToStudentUrl + '/' + student_id, course_id);
  }
}
