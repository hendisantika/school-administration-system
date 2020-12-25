import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {UserService} from 'src/app/service/user.service';
import {AuthService} from 'src/app/service/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  user: any;
  role: string;
  isDataAvailable: boolean = false;

  constructor(private router: Router, private userService: UserService,
              private authService: AuthService) {
  }

  ngOnInit() {
    this.isDataAvailable = true
  }

  home() {
    this.router.navigate(["/home"]);
  }

  logout() {
    this.authService.logout().subscribe(res => {
      this.router.navigate(["/login"]);
    });
  }

  login() {
    this.router.navigate(["/login"]);
  }

  hasSignedIn() {
    return !!this.userService.currentUser;
  }

}
