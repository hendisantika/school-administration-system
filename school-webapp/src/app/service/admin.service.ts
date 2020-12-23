import {Injectable} from '@angular/core';
import {ApiService} from './api.service';
import {ConfigService} from './config.service';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private apiService: ApiService, private configService: ConfigService) {

  }

  newYear() {
    return this.apiService.get(this.configService.getNewYearUrl);
  }

  createArchive() {
    return this.apiService.get(this.configService.getCreateArchiveUrl);
  }

  getArchive() {
    return this.apiService.get(this.configService.getGetArchiveUrl);
  }

  finished(classroom_id: number) {
    return this.apiService.get(this.configService.getFinishedUrl + '/' + classroom_id);
  }

  getArchiveById(id: number) {
    return this.apiService.get(this.configService.getGetArchiveByIdUrl + '/' + id);
  }

}
