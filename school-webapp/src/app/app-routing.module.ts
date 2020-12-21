import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from './components/login/login.component';
import {NotFoundComponent} from './components/not-found/not-found.component';
import {ForbiddenComponent} from './components/forbidden/forbidden.component';
import {HomeComponent} from './components/home/home.component';
import {AdminPanelComponent} from './components/panels/admin-panel/admin-panel.component';
import {TeacherPanelComponent} from './components/panels/teacher-panel/teacher-panel.component';
import {StudentPanelComponent} from './components/panels/student-panel/student-panel.component';
import {HeadteacherPanelComponent} from './components/panels/headteacher-panel/headteacher-panel.component';
import {UserListComponent} from './components/users/user-list/user-list.component';
import {StudentDetailsComponent} from './components/students/student-details/student-details.component';
import {StudentCreateComponent} from './components/students/student-create/student-create.component';
import {TeacherCreateComponent} from './components/teachers/teacher-create/teacher-create.component';
import {ClassroomListComponent} from './components/classrooms/classroom-list/classroom-list.component';
import {ClassroomCreateComponent} from './components/classrooms/classroom-create/classroom-create.component';
import {UserUpdateComponent} from './components/users/user-update/user-update.component';
import {StudentUpdateComponent} from './components/students/student-update/student-update.component';
import {CourseListComponent} from './components/courses/course-list/course-list.component';
import {CourseCreateComponent} from './components/courses/course-create/course-create.component';
import {CourseDetailsComponent} from './components/courses/course-details/course-details.component';
import {SummaryStudentComponent} from './components/students/summary-student/summary-student.component';
import {TeacherUpdateComponent} from './components/teachers/teacher-update/teacher-update.component';
import {SetCourseClassroomComponent} from './components/classrooms/set-course-classroom/set-course-classroom.component';
import {SetCourseComponent} from './components/courses/set-course/set-course.component';
import {CourseUpdateComponent} from './components/courses/course-update/course-update.component';
import {StudentClassroomListComponent} from './components/students/student-classroom-list/student-classroom-list.component';
import {TimetableEntityCreateComponent} from './components/timetables/timetable-entity-create/timetable-entity-create.component';
import {TimetableEntityUpdateComponent} from './components/timetables/timetable-entity-update/timetable-entity-update.component';
import {TimetableEntityViewComponent} from './components/timetables/timetable-entity-view/timetable-entity-view.component';
import {TimetableListComponent} from './components/timetables/timetable-list/timetable-list.component';
import {CreateExamClassroomComponent} from './components/exams/create-exam-classroom/create-exam-classroom.component';
import {CreateExamComponent} from './components/exams/create-exam/create-exam.component';
import {UpdateExamComponent} from './components/exams/update-exam/update-exam.component';
import {ExamListComponent} from './components/exams/exam-list/exam-list.component';
import {ClassroomUpdateComponent} from './components/classrooms/classroom-update/classroom-update.component';
import {SemesterViewComponent} from './components/reports/semester-view/semester-view.component';
import {CreateReportComponent} from './components/reports/create-report/create-report.component';
import {UpdateReportComponent} from './components/reports/update-report/update-report.component';
import {CreateReportClassroomComponent} from './components/reports/create-report-classroom/create-report-classroom.component';
import {CreateAttendanceComponent} from './components/attendances/create-attendance/create-attendance.component';
import {ViewAttendanceComponent} from './components/attendances/view-attendance/view-attendance.component';
import {ClassAttendanceComponent} from './components/attendances/class-attendance/class-attendance.component';
import {ArchivesListComponent} from './components/admins/archives-list/archives-list.component';
import {ArchiveDetailsComponent} from './components/admins/archive-details/archive-details.component';
import {AdministrationComponent} from './components/admins/administration/administration.component';
import {CreateMessageComponent} from './components/messages/create-message/create-message.component';
import {UpdateMessageComponent} from './components/messages/update-message/update-message.component';
import {RemarkCreateComponent} from './components/remarks/remark-create/remark-create.component';
import {RemarkUpdateComponent} from './components/remarks/remark-update/remark-update.component';
import {RemarkListComponent} from './components/remarks/remark-list/remark-list.component';
import {StatisticsComponent} from './components/headteachers/statistics/statistics.component';
import {PreferencesComponent} from './components/teachers/preferences/preferences.component';
import {RoomCreateComponent} from './components/rooms/room-create/room-create.component';
import {RoomViewComponent} from './components/rooms/room-view/room-view.component';

const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'home',
    component: HomeComponent
  },
  /* ----- USERS PANEL ----- */
  {
    path: 'admin',
    component: AdminPanelComponent
  },
  {
    path: 'teacher',
    component: TeacherPanelComponent
  },
  {
    path: 'student',
    component: StudentPanelComponent
  },
  {
    path: 'headteacher',
    component: HeadteacherPanelComponent
  },
  /* ----- USER ----- */
  {
    path: 'user/all',
    component: UserListComponent
  },
  {
    path: 'user/update/:id',
    component: UserUpdateComponent
  },

  /* ----- STUDENT -----*/
  {
    path: 'student/details/:id',
    component: StudentDetailsComponent
  },
  {
    path: 'student/create',
    component: StudentCreateComponent
  },
  {
    path: 'student/update/:id',
    component: StudentUpdateComponent
  },
  {
    path: 'student/summary/:id',
    component: SummaryStudentComponent
  },
  {
    path: 'student/classroom/:id',
    component: StudentClassroomListComponent
  },
  /* ----- TEACHER ----- */
  {
    path: 'teacher/create',
    component: TeacherCreateComponent
  },
  {
    path: 'teacher/update/:id',
    component: TeacherUpdateComponent
  },
  {
    path: 'teacher/preferences/:id',
    component: PreferencesComponent
  },
  /* ----- CLASSROOM ----- */
  {
    path: 'classroom/all',
    component: ClassroomListComponent
  },
  {
    path: 'classroom/create',
    component: ClassroomCreateComponent
  },
  {
    path: 'classroom/update/:id',
    component: ClassroomUpdateComponent
  },
  {
    path: 'classroom/setCourse/:id',
    component: SetCourseClassroomComponent
  },
  /* ----- COURSE ----- */
  {
    path: 'course/all',
    component: CourseListComponent
  },
  {
    path: 'course/create',
    component: CourseCreateComponent
  },
  {
    path: 'course/details/:id',
    component: CourseDetailsComponent
  },
  {
    path: 'course/update/:id',
    component: CourseUpdateComponent
  },
  {
    path: 'course/setCourse/:id',
    component: SetCourseComponent
  },
  /* ----- TIMETABLE ----- */
  {
    path: 'timetable/create/:id',
    component: TimetableEntityCreateComponent
  },
  {
    path: 'timetable/update/:id',
    component: TimetableEntityUpdateComponent
  },
  {
    path: 'timetable/view/:id',
    component: TimetableEntityViewComponent
  },
  {
    path: 'timetable/course/:id',
    component: TimetableListComponent
  },
  /* ----- EXAM ----- */
  {
    path: 'exam/create/:id',
    component: CreateExamComponent
  },
  {
    path: 'exam/update/:id',
    component: UpdateExamComponent
  },
  {
    path: 'exam/classroom/:id',
    component: CreateExamClassroomComponent
  },
  {
    path: 'exam/list/:id',
    component: ExamListComponent
  },
  /* ----- REPORT ----- */
  {
    path: 'report/student/:id',
    component: SemesterViewComponent
  },
  {
    path: 'report/create/:id',
    component: CreateReportComponent
  },
  {
    path: 'report/update/:id',
    component: UpdateReportComponent
  },
  {
    path: 'report/classroom/:id',
    component: CreateReportClassroomComponent
  },
  /* ----- ATTENDANCES ----- */
  {
    path: 'attendance/create/:id',
    component: CreateAttendanceComponent
  },
  {
    path: 'attendance/student/:id',
    component: ViewAttendanceComponent
  },
  {
    path: 'attendance/classroom/:id',
    component: ClassAttendanceComponent
  },
  /* ----- ARCHIVES ----- */
  {
    path: 'archive/all',
    component: ArchivesListComponent
  },
  {
    path: 'archive/:id',
    component: ArchiveDetailsComponent
  },
  {
    path: 'admin/control',
    component: AdministrationComponent
  },
  /* ----- MESSAGES ----- */
  {
    path: 'message/create',
    component: CreateMessageComponent
  },
  {
    path: 'message/update/:id',
    component: UpdateMessageComponent
  },
  /* ----- REMARK ----- */
  {
    path: 'remark/create',
    component: RemarkCreateComponent
  },
  {
    path: 'remark/update/:id',
    component: RemarkUpdateComponent
  },
  {
    path: 'remark/:id',
    component: RemarkListComponent
  },
  {
    path: 'statistics/:id',
    component: StatisticsComponent
  },
  {
    path: 'rooms/create',
    component: RoomCreateComponent
  },
  {
    path: 'rooms/all',
    component: RoomViewComponent
  },
  /* ----- DEFAULT ----- */
  {
    path: '404',
    component: NotFoundComponent
  },
  {
    path: '403',
    component: ForbiddenComponent
  },
  {
    path: '**',
    redirectTo: '/home'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: []
})
export class AppRoutingModule {
}
