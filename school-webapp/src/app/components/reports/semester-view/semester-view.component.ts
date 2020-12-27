import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Report} from 'src/app/model/report';
import {UserService} from 'src/app/service/user.service';
import {StudentService} from 'src/app/service/student.service';
import {ActivatedRoute, Router} from '@angular/router';
import {ReportService} from 'src/app/service/report.service';
import {Student} from 'src/app/model/student';
import {TeacherService} from 'src/app/service/teacher.service';
import {Teacher} from 'src/app/model/teacher';
import {CourseService} from 'src/app/service/course.service';
import {Course} from 'src/app/model/course';
import {isIdMatches, isTeacher} from 'src/app/shared/roles';

@Component({
  selector: 'app-semester-view',
  templateUrl: './semester-view.component.html',
  styleUrls: ['./semester-view.component.scss']
})
export class SemesterViewComponent implements OnInit {

  student_id: number;
  currentUser: any = {};
  year: number;
  semester: any = {};
  isDataAvailable: boolean = false;
  selected: boolean = false;
  reports: Observable<Report[]>;
  student = new Student();
  teacher = new Teacher();
  courses: Course[];

  constructor(private userService: UserService, private studentService: StudentService, private teacherService: TeacherService,
              private router: Router, private route: ActivatedRoute, private reportService: ReportService, private courseService: CourseService) {
  }

  ngOnInit() {
    this.student_id = this.route.snapshot.params['id'];
    this.userService.getMyInfo().toPromise().then(data => {
      this.currentUser = data;
      this.studentService.findByUserId(this.currentUser.id).subscribe(data => {
        this.student = data;
        this.isDataAvailable = true;
      });
    });
  }

  onSubmit() {
    this.reportService.getSemesterResultByStudent(this.student_id, this.year, Number(this.semester)).subscribe(data => {
      this.reports = data;
      this.selected = true;
    });
  }

  goBack() {
    this.studentService.findById(this.student_id).subscribe(data => {
      this.router.navigate(['student/classroom/', data.classroom.id]);
    });
  }

  userRole() {
    if (isTeacher(this.currentUser, this.router) ||
      isIdMatches(this.currentUser, this.router, this.student_id, this.studentService)) {
      return true;
    } else {
      this.router.navigate(['403']);
    }
  }

  update(report_id: number) {
    this.router.navigate(['report/update', report_id]);
  }

  delete(report_id: number) {
    this.reportService.delete(report_id).subscribe(() => {
      this.refresh();
    });
  }

  refresh(): void {
    window.location.reload();
  }

}
