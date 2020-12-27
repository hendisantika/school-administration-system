import {Component, OnInit} from '@angular/core';
import {SummaryDTO} from 'src/app/dto/summaryDTO';
import {UserService} from 'src/app/service/user.service';
import {StudentService} from 'src/app/service/student.service';
import {Student} from 'src/app/model/student';
import {ActivatedRoute, Router} from '@angular/router';
import {isAdmin, isTeacher} from 'src/app/shared/roles';

@Component({
  selector: 'app-summary-student',
  templateUrl: './summary-student.component.html',
  styleUrls: ['./summary-student.component.scss']
})
export class SummaryStudentComponent implements OnInit {

  currentUser: any = {};
  student = new Student();
  summaries: SummaryDTO[];
  isDataAvailable: boolean = false;
  id: number;

  constructor(private userService: UserService, private studentService: StudentService,
              private route: ActivatedRoute, private router: Router) {
  }

  ngOnInit() {
    this.id = this.route.snapshot.params['id'];
    this.userService.getMyInfo().toPromise().then(data => {
      this.currentUser = data;
      this.studentService.findById(this.id).subscribe(data => {
        this.student = data;
        this.studentService.summary(this.student.id).subscribe(data => {
          this.summaries = this.format(data);
          this.isDataAvailable = true;
        });
      });
    });
  }

  format(summaries: SummaryDTO[]) {
    for (let key in summaries) {
      if (isNaN(summaries[key].average)) summaries[key].average = 0;
    }
    return summaries;
  }

  userRole() {
    if (isAdmin(this.currentUser, this.router) || isTeacher(this.currentUser, this.router) ||
      this.currentUser.id == this.student.student.id) {
      return true;
    } else {
      this.router.navigate(['403']);
    }
  }
}
