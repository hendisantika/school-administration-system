import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Classroom} from 'src/app/model/classroom';
import {ClassroomService} from 'src/app/service/classroom.service';
import {UserService} from 'src/app/service/user.service';
import {Router} from '@angular/router';
import {AdminService} from 'src/app/service/admin.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {isAdmin, isTeacher} from 'src/app/shared/roles';

@Component({
  selector: 'app-classroom-list',
  templateUrl: './classroom-list.component.html',
  styleUrls: ['./classroom-list.component.scss']
})
export class ClassroomListComponent implements OnInit {

  searchText;
  classrooms: Observable<Classroom[]>;
  isDataAvailable: boolean = false;
  currentUser: any = {};

  constructor(private userService: UserService, private router: Router, private _snackBar: MatSnackBar,
              private classroomService: ClassroomService, private adminService: AdminService) {
  }

  ngOnInit() {
    this.userService.getMyInfo().toPromise().then(data => {
      this.currentUser = data;
      this.classroomService.findAll().subscribe(data => {
        this.classrooms = data;
        this.isDataAvailable = true;
      });
    });
  }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action, {
      duration: 2000,
    });
  }

  userRole() {
    if (isAdmin(this.currentUser, this.router) || isTeacher(this.currentUser, this.router)) {
      return true;
    } else {
      this.router.navigate(['403']);
    }
  }

  update(classroom_id: number) {
    this.router.navigate(['classroom/update', classroom_id]);
  }

  attendance(classroom_id: number) {
    this.router.navigate(['attendance/create', classroom_id]);
  }

  delete(classroom_id: number) {
    this.classroomService.delete(classroom_id).subscribe(() => {
      this.refresh();
      this.openSnackBar('Class deleted.', 'Ok');
    }, error => {
      this.openSnackBar('Failed.', 'Ok');
    });
  }

  finished(classroom_id: number) {
    this.adminService.finished(classroom_id).subscribe(() => {
    });
  }

  exam(classroom_id: number) {
    this.router.navigate(['exam/classroom', classroom_id]);
  }

  report(classroom_id: number) {
    this.router.navigate(['report/classroom', classroom_id]);
  }

  students(classroom_id: number) {
    this.router.navigate(['student/classroom', classroom_id]);
  }

  setCourse(classroom_id: number) {
    this.router.navigate(['classroom/setCourse', classroom_id]);
  }

  createClassroom() {
    this.router.navigate(['classroom/create']);
  }

  refresh(): void {
    window.location.reload();
  }
}
