import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {User} from 'src/app/model/user';
import {UserService} from 'src/app/service/user.service';
import {Router} from '@angular/router';
import {StudentService} from 'src/app/service/student.service';
import {TeacherService} from 'src/app/service/teacher.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {isAdmin} from 'src/app/shared/roles';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss']
})
export class UserListComponent implements OnInit {

  searchText;
  users: Observable<User[]>;
  isDataAvailable: boolean = false;
  currentUser: any = {};

  constructor(private userService: UserService, private router: Router, private _snackBar: MatSnackBar,
              private studentService: StudentService, private teacherService: TeacherService) {
  }

  ngOnInit() {
    this.userService.getMyInfo().toPromise().then(data => {
      this.currentUser = data;
      this.userService.getAll().subscribe(data => {
        this.users = data;
        this.isDataAvailable = true;
      });
    });
  }

  userRole() {
    if (isAdmin(this.currentUser, this.router)) {
      return true;
    } else {
      this.router.navigate(['403']);
    }
  }

  details(user_id: number) {
    this.userService.getById(user_id).subscribe(data => {
      if (data.authorities[0].authority + '' === 'ROLE_STUDENT') {
        this.studentService.findByUserId(user_id).subscribe(data => this.router.navigate(['student/details', data.id]));
      } else if (data.authorities[0].authority + '' === 'ROLE_TEACHER'
        || data.authorities[0].authority + '' === 'ROLE_HEADTEACHER') {
        this.teacherService.findByUserId(user_id).subscribe(data => this.router.navigate(['teacher/details', data.id]));
      }
    });
  }

  update(user_id: number) {
    this.userService.getById(user_id).subscribe(data => {
      if (data.authorities[0].authority + '' === 'ROLE_STUDENT') {
        this.studentService.findByUserId(user_id).subscribe(data => this.router.navigate(['student/update', data.id]));
      } else if (data.authorities[0].authority + '' === 'ROLE_TEACHER'
        || data.authorities[0].authority + '' === 'ROLE_HEADTEACHER') {
        this.teacherService.findByUserId(user_id).subscribe(data => this.router.navigate(['teacher/update', data.id]));
      }
    });
  }

  createStudent() {
    this.router.navigate(['/student/create']);
  }

  createTeacher() {
    this.router.navigate(['/teacher/create']);
  }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action, {
      duration: 4000,
    });
  }

  setCourse(student_id: number) {
    this.router.navigate(['course/setCourse', student_id]);
  }

  delete(user_id: number) {
    this.userService.getById(user_id).subscribe(data => {
      if (data.authorities[0].authority + '' === 'ROLE_STUDENT') {
        this.studentService.findByUserId(user_id).subscribe(data => {
          this.studentService.delete(data.id).subscribe(() => {
            this.userService.delete(user_id).subscribe(() => {
              this.refresh();
              this.openSnackBar('Student deleted.', 'Ok');
            });
          });
        });
      } else if (data.authorities[0].authority + '' === 'ROLE_TEACHER'
        || data.authorities[0].authority + '' === 'ROLE_HEADTEACHER') {
        this.teacherService.findByUserId(user_id).subscribe(data => {
          this.teacherService.delete(data.id).subscribe(() => {
            this.userService.delete(user_id).subscribe(() => {
              this.refresh();
              this.openSnackBar('Teacher deleted.', 'Ok');
            });
          }, error => {
            this.openSnackBar('Failed, you must update ther courses, and classes!', 'Ok');
          });
        });
      }
    });
  }

  refresh(): void {
    window.location.reload();
  }
}
