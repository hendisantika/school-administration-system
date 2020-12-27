import {Component, OnInit} from '@angular/core';
import {UserService} from 'src/app/service/user.service';
import {TeacherService} from 'src/app/service/teacher.service';
import {ActivatedRoute, Router} from '@angular/router';
import {StudentService} from 'src/app/service/student.service';
import {User} from 'src/app/model/user';
import {UserResponseDTO} from 'src/app/dto/response/userResponseDTO';
import {MatSnackBar} from '@angular/material/snack-bar';
import {isAdmin} from 'src/app/shared/roles';

@Component({
  selector: 'app-user-update',
  templateUrl: './user-update.component.html',
  styleUrls: ['./user-update.component.scss']
})
export class UserUpdateComponent implements OnInit {

  currentUser: any = {};
  isDataAvailable: boolean = false;
  id: number;
  user = new User();
  response = new UserResponseDTO();
  newPassword: string;
  reEnterPassword: string;

  constructor(private userService: UserService, private teacherService: TeacherService, private _snackBar: MatSnackBar,
              private router: Router, private route: ActivatedRoute, private studentService: StudentService) {
  }

  ngOnInit() {
    this.id = this.route.snapshot.params['id'];
    this.userService.getMyInfo().toPromise().then(data => {
      this.currentUser = data;
      this.userService.getById(this.id).subscribe(data => {
        this.user = data;
        this.isDataAvailable = true;
      });

    });
  }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action, {
      duration: 2000,
    });
  }

  onSubmit() {
    if (this.isDataChanged && this.isUsernameUnique && this.isPasswordMatch) {
      if (!this.response.fullName) this.response.fullName = this.user.fullName;
      if (!this.response.username) this.response.username = this.user.username;
      if (!this.newPassword) this.response.password = this.newPassword;
      this.userService.update(this.id, this.response).subscribe(() => {
        this.refresh();
        this.openSnackBar('User updated.', 'Ok');
      });
    }
  }

  refresh() {
    this.userService.getById(this.id).subscribe(data => {
      this.user = data;
    });
    this.response = new UserResponseDTO();
    this.newPassword = null;
    this.reEnterPassword = null;
  }

  isDataChanged() {
    if (!this.response.fullName
      || !this.response.username
      || !this.newPassword) return true;
    return false;
  }

  isPasswordMatch() {
    return this.newPassword === this.reEnterPassword;
  }

  isUsernameUnique() {
    var unique = false;
    this.userService.isUsernameUnique(this.response.username).subscribe(data => unique = data);
    return unique;
  }

  goBack() {
    this.userService.getById(this.id).subscribe(data => {
      if (data.authorities[0].authority + '' === 'ROLE_STUDENT') {
        this.studentService.findByUserId(this.id).subscribe(data => {
          this.router.navigate(['student/update', data.id]);

        });
      } else if (data.authorities[0].authority + '' === 'ROLE_TEACHER'
        || data.authorities[0].authority + '' === 'ROLE_HEADTEACHER') {
        this.teacherService.findByUserId(this.id).subscribe(data => {
          this.router.navigate(['teacher/update', data.id]);
        });
      }
    });
  }

  userRole() {
    if (isAdmin(this.currentUser, this.router) || this.currentUser.id === this.id) {
      return true;
    } else {
      this.router.navigate(['403']);
    }
  }
}
