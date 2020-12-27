import {Component, OnInit} from '@angular/core';
import {UserService} from 'src/app/service/user.service';
import {Router} from '@angular/router';
import {ClassroomService} from 'src/app/service/classroom.service';
import {TeacherService} from 'src/app/service/teacher.service';
import {Teacher} from 'src/app/model/teacher';

@Component({
  selector: 'app-headteacher-panel',
  templateUrl: './headteacher-panel.component.html',
  styleUrls: ['./headteacher-panel.component.scss']
})
export class HeadteacherPanelComponent implements OnInit {

  currentUser: any = {};
  teacher = new Teacher();
  isDataAvailable: boolean = false;

  constructor(private userService: UserService, private router: Router,
              private classroomService: ClassroomService, private teacherService: TeacherService) {
  }

  ngOnInit() {
    this.userService.getMyInfo().toPromise().then(data => {
      this.currentUser = data;
      this.teacherService.findByUserId(this.currentUser.id).subscribe(data => {
        this.teacher = data;
        this.isDataAvailable = true;
      })
    });
  }

  getMyClassroom() {
    this.classroomService.findByHeadteacherId(this.teacher.id).subscribe(data => {
      this.router.navigate(['student/classroom', data.id]);
    });
  }

  getAttendances() {
    this.classroomService.findByHeadteacherId(this.teacher.id).subscribe(data => {
      this.router.navigate(['attendance/classroom', data.id]);
    });
  }

  getStatistics() {
    this.classroomService.findByHeadteacherId(this.teacher.id).subscribe(data => {
      this.router.navigate(['statistics', data.id]);
    });
  }
}
