import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Course} from 'src/app/model/course';
import {ExamDTO} from 'src/app/dto/examDTO';
import {ExamResponseDTO} from 'src/app/dto/response/examResponseDTO';
import {UserService} from 'src/app/service/user.service';
import {CourseService} from 'src/app/service/course.service';
import {ActivatedRoute, Router} from '@angular/router';
import {ExamService} from 'src/app/service/exam.service';
import {TeacherService} from 'src/app/service/teacher.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {isTeacher} from 'src/app/shared/roles';

@Component({
  selector: 'app-create-exam-classroom',
  templateUrl: './create-exam-classroom.component.html',
  styleUrls: ['./create-exam-classroom.component.scss']
})
export class CreateExamClassroomComponent implements OnInit {

  classroom_id: number;
  currentUser: any = {};
  etype: any = {};
  isDataAvailable: boolean = false;
  isBasicSet: boolean = false;
  courses: Observable<Course[]>;
  exams: Observable<ExamDTO[]>;
  raw_exams: ExamDTO[];
  response: ExamResponseDTO[];
  marks: any = {};
  written_at: any = {};
  selectedCourse: any = {};

  constructor(private userService: UserService, private courseService: CourseService, private router: Router,
              private examService: ExamService, private teacherService: TeacherService, private route: ActivatedRoute, private _snackBar: MatSnackBar) {
  }

  ngOnInit() {
    this.classroom_id = this.route.snapshot.params['id'];
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

  setBasic() {
    this.examService.makeExamsFormToClassroom(this.classroom_id, this.written_at, this.etype).subscribe(data => {
      this.exams = data;
      this.raw_exams = data;
      this.isBasicSet = true;
    });
  }

  onSubmit() {
    this.examService.createExamsFromForm(this.collect(this.marks, this.raw_exams, this.selectedCourse.id, this.written_at, this.etype))
      .subscribe(data => {
        this.refresh();
        this.openSnackBar('Exams created', 'Ok');
      }, error => {
        this.openSnackBar('Failed.', 'Ok');
      });
  }

  refresh() {
    this.response = [];
    this.isBasicSet = false;
    this.marks = {};
    this.written_at = {};
    this.selectedCourse = {};
  }

  collect(marks: number[], entities: ExamDTO[], course_id: number,
          written_at: string, etype: string): ExamResponseDTO[] {
    var index = 0;
    var result: ExamResponseDTO[] = [];

    for (let entity of entities) {
      result.push(new ExamResponseDTO());
      result[index].course_id = course_id;
      result[index].student_id = entity.student.id;
      result[index].examType = etype;
      result[index].written_at = written_at;
      if (marks[index] != null) {
        result[index].mark = marks[index];
      } else {
        result[index].mark = 0;
      }
      index++;
    }
    return result;
  }

  goBack() {
    this.router.navigate(['classroom/all']);
  }

  userRole() {
    if (isTeacher(this.currentUser, this.router)) {
      return true;
    } else {
      this.router.navigate(['403']);
    }
  }

}
