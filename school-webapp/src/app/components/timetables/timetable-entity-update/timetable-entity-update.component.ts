import {Component, OnInit} from '@angular/core';
import {TimeTableEntity} from 'src/app/model/timeTableEntity';
import {Observable} from 'rxjs';
import {Classroom} from 'src/app/model/classroom';
import {TimeTableEntityResponseDTO} from 'src/app/dto/response/timeTableEntityResponseDTO';
import {UserService} from 'src/app/service/user.service';
import {ActivatedRoute, Router} from '@angular/router';
import {ClassroomService} from 'src/app/service/classroom.service';
import {TimeTableService} from 'src/app/service/timeTable.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {Room} from 'src/app/model/room';
import {RoomService} from 'src/app/service/room.service';
import {isAdmin} from 'src/app/shared/roles';

@Component({
  selector: 'app-timetable-entity-update',
  templateUrl: './timetable-entity-update.component.html',
  styleUrls: ['./timetable-entity-update.component.scss']
})
export class TimetableEntityUpdateComponent implements OnInit {

  id: number;
  currentUser: any = {};
  isDataAvailable: boolean = false;
  selectedOptionClassroom: any = {};
  selectedOptionRoom: any = {};
  classrooms: Observable<Classroom[]>;
  rooms: Observable<Room[]>;
  response = new TimeTableEntityResponseDTO();
  timeTableEntity = new TimeTableEntity();

  constructor(private userService: UserService, private router: Router, private route: ActivatedRoute, private roomService: RoomService,
              private classroomService: ClassroomService, private timeTableService: TimeTableService, private _snackBar: MatSnackBar) {
  }

  ngOnInit() {
    this.id = this.route.snapshot.params['id'];
    this.userService.getMyInfo().toPromise().then(data => {
      this.currentUser = data;
      this.classroomService.findAll().subscribe(data => {
        this.classrooms = data;
        this.roomService.findAll().subscribe(data => {
          this.rooms = data;
          this.timeTableService.findById(this.id).subscribe(data => {
            this.timeTableEntity = data;
            this.isDataAvailable = true;
          });
        });
      });
    });
  }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action, {
      duration: 2000,
    });
  }

  isDataChanged() {
    if (!this.response.classroom_id
      || !this.response.day
      || !this.response.lessonNumber) return true;
    return false;
  }

  onSubmit() {
    if (this.isDataChanged) {
      this.response.course_id = this.timeTableEntity.course.id;
      this.response.room_id = this.selectedOptionRoom.id;
      if (!this.response.room_id) this.response.room_id = this.timeTableEntity.room.id;
      if (!this.response.day) this.response.day = this.timeTableEntity.day;
      if (!this.response.lessonNumber) this.response.lessonNumber = this.timeTableEntity.lessonNumber;
      this.response.classroom_id = this.selectedOptionClassroom.id;
      if (!this.response.classroom_id) this.response.classroom_id = this.timeTableEntity.classroom.id;
      this.timeTableService.update(this.id, this.response).subscribe(() => {
        this.refresh();
        this.openSnackBar('Time table entity updated', 'Ok');
      });
    }
  }

  refresh() {
    this.response = new TimeTableEntityResponseDTO();
    this.timeTableEntity = new TimeTableEntity();
    this.selectedOptionClassroom = {};
  }

  goBack(course_id: number) {
    this.router.navigate(['/timetable/course', course_id]);
  }

  userRole() {
    if (isAdmin(this.currentUser, this.router)) {
      return true;
    } else {
      this.router.navigate(['403']);
    }
  }
}
