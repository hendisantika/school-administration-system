import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Attendance} from 'src/app/model/attendance';
import {UserService} from 'src/app/service/user.service';
import {ActivatedRoute, Router} from '@angular/router';
import {AttendanceService} from 'src/app/service/attendace.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {isAdmin, isTeacher} from 'src/app/shared/roles';

@Component({
  selector: 'app-class-attendance',
  templateUrl: './class-attendance.component.html',
  styleUrls: ['./class-attendance.component.scss']
})
export class ClassAttendanceComponent implements OnInit {

  classroom_id: number;
  searchText;
  isDataAvailable: boolean = false;
  currentUser: any = {};
  miss: boolean[] = [];
  attendances: Observable<Attendance[]>;
  raw_attendances: Attendance[];

  constructor(private userService: UserService, private router: Router, private route: ActivatedRoute,
              private attendanceService: AttendanceService, private _snackBar: MatSnackBar) {
  }

  ngOnInit() {
    this.classroom_id = this.route.snapshot.params['id'];
    this.userService.getMyInfo().toPromise().then(data => {
      this.currentUser = data;
      this.attendanceService.getAllAttendancesByClassroom(this.classroom_id).subscribe(data => {

        this.attendances = data;
        this.raw_attendances = data;
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
    this.collect(this.raw_attendances, this.miss);
    this.refresh();
  }

  collect(attendances: Attendance[], misses: boolean[]) {
    var index = 0;
    for (let attendance of attendances) {
      if (misses[index] == true) {
        this.attendanceService.verify(attendance.id).subscribe();
      }
      index++;
    }
    if (index > 0) {
      this.openSnackBar('Attendances verified.', 'Ok');
    }
  }

  userRole() {
    if (isAdmin(this.currentUser, this.router) || isTeacher(this.currentUser, this.router)) {
      return true;
    } else {
      this.router.navigate(['403']);
    }
  }

  refresh(): void {
    window.location.reload();
  }
}
