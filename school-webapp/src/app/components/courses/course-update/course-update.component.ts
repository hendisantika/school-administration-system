import {Component, OnInit} from '@angular/core';
import {UserService} from 'src/app/service/user.service';
import {TeacherService} from 'src/app/service/teacher.service';
import {ActivatedRoute, Router} from '@angular/router';
import {CourseService} from 'src/app/service/course.service';
import {Course} from 'src/app/model/course';
import {CourseResponseDTO} from 'src/app/dto/response/courseResponseDTO';
import {Observable} from 'rxjs';
import {Teacher} from 'src/app/model/teacher';
import {MatSnackBar} from '@angular/material/snack-bar';
import {isAdmin} from 'src/app/shared/roles';

@Component({
  selector: 'app-course-update',
  templateUrl: './course-update.component.html',
  styleUrls: ['./course-update.component.scss']
})
export class CourseUpdateComponent implements OnInit {

  currentUser: any = {};
  isDataAvailable: boolean = false;
  course_id: number;
  teachers: Observable<Teacher[]>;
  course = new Course();
  response = new CourseResponseDTO();
  selectedOption: any = {};

  constructor(private userService: UserService, private teacherService: TeacherService, private _snackBar: MatSnackBar,
              private router: Router, private route: ActivatedRoute, private courseService: CourseService) {
  }

  ngOnInit() {
    this.course_id = this.route.snapshot.params['id'];
    this.userService.getMyInfo().toPromise().then(data => {
      this.currentUser = data;
      this.courseService.findById(this.course_id).subscribe(data => {
        this.course = data;
        this.teacherService.findAll().subscribe(data => {
          this.teachers = data;
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

  isDataChanged() {
    if (!this.response.title
      || !this.response.year
      || !this.response.teacher_id) return true;
    return false;
  }

  onSubmit() {
    if (this.isDataChanged) {
      if (!this.selectedOption) this.response.teacher_id = this.course.teacher.id;
      else this.response.teacher_id = Number(this.selectedOption.id);
      if (!this.response.title) this.response.title = this.course.title;
      if (!this.response.year) this.response.year = this.course.year;
      this.courseService.update(this.course_id, this.response).subscribe(() => {
        this.openSnackBar('Course updated', 'Ok');
        this.refresh();
      });
    }
  }

  refresh() {
    this.courseService.findById(this.course_id).subscribe(data => {
      this.course = data;
    });
    this.response = new CourseResponseDTO();
    this.selectedOption = {};
  }

  goBack() {
    this.router.navigate(['/course/all']);
  }

  userRole() {
    return isAdmin(this.currentUser, this.router);
  }

}
