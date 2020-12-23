import {Injectable} from '@angular/core';

import {ApiService} from './api.service';

import {ConfigService} from './config.service';
import {RoomResponseDTO} from '../dto/response/roomResponseDTO';

@Injectable({
  providedIn: 'root'
})
export class RoomService {

  constructor(private apiService: ApiService, private configService: ConfigService) {

  }

  findAll() {
    return this.apiService.get(this.configService.getFindAllRoomsUrl);
  }

  findById(id: number) {
    return this.apiService.get(this.configService.getFindByRoomUrl + '/' + id);
  }

  create(room: RoomResponseDTO) {
    return this.apiService.post(this.configService.getCreateRoomUrl, room);
  }

  delete(id: number) {
    return this.apiService.delete(this.configService.getDeleteRoomUrl + '/' + id, id);
  }
}
