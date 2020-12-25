import {Component, OnInit} from '@angular/core';
import {ExamResponseDTO} from 'src/app/dto/response/examResponseDTO';
import {UserService} from 'src/app/service/user.service';
import {ActivatedRoute, Router} from '@angular/router';
import {ExamService} from 'src/app/service/exam.service';
import {CourseService} from 'src/app/service/course.service';
import {Observable} from 'rxjs';
import {Course} from 'src/app/model/course';
import {StudentService} from 'src/app/service/student.service';
import {Classroom} from 'src/app/model/classroom';
import {TeacherService} from 'src/app/service/teacher.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {isAdmin, isTeacher} from 'src/app/shared/roles';

@Component({
  selector: 'app-create-exam',
  templateUrl: './create-exam.component.html',
  styleUrls: ['./create-exam.component.scss']
})
export class CreateExamComponent implements OnInit {

  student_id: number;
  currentUser: any = {};
  etype: any = {};
  isDataAvailable: boolean = false;
  exam = new ExamResponseDTO();
  courses: Observable<Course[]>;
  selectedOption: any = {};
  classroom = new Classroom();

  constructor(private userService: UserService, private router: Router, private examService: ExamService, private teacherService: TeacherService,
              private courseService: CourseService, private studentService: StudentService, private route: ActivatedRoute, private _snackBar: MatSnackBar) {
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

  onSubmit() {
    this.exam.student_id = this.student_id;
    this.exam.course_id = this.selectedOption.id;
    this.exam.examType = this.etype;
    this.examService.create(this.exam).subscribe(() => {
      this.refresh();
      this.openSnackBar('Exam created.', 'Ok');
    }, error => {
      this.openSnackBar('Failed.', 'Ok');
    });
  }

  refresh() {
    this.exam = new ExamResponseDTO();
    this.selectedOption = {};
  }

  goBack() {
    this.studentService.findById(this.student_id).subscribe(data => {
      this.router.navigate(['/student/classroom', data.classroom.id]);
    });
  }

  userRole() {
    if (isAdmin(this.currentUser, this.router) || isTeacher(this.currentUser, this.router)) {
      return true;
    } else {
      this.router.navigate(['403']);
    }
  }
}
