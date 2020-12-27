import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {ReportDTO} from 'src/app/dto/reportDTO';
import {Course} from 'src/app/model/course';
import {ReportResponseDTO} from 'src/app/dto/response/reportResponseDTO';
import {UserService} from 'src/app/service/user.service';
import {CourseService} from 'src/app/service/course.service';
import {ActivatedRoute, Router} from '@angular/router';
import {ReportService} from 'src/app/service/report.service';
import {ClassroomService} from 'src/app/service/classroom.service';
import {TeacherService} from 'src/app/service/teacher.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {isTeacher} from 'src/app/shared/roles';

@Component({
  selector: 'app-create-report-classroom',
  templateUrl: './create-report-classroom.component.html',
  styleUrls: ['./create-report-classroom.component.scss']
})
export class CreateReportClassroomComponent implements OnInit {

  classroom_id: number;
  currentUser: any = {};
  isDataAvailable: boolean = false;
  isBasicSet: boolean = false;
  courses: Observable<Course[]>;
  reports: Observable<ReportDTO>;
  raw_reports: ReportDTO[];
  response: ReportResponseDTO[];
  marks: any = {};
  year: any = {};
  semester: any = {};
  selectedCourse: any = {};

  constructor(private userService: UserService, private courseService: CourseService, private router: Router, private _snackBar: MatSnackBar,
              private reportService: ReportService, private classroomService: ClassroomService, private teacherService: TeacherService,
              private route: ActivatedRoute) {
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
    this.reportService.makeReportFormToClassroom(this.classroom_id).subscribe(data => {
      this.reports = data;
      this.raw_reports = data;
      this.isBasicSet = true;
    });
  }

  onSubmit() {
    this.reportService.createReportsToClassroom(this.collect(this.marks, this.raw_reports, this.selectedCourse.id, this.year, this.semester))
      .subscribe(data => {
        this.refresh();
        this.openSnackBar('Reports created.', 'Ok');
      });
  }

  refresh() {
    this.response = [];
    this.marks = {};
    this.year = {};
    this.semester = {};
    this.selectedCourse = {};
  }

  collect(marks: number[], entities: ReportDTO[], course_id: number,
          year: number, semester: number): ReportResponseDTO[] {
    var index = 0;
    var result: ReportResponseDTO[] = [];

    for (let entity of entities) {
      result.push(new ReportResponseDTO());

      if (marks[index] != null) {
        result[index].mark = marks[index];
        result[index].course_id = course_id;
        result[index].semester = semester;
        result[index].year = year;
        result[index].student_id = entity.student.id;
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
