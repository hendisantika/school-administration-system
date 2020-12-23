import {Injectable} from '@angular/core';
import {ApiService} from './api.service';
import {ConfigService} from './config.service';

@Injectable({
  providedIn: 'root'
})
export class HeadTeacherService {

  constructor(private apiService: ApiService, private configService: ConfigService) {

  }

  findFailedStudentsInClass(classroom_id: number) {
    return this.apiService.get(this.configService.getFindFailedStudentsInClassUrl + '/' + classroom_id);
  }

  showResultByCourse(classroom_id: number) {
    return this.apiService.get(this.configService.getShowResultByCourseUrl + '/' + classroom_id);
  }
}
