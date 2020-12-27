import {Component, OnInit} from '@angular/core';
import {ReportResponseDTO} from 'src/app/dto/response/reportResponseDTO';
import {Observable} from 'rxjs';
import {Course} from 'src/app/model/course';
import {UserService} from 'src/app/service/user.service';
import {ActivatedRoute, Router} from '@angular/router';
import {TeacherService} from 'src/app/service/teacher.service';
import {ReportService} from 'src/app/service/report.service';
import {CourseService} from 'src/app/service/course.service';
import {StudentService} from 'src/app/service/student.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {isTeacher} from 'src/app/shared/roles';

@Component({
  selector: 'app-create-report',
  templateUrl: './create-report.component.html',
  styleUrls: ['./create-report.component.scss']
})
export class CreateReportComponent implements OnInit {

  student_id: number;
  currentUser: any = {};
  isDataAvailable: boolean = false;
  report = new ReportResponseDTO();
  courses: Observable<Course[]>;
  selectedOption: any = {};
  semester: any = {};

  constructor(private userService: UserService, private router: Router, private route: ActivatedRoute,
              private teacherService: TeacherService, private reportService: ReportService,
              private courseService: CourseService, private studentService: StudentService, private _snackBar: MatSnackBar) {
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
    this.report.student_id = this.student_id;
    if (this.selectedOption) {
      this.report.semester = this.semester;
      this.report.course_id = this.selectedOption.id;
      this.reportService.create(this.report).subscribe(() => {
        this.refresh();
        this.openSnackBar('Report created.', 'Ok');
      }, error => {
        this.openSnackBar('Failed', 'Ok');
      });
    }
  }

  refresh() {
    this.report = new ReportResponseDTO();
    this.selectedOption = {};
    this.semester = {};
  }

  goBack() {
    this.studentService.findById(this.student_id).subscribe(data => {
      this.router.navigate(['/student/classroom', data.classroom.id]);
    });
  }

  userRole() {
    if (isTeacher(this.currentUser, this.router)) {
      return true;
    } else {
      this.router.navigate(['403']);
    }
  }
}
