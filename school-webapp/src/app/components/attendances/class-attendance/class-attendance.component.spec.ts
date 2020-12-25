import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {ClassAttendanceComponent} from './class-attendance.component';

describe('ClassAttendanceComponent', () => {
  let component: ClassAttendanceComponent;
  let fixture: ComponentFixture<ClassAttendanceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ClassAttendanceComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClassAttendanceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
