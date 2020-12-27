import {Component, OnInit} from '@angular/core';
import {Student} from 'src/app/model/student';
import {StudentResponseDTO} from 'src/app/dto/response/studentResponseDTO';
import {UserService} from 'src/app/service/user.service';
import {StudentService} from 'src/app/service/student.service';
import {Observable} from 'rxjs';
import {Classroom} from 'src/app/model/classroom';
import {ActivatedRoute, Router} from '@angular/router';
import {ClassroomService} from 'src/app/service/classroom.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {isAdmin, isIdMatches, isTeacher} from 'src/app/shared/roles';

@Component({
  selector: 'app-student-update',
  templateUrl: './student-update.component.html',
  styleUrls: ['./student-update.component.scss']
})
export class StudentUpdateComponent implements OnInit {

  currentUser: any = {}
  id: number;
  student = new Student();
  response = new StudentResponseDTO();
  isDataAvailable: boolean = false;
  classrooms: Observable<Classroom[]>;
  selectedOption: any = {};
  selectedOptionGender: any = {};
  genders: string[] = ['Male', 'Female', 'Other'];

  constructor(private userService: UserService, private studentService: StudentService, private router: Router,
              private route: ActivatedRoute, private classroomService: ClassroomService, private _snackBar: MatSnackBar) {
  }

  ngOnInit() {
    this.id = this.route.snapshot.params['id'];
    this.userService.getMyInfo().toPromise().then(data => {
      this.currentUser = data;
      this.studentService.findById(this.id).subscribe(data => {
        this.student = data;
        this.classroomService.findById(data.classroom.id).subscribe(data => {
          this.classroomService.findAll().subscribe(data => {
            this.classrooms = data;
            this.isDataAvailable = true;
          });
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
    if (!this.response.address
      || !this.response.parent1Name
      || !this.response.parent2Name
      || !this.response.parent1Phone
      || !this.response.parent2Phone
      || !this.response.dateOfBirth
      || !this.response.educationId
      || !this.response.start_year
      || !this.response.healthCareId
      || !this.response.classroom_id
      || !this.response.gender) return true;
    return false;
  }

  submit() {
    if (this.isDataChanged) {
      if (!this.selectedOptionGender)
        if (!this.response.address) this.response.address = this.student.address;
      if (!this.response.parent1Name) this.response.parent1Name = this.student.parent1Name;
      if (!this.response.parent2Name) this.response.parent2Name = this.student.parent2Name;
      if (!this.response.parent1Phone) this.response.parent1Phone = this.student.parent1Phone;
      if (!this.response.parent2Phone) this.response.parent2Phone = this.student.parent2Phone;
      if (!this.response.dateOfBirth) this.response.dateOfBirth = this.student.dateOfBirth;
      if (!this.response.educationId) this.response.educationId = this.student.educationId;
      if (!this.response.start_year) this.response.start_year = this.student.start_year;
      if (!this.response.healthCareId) this.response.healthCareId = this.student.healthCareId;
      if (!this.selectedOption) this.response.classroom_id = this.student.classroom.id;
      else this.response.classroom_id = Number(this.selectedOption.id);
      if (!this.selectedOptionGender) this.response.gender = this.student.gender;
      else this.response.gender = this.selectedOptionGender;
      this.studentService.update(this.id, this.response).subscribe(() => {
        this.openSnackBar('Student updated.', 'Ok');
        this.refresh();
      }, error => {
        this.openSnackBar('Failed.', 'Ok');
      });
    }
  }

  goBack() {
    if (this.currentUser.authorities[0].authority + '' === 'ROLE_ADMIN') {
      this.router.navigate(['/user/all']);
    } else {
      this.router.navigate(['/home']);
    }
  }

  userUpdate() {
    this.userService.getById(this.student.student.id).subscribe(data => {
      this.student = data;
      this.response = new StudentResponseDTO();
      this.selectedOption = {};
    }, error => {
      this.openSnackBar('Failed.', 'Ok');
    });
  }

  refresh() {
    this.userService.getById(this.student.student.id).subscribe(data => {
      this.student = data;
    });
    this.response = new StudentResponseDTO();
    this.selectedOption = {};
  }

  userRole() {
    if (isAdmin(this.currentUser, this.router) || isTeacher(this.currentUser, this.router) ||
      isIdMatches(this.currentUser, this.router, this.student.id, this.studentService)) {
      return true;
    } else {
      this.router.navigate(['403']);
    }
  }
}
