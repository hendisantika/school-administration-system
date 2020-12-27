import {Component, OnInit} from '@angular/core';
import {UserService} from 'src/app/service/user.service';
import {StudentService} from 'src/app/service/student.service';
import {ActivatedRoute} from '@angular/router';
import {TeacherService} from 'src/app/service/teacher.service';
import {TimeTableService} from 'src/app/service/timeTable.service';
import {Observable} from 'rxjs';
import {TimeTableEntity} from 'src/app/model/timeTableEntity';

@Component({
  selector: 'app-timetable-entity-view',
  templateUrl: './timetable-entity-view.component.html',
  styleUrls: ['./timetable-entity-view.component.scss']
})
export class TimetableEntityViewComponent implements OnInit {

  currentUser: any = {};
  id: number;
  isDataAvailable: boolean = false;
  timetable: Observable<TimeTableEntity[]>;

  constructor(private userService: UserService, private route: ActivatedRoute,
              private studentService: StudentService, private teacherService: TeacherService,
              private timeTableService: TimeTableService) {
  }

  ngOnInit() {
    this.id = this.route.snapshot.params['id'];
    this.userService.getMyInfo().toPromise().then(data => {
      this.currentUser = data;
      var student = {};
      var teacher = {};
      console.log(data);
      if (this.userRole() == 'ROLE_TEACHER' || this.userRole() == 'ROLE_HEADTEACHER') {
        this.teacherService.findById(this.id).subscribe(data => {
          teacher = data;
          if (teacher) {
            this.timeTableService.getTimeTableByTeacher(this.id).subscribe(data => {
              this.timetable = data;
              this.isDataAvailable = true;
            });
          }
        });
      } else if (this.userRole() == 'ROLE_STUDENT') {
        this.studentService.findById(this.id).subscribe(data => {
          student = data;
          if (student) {
            this.timeTableService.getTimeTableByStudent(this.id).subscribe(data => {
              this.timetable = data;
              this.isDataAvailable = true;
            });
          } else {

          }
        });
      } else {
        this.isDataAvailable = true;
      }
    });
  }

  userRole(): string {
    return this.currentUser.authorities[0].authority + '';
  }

  hasSignedIn() {
    return !!this.userService.currentUser;
  }

}
