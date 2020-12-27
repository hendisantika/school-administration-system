import {Component, OnInit} from '@angular/core';
import {UserResponseDTO} from 'src/app/dto/response/userResponseDTO';
import {TeacherResponseDTO} from 'src/app/dto/response/teacherResponseDTO';
import {UserService} from 'src/app/service/user.service';
import {Router} from '@angular/router';
import {TeacherService} from 'src/app/service/teacher.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {isAdmin} from 'src/app/shared/roles';

@Component({
  selector: 'app-teacher-create',
  templateUrl: './teacher-create.component.html',
  styleUrls: ['./teacher-create.component.scss']
})
export class TeacherCreateComponent implements OnInit {

  user = new UserResponseDTO();
  teacher = new TeacherResponseDTO();
  currentUser: any = {};
  userSubmitted: boolean = false;
  isDataAvailable: boolean = false;
  selectedOption: any = {};

  constructor(private userService: UserService, private router: Router,
              private teacherService: TeacherService, private _snackBar: MatSnackBar) {
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

  onUserSubmit() {
    this.user.role = 'ROLE_TEACHER';
    this.teacher.username = this.user.username;
    this.userService.create(this.user).subscribe(() => {
      this.openSnackBar('Teacher Created.', 'Ok');
    }, error => {
      this.openSnackBar('Failed.', 'Ok');
    });
    this.userSubmitted = true;
  }

  onTeacherSubmit() {
    this.teacherService.create(this.teacher).subscribe(() => {

    }, error => {
      this.openSnackBar('Failed.', 'Ok');
    });
    this.refresh();
    this.openSnackBar('Teacher created', 'Ok')
  }

  refresh() {
    this.userSubmitted = false;
    this.user = new UserResponseDTO();
    this.teacher = new TeacherResponseDTO();
    this.selectedOption = {};
  }

  goBack() {
    this.router.navigate(['/user/all']);
  }

  userRole() {
    if (isAdmin(this.currentUser, this.router)) {
      return true;
    } else {
      this.router.navigate(['403']);
    }
  }
}
