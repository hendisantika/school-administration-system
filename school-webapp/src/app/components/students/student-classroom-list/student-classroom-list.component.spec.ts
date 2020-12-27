import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {StudentClassroomListComponent} from './student-classroom-list.component';

describe('StudentClassroomListComponent', () => {
  let component: StudentClassroomListComponent;
  let fixture: ComponentFixture<StudentClassroomListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [StudentClassroomListComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StudentClassroomListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
