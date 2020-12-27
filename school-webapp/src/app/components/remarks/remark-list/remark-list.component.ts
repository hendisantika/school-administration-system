import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Remark} from 'src/app/model/remark';
import {RemarkService} from 'src/app/service/remark.service';
import {UserService} from 'src/app/service/user.service';
import {ActivatedRoute, Router} from '@angular/router';
import {MatSnackBar} from '@angular/material/snack-bar';
import {isAdmin, isTeacher} from 'src/app/shared/roles';
import {StudentService} from 'src/app/service/student.service';
import {Student} from 'src/app/model/student';

@Component({
  selector: 'app-remark-list',
  templateUrl: './remark-list.component.html',
  styleUrls: ['./remark-list.component.scss']
})
export class RemarkListComponent implements OnInit {

  remarks: Observable<Remark[]>;
  isDataAvailable: boolean = false;
  student_id: number;
  student: Student;
  currentUser: any = {};

  constructor(private userService: UserService, private router: Router, private studentService: StudentService,
              private remarkService: RemarkService, private route: ActivatedRoute, private _snackBar: MatSnackBar) {
  }

  ngOnInit() {
    this.student_id = this.route.snapshot.params['id'];
    this.userService.getMyInfo().toPromise().then(data => {
      this.currentUser = data;
      this.remarkService.findAll(this.student_id).subscribe(data => {
        this.remarks = data;
        this.studentService.findByUserId(this.student_id).subscribe(data => {
          this.student = data;

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

  userRole() {
    if (isAdmin(this.currentUser, this.router) || isTeacher(this.currentUser, this.router) ||
      this.currentUser.id == this.student.student.id) {
      return true;
    } else {
      this.router.navigate(['403']);
    }
  }

  update(remark_id: number) {
    this.remarkService.findById(remark_id).subscribe(
      data => this.router.navigate(['/remark/update', data.id])
    );
  }

  delete(remark_id: number) {
    this.remarkService.delete(remark_id).subscribe(() => {
      this.refresh();
      this.openSnackBar('Remark deleted.', 'Ok');
    }, error => {
      this.openSnackBar('Failed.', 'Ok');
    });
  }

  refresh(): void {
    window.location.reload();
  }
}
