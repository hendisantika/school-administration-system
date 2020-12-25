import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Exam} from 'src/app/model/exam';
import {Course} from 'src/app/model/course';
import {UserService} from 'src/app/service/user.service';
import {CourseService} from 'src/app/service/course.service';
import {ExamService} from 'src/app/service/exam.service';
import {ActivatedRoute, Router} from '@angular/router';
import {TeacherService} from 'src/app/service/teacher.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {isAdmin, isTeacher} from 'src/app/shared/roles';

@Component({
  selector: 'app-exam-list',
  templateUrl: './exam-list.component.html',
  styleUrls: ['./exam-list.component.scss']
})
export class ExamListComponent implements OnInit {

  student_id: number;
  currentUser: any = {};
  exams: Observable<Exam[]>;
  courses: Observable<Course[]>;
  selected: boolean = false;
  isDataAvailable: boolean = false;
  selectedOption: any = {};

  constructor(private userService: UserService, private courseService: CourseService, private teacherService: TeacherService,
              private examService: ExamService, private router: Router, private route: ActivatedRoute, private _snackBar: MatSnackBar) {
  }

  ngOnInit() {
    this.student_id = this.route.snapshot.params['id'];
    this.userService.getMyInfo().toPromise().then(data => {
      this.currentUser = data;
      this.teacherService.findByUserId(this.currentUser.id).subscribe(data => {
        this.courseService.getCoursesByTeacherId(data.id).subscribe(data => {
          this.courses = data;
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
    this.router.navigate(['exam/create', this.student_id]);
  }

  onSubmit() {
    this.examService.findAllByStudent(this.student_id, this.selectedOption.id).subscribe(data => {
      this.exams = data;
      this.selected = true;
    });
  }

  update(exam_id: number) {
    this.router.navigate(['exam/update/', exam_id]);
  }

  delete(exam_id: number) {
    this.examService.delete(exam_id).subscribe(() => {
      this.refresh();
      this.openSnackBar('Exam deleted.', 'Ok');
    }, error => {
      this.openSnackBar('Failed.', 'Ok');
    });
  }

  userRole() {
    if (isAdmin(this.currentUser, this.router) || isTeacher(this.currentUser, this.router)) {
      return true;
    } else {
      this.router.navigate(['403']);
    }
  }

  refresh(): void {
    window.location.reload();
  }
}
