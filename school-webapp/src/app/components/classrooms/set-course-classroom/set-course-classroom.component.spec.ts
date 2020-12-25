import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {SetCourseClassroomComponent} from './set-course-classroom.component';

describe('SetCourseClassroomComponent', () => {
  let component: SetCourseClassroomComponent;
  let fixture: ComponentFixture<SetCourseClassroomComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [SetCourseClassroomComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SetCourseClassroomComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
