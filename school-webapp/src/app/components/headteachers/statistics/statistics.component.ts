import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {FailedStudentDTO} from 'src/app/dto/failedStudentDTO';
import {ClassroomCourseResultDTO} from 'src/app/dto/classroomCourseResultDTO';
import {HeadTeacherService} from 'src/app/service/headteacher.service';
import {UserService} from 'src/app/service/user.service';
import {ActivatedRoute, Router} from '@angular/router';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-statistics',
  templateUrl: './statistics.component.html',
  styleUrls: ['./statistics.component.scss']
})
export class StatisticsComponent implements OnInit {

  isDataAvailable: boolean = false;
  currentUser: any = {};
  failed: Observable<FailedStudentDTO[]>;
  averages: ClassroomCourseResultDTO[];
  classroom_id: number;

  constructor(private userService: UserService, private router: Router, private route: ActivatedRoute,
              private headTeacherService: HeadTeacherService, private _snackBar: MatSnackBar) {
  }

  ngOnInit() {
    this.classroom_id = this.route.snapshot.params['id'];
    this.userService.getMyInfo().toPromise().then(data => {
      this.currentUser = data;
      this.headTeacherService.findFailedStudentsInClass(this.classroom_id).subscribe(data => {
        this.failed = data;
        this.headTeacherService.showResultByCourse(this.classroom_id).subscribe(data => {
          this.averages = this.format(data);
          this.isDataAvailable = true;
        });
      });
    });
  }

  format(data: ClassroomCourseResultDTO[]) {
    for (let key in data) {
      if (isNaN(data[key].result)) data[key].result = 0;
    }
    return data;
  }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action, {
      duration: 2000,
    });
  }

  userRole() {
    if (this.currentUser.authorities[0].authority + '' === 'ROLE_HEADTEACHER') {
      return true;
    } else {
      this.router.navigate(['403']);
    }
    return false;
  }

}
