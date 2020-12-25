import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {SetCourseComponent} from './set-course.component';

describe('SetCourseComponent', () => {
  let component: SetCourseComponent;
  let fixture: ComponentFixture<SetCourseComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [SetCourseComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SetCourseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
