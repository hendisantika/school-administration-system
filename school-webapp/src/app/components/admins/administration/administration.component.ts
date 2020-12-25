import {Component, OnInit} from '@angular/core';
import {UserService} from 'src/app/service/user.service';
import {AdminService} from 'src/app/service/admin.service';
import {Router} from '@angular/router';
import {MatSnackBar} from '@angular/material/snack-bar';
import {isAdmin} from 'src/app/shared/roles';

@Component({
  selector: 'app-administration',
  templateUrl: './administration.component.html',
  styleUrls: ['./administration.component.scss']
})
export class AdministrationComponent implements OnInit {

  currentUser: any = {};
  isDataAvailable: boolean = false;

  constructor(private userService: UserService, private adminService: AdminService,
              private _snackBar: MatSnackBar, private router: Router) {
  }

  ngOnInit() {
    this.userService.getMyInfo().toPromise().then(data => {
      this.currentUser = data;
      this.isDataAvailable = true;
    });
  }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action, {
      duration: 2000,
    });
  }

  create() {
    this.adminService.createArchive().subscribe(data => {
      this.openSnackBar('Archive created.', 'Ok');
    }, error => {
      this.openSnackBar('Failed.', 'Ok');
    });
  }

  newYear() {
    this.adminService.newYear().subscribe(data => {
      this.openSnackBar('New Year Started!', 'Ok');
    }, error => {
      this.openSnackBar('Failed.', 'Ok');
    });
  }

  userRole() {
    return isAdmin(this.currentUser, this.router);
  }
}
