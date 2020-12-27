import {Component, OnInit} from '@angular/core';
import {UserService} from 'src/app/service/user.service';
import {Router} from '@angular/router';
import {StudentService} from 'src/app/service/student.service';
import {StudentResponseDTO} from 'src/app/dto/response/studentResponseDTO';
import {Observable} from 'rxjs';
import {Classroom} from 'src/app/model/classroom';
import {ClassroomService} from 'src/app/service/classroom.service';
import {UserResponseDTO} from 'src/app/dto/response/userResponseDTO';
import {MatSnackBar} from '@angular/material/snack-bar';
import {isAdmin} from 'src/app/shared/roles';

@Component({
  selector: 'app-student-create',
  templateUrl: './student-create.component.html',
  styleUrls: ['./student-create.component.scss']
})
export class StudentCreateComponent implements OnInit {

  user = new UserResponseDTO();
  student = new StudentResponseDTO();
  currentUser: any = {};
  userSubmitted: boolean = false;
  isDataAvailable: boolean = false;
  classrooms: Observable<Classroom[]>;
  selectedOption: any = {};
  selectedOptionGender: any = {};
  genders: string[] = ['Male', 'Female', 'Other'];

  constructor(private userService: UserService, private router: Router, private _snackBar: MatSnackBar,
              private studentService: StudentService, private classroomService: ClassroomService) {
  }

  ngOnInit() {
    this.userService.getMyInfo().toPromise().then(data => {
      this.currentUser = data;
      this.classroomService.findAll().subscribe(data => {
        this.classrooms = data;
        this.isDataAvailable = true;
      });
    });
  }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action, {
      duration: 2000,
    });
  }

  onUserSubmit() {
    this.user.role = 'ROLE_STUDENT';
    this.student.username = this.user.username;
    this.userService.create(this.user).subscribe(() => {
      this.openSnackBar('Student created.', 'Ok');
    }, error => {
      this.openSnackBar('Failed.', 'Ok');
    });
    this.userSubmitted = true;
  }

  onStudentSubmit() {
    this.student.classroom_id = Number(this.selectedOption.id);
    this.student.gender = this.selectedOptionGender;
    this.studentService.create(this.student).subscribe(() => {

    }, error => {
      this.openSnackBar('Failed.', 'Ok');
    });
    this.refresh();
  }

  refresh() {
    this.user = new UserResponseDTO();
    this.student = new StudentResponseDTO();
    this.userSubmitted = false;
    this.selectedOption = {};
  }

  goBack() {
    this.router.navigate(['/user/all']);
  }

  userRole() {
    if (isAdmin(this.currentUser, this.router)) {
      return true;
    } else {
      this.router.navigate(['403']);
    }
  }
}
