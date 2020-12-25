import {Component, OnInit} from '@angular/core';
import {CourseService} from 'src/app/service/course.service';
import {UserService} from 'src/app/service/user.service';
import {Router} from '@angular/router';
import {TeacherService} from 'src/app/service/teacher.service';
import {Observable} from 'rxjs';
import {Teacher} from 'src/app/model/teacher';
import {CourseResponseDTO} from 'src/app/dto/response/courseResponseDTO';
import {MatSnackBar} from '@angular/material/snack-bar';
import {isAdmin} from 'src/app/shared/roles';

@Component({
  selector: 'app-course-create',
  templateUrl: './course-create.component.html',
  styleUrls: ['./course-create.component.scss']
})
export class CourseCreateComponent implements OnInit {

  course = new CourseResponseDTO();
  currentUser: any = {};
  isDataAvailable: boolean = false;
  teachers: Observable<Teacher[]>;
  selectedOption: any = {};

  constructor(private userService: UserService, private router: Router, private _snackBar: MatSnackBar,
              private teacherService: TeacherService, private courseService: CourseService) {

  }

  ngOnInit() {
    this.userService.getMyInfo().toPromise().then(data => {
      this.currentUser = data;
      this.teacherService.findAll().subscribe(data => {
        this.teachers = data;
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
    this.course.teacher_id = Number(this.selectedOption.id);
    this.courseService.create(this.course).subscribe(() => {
      this.reset();
      this.openSnackBar('Course created.', 'Ok');
    }, error => {
      this.openSnackBar('Failed.', 'Ok');
    });
    this.refresh();
  }

  reset() {
    this.course = new CourseResponseDTO();
    this.selectedOption = {};
  }

  refresh(): void {
    window.location.reload();
  }

  goBack() {
    this.router.navigate(['/course/all']);
  }

  userRole() {
    if (isAdmin(this.currentUser, this.router)) {
      return true;
    } else {
      this.router.navigate(['403']);
    }
  }

}
