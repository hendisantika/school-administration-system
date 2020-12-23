import {Injectable} from '@angular/core';
import {ApiService} from './api.service';
import {ConfigService} from './config.service';
import {MessageResponseDTO} from '../dto/response/MEssageResponseDTO';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  constructor(private apiService: ApiService, private configService: ConfigService) {

  }

  findAll() {
    return this.apiService.get(this.configService.getFindAllMessagesUrl);
  }

  findById(id: number) {
    return this.apiService.get(this.configService.getFindByMessageIdUrl + '/' + id);
  }

  create(message: MessageResponseDTO) {
    return this.apiService.post(this.configService.getCreateMessageUrl, message);
  }

  update(id: number, message: MessageResponseDTO) {
    return this.apiService.put(this.configService.getUpdateMessageUrl + '/' + id, message);
  }

  delete(id: number) {
    return this.apiService.delete(this.configService.getDeleteMessageUrl + '/' + id, id);
  }
}
