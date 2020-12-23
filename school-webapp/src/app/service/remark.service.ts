import {Injectable} from '@angular/core';
import {ApiService} from './api.service';
import {ConfigService} from './config.service';
import {RemarkResponseDTO} from '../dto/response/RemarkResponseDTO';

@Injectable({
  providedIn: 'root'
})
export class RemarkService {

  constructor(private apiService: ApiService, private configService: ConfigService) {

  }

  findAll(student_id: number) {
    return this.apiService.get(this.configService.getFindAllRemarksUrl + '/' + student_id);
  }

  findById(id: number) {
    return this.apiService.get(this.configService.getFindByRemarkIdUrl + '/' + id);
  }

  create(remark: RemarkResponseDTO) {
    return this.apiService.post(this.configService.getCreateRemarkUrl, remark);
  }

  update(id: number, remark: RemarkResponseDTO) {
    return this.apiService.put(this.configService.getUpdateRemarkUrl + '/' + id, remark);
  }

  delete(id: number) {
    return this.apiService.delete(this.configService.getDeleteRemarkUrl + '/' + id, id);
  }
}
