import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {CreateAttendanceComponent} from './create-attendance.component';

describe('CreateAttendanceComponent', () => {
  let component: CreateAttendanceComponent;
  let fixture: ComponentFixture<CreateAttendanceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [CreateAttendanceComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateAttendanceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
