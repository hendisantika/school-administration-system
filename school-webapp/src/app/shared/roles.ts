import {Router} from '@angular/router';
import {StudentService} from '../service/student.service';

var student = 'ROLE_STUDENT';
var teacher = 'ROLE_TEACHER';
var headteacher = 'ROLE_HEADTEACHER';
var admin = 'ROLE_ADMIN';

export function isAdmin(currentUser: any, router: Router): boolean {
  if (currentUser.authorities[0].authority + '' === admin) {
    return true;
  }
  return false;
}

export function isTeacher(currentUser: any, router: Router): boolean {
  if (currentUser.authorities[0].authority + '' === teacher ||
    currentUser.authorities[0].authority + '' === headteacher) {
    return true;
  }
  return false;

}

export function isStudent(currentUser: any, router: Router): boolean {
  if (currentUser.authorities[0].authority + '' === student) {
    return true;
  }
  return false;
}

export function isIdMatches(currentUser: any, router: Router, id: number, studentService: StudentService): boolean {
  studentService.findById(id).subscribe(data => {
    if (currentUser.id == data.student.id) {
      return true;
    }
  });
  return false;
}
