import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Course} from 'src/app/model/course';
import {UserService} from 'src/app/service/user.service';
import {CourseService} from 'src/app/service/course.service';
import {ActivatedRoute, Router} from '@angular/router';
import {MatSnackBar} from '@angular/material/snack-bar';
import {isAdmin} from 'src/app/shared/roles';

@Component({
  selector: 'app-set-course',
  templateUrl: './set-course.component.html',
  styleUrls: ['./set-course.component.scss']
})
export class SetCourseComponent implements OnInit {

  student_id: number;
  currentUser: any = {};
  selectedOption: any = {};
  isDataAvailable: boolean = false;
  unset: boolean = false;
  courses: Observable<Course[]>;

  constructor(private userService: UserService, private courseService: CourseService,
              private router: Router, private route: ActivatedRoute, private _snackBar: MatSnackBar) {
  }

  ngOnInit() {
    this.student_id = this.route.snapshot.params['id'];
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
      this.courseService.setCourse(this.student_id, this.selectedOption.id).subscribe(() => {
        this.refresh();
        this.openSnackBar('Course set to student.', 'Ok');
      }, error => {
        this.openSnackBar('Failed.', 'Ok');
      });
    } else {
      this.courseService.unsetCourse(this.student_id, this.selectedOption.id).subscribe(() => {
        this.refresh();
        this.openSnackBar('Course unset from student.', 'Ok');
      }, error => {
        this.openSnackBar('Failed.', 'Ok');
      });
    }
  }


  refresh() {
    this.unset = false;
    this.selectedOption = {};
  }

  goBack() {
    this.router.navigate(['user/all']);
  }

  userRole() {
    if (isAdmin(this.currentUser, this.router)) {
      return true;
    } else {
      this.router.navigate(['403']);
    }
  }

}
