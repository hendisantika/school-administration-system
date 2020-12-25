import {Component, OnInit} from '@angular/core';
import {AttendanceResponseDTO} from 'src/app/dto/response/attendanceResponseDTO';
import {Observable} from 'rxjs';
import {AttendanceDTO} from 'src/app/dto/AttendanceDTO';
import {AttendanceService} from 'src/app/service/attendace.service';
import {UserService} from 'src/app/service/user.service';
import {ActivatedRoute, Router} from '@angular/router';
import {MatSnackBar} from '@angular/material/snack-bar';
import {isTeacher} from 'src/app/shared/roles';

@Component({
  selector: 'app-create-attendance',
  templateUrl: './create-attendance.component.html',
  styleUrls: ['./create-attendance.component.scss']
})
export class CreateAttendanceComponent implements OnInit {

  classroom_id: number;
  currentUser: any = {};
  isDataAvailable: boolean = false;
  isBasicSet: boolean = false;
  miss: boolean[] = [];
  lesson: any = {};
  dom: any = {};
  response: AttendanceResponseDTO[];
  attendances: Observable<AttendanceDTO[]>;
  raw_attendances: AttendanceDTO[];


  constructor(private userService: UserService, private router: Router, private _snackBar: MatSnackBar,
              private attendanceService: AttendanceService, private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.classroom_id = this.route.snapshot.params['id'];
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

  setBasic() {
    this.attendanceService.makeAttendanceFormToClassroom(this.classroom_id).subscribe(data => {
      this.attendances = data;
      this.raw_attendances = data;
      this.isBasicSet = true;
    });
  }

  onSubmit() {
    this.attendanceService.create(this.collect(this.miss, this.raw_attendances, this.lesson, this.dom)).subscribe(data => {
      this.openSnackBar('Attendance created.', 'Ok');
    }, error => {
      this.openSnackBar('Failed.', 'Ok');
    });
  }

  collect(misses: boolean[], entities: AttendanceDTO[], lesson: number, dom: string): AttendanceResponseDTO[] {
    var index = 0;
    var result: AttendanceResponseDTO[] = [];
    for (let entity of entities) {
      result.push(new AttendanceResponseDTO());
      if (misses[index]) result[index].miss = misses[index];
      else result[index].miss = false;
      result[index].dateOfMiss = dom;
      result[index].lesson = lesson;
      result[index].student_id = entity.student.id;
      index++;
    }
    return result;
  }

  goBack() {
    this.router.navigate(['classroom/all']);
  }

  userRole() {
    if (isTeacher(this.currentUser, this.router)) {
      return true;
    } else {
      this.router.navigate(['403']);
    }
  }

}
