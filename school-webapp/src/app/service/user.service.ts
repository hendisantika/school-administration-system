import {Injectable} from '@angular/core';
import {ApiService} from './api.service';
import {ConfigService} from './config.service';
import {map} from 'rxjs/operators';
import {UserResponseDTO} from '../dto/response/userResponseDTO';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  currentUser;

  constructor(private apiService: ApiService, private configService: ConfigService) {

  }

  initUser() {
    const PROMISE = this.apiService.get(this.configService.getRefreshTokenUrl)
      .toPromise()
      .then(response => {
        if (response.access_token !== null) {
          return this.getMyInfo()
            .toPromise()
            .then(user => {
              this.currentUser = user;
            })
        }
      }).catch(() => null);
    return PROMISE;
  }

  getMyInfo() {
    return this.apiService.get(this.configService.getWhoami)
      .pipe(map(user => this.currentUser = user));
  }

  getAll() {
    return this.apiService.get(this.configService.getAllUsersUrl);
  }

  getById(id: number) {
    return this.apiService.get(this.configService.getGetUserByIdUrl + '/' + id);
  }

  create(user: UserResponseDTO) {
    return this.apiService.post(this.configService.getCreateUrl, user);
  }

  update(id: number, user: UserResponseDTO) {
    return this.apiService.put(this.configService.getUpdateUserUrl + '/' + id, user);
  }

  delete(id: number) {
    return this.apiService.delete(this.configService.getDeleteUrl + '/' + id, id);
  }

  isUsernameUnique(username: string) {
    return this.apiService.get(this.configService.getIsUsernameUniqueUrl + '/' + username);
  }
}
