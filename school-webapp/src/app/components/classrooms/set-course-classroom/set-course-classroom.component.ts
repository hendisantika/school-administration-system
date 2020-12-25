import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Course} from 'src/app/model/course';
import {UserService} from 'src/app/service/user.service';
import {CourseService} from 'src/app/service/course.service';
import {ActivatedRoute, Router} from '@angular/router';
import {ClassroomService} from 'src/app/service/classroom.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {isAdmin} from 'src/app/shared/roles';

@Component({
  selector: 'app-set-course-classroom',
  templateUrl: './set-course-classroom.component.html',
  styleUrls: ['./set-course-classroom.component.scss']
})
export class SetCourseClassroomComponent implements OnInit {

  currentUser: any = {};
  selectedOption: any = {};
  classroom_id: number;
  isDataAvailable: boolean = false;
  unset: boolean = false;
  courses: Observable<Course[]>;

  constructor(private userService: UserService, private courseService: CourseService, private route: ActivatedRoute,
              private router: Router, private classroomService: ClassroomService, private _snackBar: MatSnackBar) {
  }

  ngOnInit() {
    this.classroom_id = this.route.snapshot.params['id'];
    this.userService.getMyInfo().toPromise().then(data => {
      this.currentUser = data;
      this.courseService.findAll().subscribe(data => {
        this.courses = data;
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
    if (this.unset == false) {
      this.classroomService.setCourse(this.classroom_id, this.selectedOption.id).subscribe(() => {
        this.refresh();
        this.openSnackBar('Course set to class.', 'Ok');
      }, error => {
        this.openSnackBar('Failed.', 'Ok');
      });
    } else {
      this.classroomService.unsetCourse(this.classroom_id, this.selectedOption.id).subscribe(() => {
        this.refresh();
        this.openSnackBar('Course unset from class.', 'Ok');
      }, error => {
        this.openSnackBar('Failed.', 'Ok');
      });
    }

  }

  refresh() {
    this.selectedOption = {};
  }

  goBack() {
    this.router.navigate(['classroom/all']);
  }

  userRole() {
    if (isAdmin(this.currentUser, this.router)) {
      return true;
    } else {
      this.router.navigate(['403']);
    }
  }
}
