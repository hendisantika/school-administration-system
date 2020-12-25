import {Component, OnInit} from '@angular/core';
import {Course} from 'src/app/model/course';
import {UserService} from 'src/app/service/user.service';
import {ActivatedRoute, Router} from '@angular/router';
import {CourseService} from 'src/app/service/course.service';
import {TeacherService} from 'src/app/service/teacher.service';
import {TeacherPreference} from 'src/app/model/teacherPreference';

@Component({
  selector: 'app-course-details',
  templateUrl: './course-details.component.html',
  styleUrls: ['./course-details.component.scss']
})
export class CourseDetailsComponent implements OnInit {

  currentUser: any = {};
  id: number = 0;
  course = new Course();
  isDataAvailable: boolean = false;
  preference = new TeacherPreference();

  constructor(private userService: UserService, private route: ActivatedRoute,
              private courseService: CourseService, private router: Router, private teacherService: TeacherService) {
  }

  ngOnInit() {
    this.id = this.route.snapshot.params['id'];
    this.userService.getMyInfo().toPromise().then(data => {
      this.currentUser = data;
      this.courseService.findById(this.id).subscribe(data => {
        this.course = data;
        this.teacherService.getAllTeacherPreferences(this.course.teacher.id).subscribe(data => {
          this.preference = data;
          this.isDataAvailable = true;
        });
      });
    });
  }

  goBack() {
    this.router.navigate(['course/all']);
  }

  hasSignedId() {
    return !!this.userService.currentUser;
  }
}
