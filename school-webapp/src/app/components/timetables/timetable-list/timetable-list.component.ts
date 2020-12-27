import {Component, OnInit} from '@angular/core';
import {UserService} from 'src/app/service/user.service';
import {CourseService} from 'src/app/service/course.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Observable} from 'rxjs';
import {TimeTableEntity} from 'src/app/model/timeTableEntity';
import {Course} from 'src/app/model/course';
import {TimeTableService} from 'src/app/service/timeTable.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {isAdmin} from 'src/app/shared/roles';

@Component({
  selector: 'app-timetable-list',
  templateUrl: './timetable-list.component.html',
  styleUrls: ['./timetable-list.component.scss']
})
export class TimetableListComponent implements OnInit {

  course_id: number;
  currentUser: any = {};
  course = new Course();
  isDataAvailable: boolean = false;
  timeTable: Observable<TimeTableEntity[]>;

  constructor(private userService: UserService, private courseService: CourseService, private _snackBar: MatSnackBar,
              private router: Router, private route: ActivatedRoute, private timeTableService: TimeTableService) {
  }

  ngOnInit() {
    this.course_id = this.route.snapshot.params['id'];
    this.userService.getMyInfo().toPromise().then(data => {
      this.currentUser = data;
      this.courseService.findById(this.course_id).subscribe(data => {
        this.course = data;
        this.timeTableService.getTimeTableEntitiesByCourse(this.course_id).subscribe(data => {
          this.timeTable = data;
          this.isDataAvailable = true;
        });
      });
    });
  }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action, {
      duration: 2000,
    });
  }

  create() {
    this.courseService.findById(this.course_id).subscribe(data =>
      this.router.navigate(['/timetable/create', data.id])
    );
  }

  userRole() {
    if (isAdmin(this.currentUser, this.router)) {
      return true;
    } else {
      this.router.navigate(['403']);
    }
  }

  update(entity_id: number) {
    this.router.navigate(['timetable/update', entity_id]);
  }

  delete(entity_id: number) {
    this.timeTableService.delete(entity_id).subscribe(() => {
      this.refresh();
      this.openSnackBar('Time table entity deleted', 'Ok');
    });
  }

  refresh(): void {
    window.location.reload();
  }
}
