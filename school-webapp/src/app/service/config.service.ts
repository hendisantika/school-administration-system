import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ConfigService {

  private baseUrl = '/api';

  private refreshTokenUrl = this.baseUrl + '/refresh';
  private loginUrl = this.baseUrl + '/login';
  private logoutUrl = this.baseUrl + '/logout';
  private changePasswordUrl = this.baseUrl + '/changePassword';
  private userUrl = this.baseUrl + '/user';
  private whoamiUrl = this.userUrl + '/whoami';
  private allUsersUrl = this.userUrl + '/all';
  private createUrl = this.userUrl + '/create';
  private deleteUrl = this.userUrl;
  private getUserByIdUrl = this.userUrl;
  private isUsernameUniqueUrl = this.userUrl + '/username';
  private updateUserUrl = this.userUrl + '/update';
  private studentUrl = this.baseUrl + '/students';
  private studentAllUrl = this.studentUrl + '/all';
  private studentByIdUrl = this.studentUrl;
  private studentByUserIdUrl = this.studentUrl + '/user';
  private createStudentUrl = this.studentUrl + '/create';
  private updateStudentUrl = this.studentUrl + '/update';
  private deleteStudentUrl = this.studentUrl;
  private summaryStudentUrl = this.studentUrl + '/summary';
  private attendanceUrl = this.baseUrl + '/attendances';
  private makeAttendanceFormToClassroomUrl = this.attendanceUrl;
  private createAttendanceUrl = this.attendanceUrl + '/create';
  private verifyAttendanceUrl = this.attendanceUrl + '/verify';
  private getAllAttendancesByClassroomUrl = this.attendanceUrl + '/classroom';
  private allAttendanceByStudentUrl = this.attendanceUrl + '/all';
  private deleteAttendaceUrl = this.attendanceUrl;
  private classroomUrl = this.baseUrl + '/classrooms';
  private allClassroomUrl = this.classroomUrl + '/all';
  private findByHeadteacherIdUrl = this.classroomUrl + '/headteacher';
  private createClassroomUrl = this.classroomUrl + '/create';
  private updateClassroomUrl = this.classroomUrl + '/update';
  private deleteClassroomUrl = this.classroomUrl;
  private getStudentsFromClassroomUrl = this.classroomUrl + '/students';
  private setCourseUrl = this.classroomUrl + '/setCourse';
  private unsetCourseUrl = this.classroomUrl + '/unsetCourse';
  private getClassroomByIdUrl = this.classroomUrl;
  private courseUrl = this.baseUrl + '/courses';
  private getCoursesByTeacherIdUrl = this.courseUrl + '/teacher';
  private createCourseUrl = this.courseUrl + '/create';
  private updateCourseUrl = this.courseUrl + '/update';
  private setCourseToStudentUrl = this.courseUrl + '/setCourse';
  private deleteCourseUrl = this.courseUrl;
  private getCourseByIdUrl = this.courseUrl;
  private getAllCoursesUrl = this.courseUrl + '/all';
  private unsetCourseToStudentUrl = this.courseUrl + '/unsetCourse';
  private examUrl = this.baseUrl + '/exams';
  private findExamByIdUrl = this.examUrl;
  private examfindAllByStudentUrl = this.examUrl + '/student';
  private createExamUrl = this.examUrl + '/create';
  private updateExamUrl = this.examUrl + '/update';
  private deleteExamUrl = this.examUrl;
  private createExamsFromFormUrl = this.examUrl + '/form/create';
  private makeExamsFormToClassroomUrl = this.examUrl + '/form';
  private getAllExamTypeUrl = this.examUrl + '/type/all';
  private reportUrl = this.baseUrl + '/reports';
  private getSemesterResultByStudentUrl = this.reportUrl;
  private findReportByIdUrl = this.reportUrl;
  private createrReportUrl = this.reportUrl + '/create';
  private updateReportUrl = this.reportUrl + '/update';
  private deletereportUrl = this.reportUrl;
  private makeReportFormToClassroomUrl = this.reportUrl + '/form';
  private createReportsToClassroomUrl = this.reportUrl + '/form/create';
  private teacherUrl = this.baseUrl + '/teachers';
  private findAllTeacherUrl = this.teacherUrl + '/all';
  private findTeacherById = this.teacherUrl;
  private findTeacherByUserId = this.teacherUrl + '/user';
  private createTeacherUrl = this.teacherUrl + '/create';
  private updateTeacherUrl = this.teacherUrl + '/update';
  private setCourseToTeacherUrl = this.teacherUrl + '/setCourse';
  private deleteTeacherUrl = this.teacherUrl;
  private setTeacherPreferencesUrl = this.teacherUrl + '/preferences';
  private getAllTeacherPreferencesUrl = this.teacherUrl + '/preferences';
  private timetableUrl = this.baseUrl + '/timetables';
  private createTimeTableUrl = this.timetableUrl + '/create';
  private updateTimeTableUrl = this.timetableUrl + '/update';
  private deleteTimeTableUrl = this.timetableUrl;
  private findByIdTimeTableUrl = this.timetableUrl;
  private getTimeTableByStudentUrl = this.timetableUrl + '/student';
  private getTimeTableByTeacherUrl = this.timetableUrl + '/teacher';
  private getTimeTableEntitiesByCourseUrl = this.timetableUrl + '/course';
  private adminUrl = this.baseUrl + '/admin';
  private newYearUrl = this.adminUrl + '/newYear';
  private createArchiveUrl = this.adminUrl + '/createArchive';
  private getArchiveUrl = this.adminUrl + '/archives';
  private finishedUrl = this.adminUrl + '/finished';
  private getArchiveByIdUrl = this.adminUrl + '/archive';
  private headteacherUrl = this.baseUrl + '/headteacher';
  private findFailedStudentsInClassUrl = this.headteacherUrl + '/find-failed';
  private showResultByCourseUrl = this.headteacherUrl + '/show-result';
  private messageUrl = this.baseUrl + '/messages';
  private findByMessageIdUrl = this.messageUrl;
  private findAllMessagesUrl = this.messageUrl + '/all';
  private createMessageUrl = this.messageUrl + '/create';
  private updateMessageUrl = this.messageUrl + '/update';
  private deleteMessageUrl = this.messageUrl;
  private remarkUrl = this.baseUrl + '/remarks';
  private findByRemarkIdUrl = this.remarkUrl;
  private findAllRemarksUrl = this.remarkUrl + '/student';
  private createRemarkUrl = this.remarkUrl + '/create';
  private updateRemarkUrl = this.remarkUrl + '/update';
  private deleteRemarkUrl = this.remarkUrl;
  private roomUrl = this.baseUrl + '/rooms';
  private findAllRoomsUrl = this.roomUrl + '/all';
  private findByRoomUrl = this.roomUrl;
  private createRoomUrl = this.roomUrl + 'create';
  private deleteRoomUrl = this.roomUrl;

  get getRefreshTokenUrl(): string {
    return this.refreshTokenUrl;
  }

  get getLoginUrl(): string {
    return this.loginUrl;
  }

  get getLogoutUrl(): string {
    return this.logoutUrl;
  }

  get getChangePasswordUrl(): string {
    return this.changePasswordUrl;
  }

  get getWhoami(): string {
    return this.whoamiUrl;
  }

  get getAllUsersUrl(): string {
    return this.allUsersUrl;
  }

  get getCreateUrl(): string {
    return this.createUrl;
  }

  get getDeleteUrl(): string {
    return this.deleteUrl;
  }

  get getGetUserByIdUrl(): string {
    return this.getUserByIdUrl;
  }

  get getIsUsernameUniqueUrl(): string {
    return this.isUsernameUniqueUrl;
  }

  get getUpdateUserUrl(): string {
    return this.updateUserUrl;
  }

  get getStudentAllUrl(): string {
    return this.studentAllUrl;
  }

  get getStudentByUserIdUrl(): string {
    return this.studentByUserIdUrl;
  }

  get getCreateStudentUrl(): string {
    return this.createStudentUrl;
  }

  get getStudentUrl(): string {
    return this.studentByIdUrl;
  }

  get getUpdateStudentUrl(): string {
    return this.updateStudentUrl;
  }

  get getDeleteStudentUrl(): string {
    return this.deleteStudentUrl;
  }

  get getSummaryStudentUrl(): string {
    return this.summaryStudentUrl;
  }

  get getMakeAttendanceFormToClassroomUrl(): string {
    return this.makeAttendanceFormToClassroomUrl;
  }

  get getCreateAttendanceUrl(): string {
    return this.createAttendanceUrl;
  }

  get getVerifyAttendanceUrl(): string {
    return this.verifyAttendanceUrl;
  }

  get getGetAllAttendancesByClassroomUrl(): string {
    return this.getAllAttendancesByClassroomUrl;
  }

  get getAllAttendanceByStudentUrl(): string {
    return this.allAttendanceByStudentUrl;
  }

  get getDeleteAttendaceUrl(): string {
    return this.deleteAttendaceUrl;
  }

  get getAllClassroomUrl(): string {
    return this.allClassroomUrl;
  }

  get getFindByHeadteacherIdUrl(): string {
    return this.findByHeadteacherIdUrl;
  }

  get getCreateClassroomUrl(): string {
    return this.createClassroomUrl;
  }

  get getUpdateClassroomUrl(): string {
    return this.updateClassroomUrl;
  }

  get getDeleteClassroomUrl(): string {
    return this.deleteClassroomUrl;
  }

  get getGetStudentsFromClassroomUrl(): string {
    return this.getStudentsFromClassroomUrl;
  }

  get getSetCourseUrl(): string {
    return this.setCourseUrl;
  }

  get getGetClassroomByIdUrl(): string {
    return this.getClassroomByIdUrl;
  }

  get getUnsetCourseUrl(): string {
    return this.setCourseUrl;
  }

  get getCreateCourseUrl(): string {
    return this.createCourseUrl;
  }

  get getGetCoursesByTeacherIdUrl(): string {
    return this.getCoursesByTeacherIdUrl;
  }

  get getUpdateCourseUrl(): string {
    return this.updateCourseUrl;
  }

  get getSetCourseToStudentUrl(): string {
    return this.setCourseToStudentUrl;
  }

  get getDeleteCourseUrl(): string {
    return this.deleteCourseUrl;
  }

  get getGetCourseByIdUrl(): string {
    return this.getCourseByIdUrl;
  }

  get getGetAllCoursesUrl(): string {
    return this.getAllCoursesUrl;
  }

  get getUnsetCourseToStudentUrl(): string {
    return this.unsetCourseToStudentUrl;
  }

  get getExamfindAllByStudentUrl(): string {
    return this.examfindAllByStudentUrl;
  }

  get getFindExamByIdUrl(): string {
    return this.findExamByIdUrl;
  }

  get getCreateExamUrl(): string {
    return this.createExamUrl;
  }

  get getUpdateExamUrl(): string {
    return this.updateExamUrl;
  }

  get getDeleteExamUrl(): string {
    return this.deleteExamUrl;
  }

  get getCreateExamsFromFormUrl(): string {
    return this.createExamsFromFormUrl;
  }

  get getMakeExamsFormToClassroomUrl(): string {
    return this.makeExamsFormToClassroomUrl;
  }

  get getGetAllExamTypeUrl(): string {
    return this.getAllExamTypeUrl;
  }

  get getGetSemesterResultByStudentUrl(): string {
    return this.getSemesterResultByStudentUrl;
  }

  get getFindReportByIdUrl(): string {
    return this.findReportByIdUrl;
  }

  get getCreateReportUrl(): string {
    return this.createrReportUrl;
  }

  get getUpdateReportUrl(): string {
    return this.updateReportUrl;
  }

  get getDeleteReportUrl(): string {
    return this.deletereportUrl;
  }

  get getMakeReportFormToClassroomUrl(): string {
    return this.makeReportFormToClassroomUrl;
  }

  get getCreateReportsToClassroomUrl(): string {
    return this.createReportsToClassroomUrl;
  }

  get getFindAllTeacherUrl(): string {
    return this.findAllTeacherUrl;
  }

  get getFindTeacherById(): string {
    return this.findTeacherById;
  }

  get getFindTeacherByUserId(): string {
    return this.findTeacherByUserId;
  }

  get getCreateTeacherUrl(): string {
    return this.createTeacherUrl;
  }

  get getUpdateTeacherUrl(): string {
    return this.updateTeacherUrl;
  }

  get getSetCourseToTeacherUrl(): string {
    return this.setCourseToTeacherUrl;
  }

  get getDeleteTeacherUrl(): string {
    return this.deleteTeacherUrl;
  }

  get getSetTeacherPreferencesUrl(): string {
    return this.setTeacherPreferencesUrl;
  }

  get getGetAllTeacherPreferencesUrl(): string {
    return this.getAllTeacherPreferencesUrl;
  }

  get getFindByIdTimeTableUrl(): string {
    return this.findByIdTimeTableUrl;
  }

  get getCreateTimeTableUrl(): string {
    return this.createTimeTableUrl;
  }

  get getUpdateTimeTableUrl(): string {
    return this.updateTimeTableUrl;
  }

  get getDeleteTimeTableUrl(): string {
    return this.deleteTimeTableUrl;
  }

  get getGetTimeTableByStudentUrl(): string {
    return this.getTimeTableByStudentUrl;
  }

  get getGetTimeTableByTeacherUrl(): string {
    return this.getTimeTableByTeacherUrl;
  }

  get getGetTimeTableEntitiesByCourseUrl(): string {
    return this.getTimeTableEntitiesByCourseUrl;
  }

  get getNewYearUrl(): string {
    return this.newYearUrl;
  }

  get getCreateArchiveUrl(): string {
    return this.createArchiveUrl;
  }

  get getGetArchiveUrl(): string {
    return this.getArchiveUrl;
  }

  get getFinishedUrl(): string {
    return this.finishedUrl;
  }

  get getGetArchiveByIdUrl(): string {
    return this.getArchiveByIdUrl;
  }

  get getFindFailedStudentsInClassUrl(): string {
    return this.findFailedStudentsInClassUrl;
  }

  get getShowResultByCourseUrl(): string {
    return this.showResultByCourseUrl;
  }

  get getFindAllMessagesUrl(): string {
    return this.findAllMessagesUrl;
  }

  get getFindByMessageIdUrl(): string {
    return this.findByMessageIdUrl;
  }

  get getCreateMessageUrl(): string {
    return this.createMessageUrl;
  }

  get getUpdateMessageUrl(): string {
    return this.updateMessageUrl;
  }

  get getDeleteMessageUrl(): string {
    return this.deleteMessageUrl;
  }

  get getFindAllRemarksUrl(): string {
    return this.findAllRemarksUrl;
  }

  get getFindByRemarkIdUrl(): string {
    return this.findByRemarkIdUrl;
  }

  get getCreateRemarkUrl(): string {
    return this.createRemarkUrl;
  }

  get getUpdateRemarkUrl(): string {
    return this.updateRemarkUrl;
  }

  get getDeleteRemarkUrl(): string {
    return this.deleteRemarkUrl;
  }

  get getFindAllRoomsUrl(): string {
    return this.findAllRoomsUrl;
  }

  get getFindByRoomUrl(): string {
    return this.findByRoomUrl;
  }

  get getCreateRoomUrl(): string {
    return this.createRoomUrl;
  }

  get getDeleteRoomUrl(): string {
    return this.deleteRoomUrl;
  }
}
