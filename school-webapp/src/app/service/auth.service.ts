import {Injectable} from '@angular/core';
import {HttpHeaders} from '@angular/common/http';
import {ApiService} from './api.service';
import {UserService} from './user.service';
import {ConfigService} from './config.service';
import {map} from 'rxjs/operators';

@Injectable()
export class AuthService {

  constructor(private apiService: ApiService, private configService: ConfigService,
              private userService: UserService) {

  }

  login(user) {

    const LOGIN_HEADER = new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/x-www-form-urlencoded'
    });

    const BODY = `username=${user.username}&password=${user.password}`;

    return this.apiService.post(this.configService.getLoginUrl, BODY, LOGIN_HEADER)
      .pipe(map(() => {
        console.log('Login success');
        this.userService.getMyInfo().subscribe();
      }));
  }

  logout() {
    return this.apiService.post(this.configService.getLogoutUrl, {})
      .pipe(map(() => {
        this.userService.currentUser = null;
      }));
  }
}
