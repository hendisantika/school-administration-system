import {Component, OnInit} from '@angular/core';
import {TeacherPreference} from 'src/app/model/teacherPreference';
import {TeacherPreferenceResponseDTO} from 'src/app/dto/response/teacherPreferenceResponseDTO';
import {Router} from '@angular/router';
import {UserService} from 'src/app/service/user.service';
import {TeacherService} from 'src/app/service/teacher.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {Teacher} from 'src/app/model/teacher';
import {isAdmin, isTeacher} from 'src/app/shared/roles';

@Component({
  selector: 'app-preferences',
  templateUrl: './preferences.component.html',
  styleUrls: ['./preferences.component.scss']
})
export class PreferencesComponent implements OnInit {

  currentUser: any = {};
  isDataAvailable: boolean = false;
  preferencesOrigin = new TeacherPreference();
  preferences = new TeacherPreferenceResponseDTO();
  teacher = new Teacher();
  underModifying: Boolean = false;

  constructor(private userService: UserService, private teacherService: TeacherService, private _snackBar: MatSnackBar,
              private router: Router) {
  }

  ngOnInit() {
    this.userService.getMyInfo().toPromise().then(data => {
      this.currentUser = data;
      this.teacherService.findByUserId(this.currentUser.id).subscribe(data => {
        this.teacher = data;
        this.teacherService.getAllTeacherPreferences(this.teacher.id).subscribe(data => {
          this.preferencesOrigin = data;
          this.preferences.homeworkWeight = this.preferencesOrigin.homeworkWeight;
          this.preferences.repetitionWeight = this.preferencesOrigin.repetitionWeight;
          this.preferences.testWeight = this.preferencesOrigin.testWeight;
          this.preferences.topicTestWeight = this.preferencesOrigin.topicTestWeight;
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

  isDataChanged() {
    if (!(this.preferences.homeworkWeight == this.preferencesOrigin.homeworkWeight) ||
      !(this.preferences.topicTestWeight == this.preferencesOrigin.topicTestWeight) ||
      !(this.preferences.testWeight == this.preferencesOrigin.testWeight) ||
      !(this.preferences.repetitionWeight == this.preferencesOrigin.repetitionWeight)) return true;
    return false;
  }

  onSubmit() {
    if (this.isDataChanged) {
      this.preferences.teacher_id = this.teacher.id;
      this.teacherService.setTeacherPreferences(this.preferences).subscribe(() => {
        this.refresh();
        this.openSnackBar('Preferences updated', 'Ok');
      });
    }
  }

  refresh(): void {
    window.location.reload();
  }

  userRole() {
    if (isAdmin(this.currentUser, this.router) || isTeacher(this.currentUser, this.router) ||
      this.currentUser.id == this.teacher.teacher.id) {
      return true;
    } else {
      this.router.navigate(['403']);
    }
  }

  goBack() {
    this.underModifying = false;
    this.refresh();
  }

  modify() {
    this.underModifying = true;
  }
}
