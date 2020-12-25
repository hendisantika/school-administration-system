import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {CreateExamClassroomComponent} from './create-exam-classroom.component';

describe('CreateExamClassroomComponent', () => {
  let component: CreateExamClassroomComponent;
  let fixture: ComponentFixture<CreateExamClassroomComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [CreateExamClassroomComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateExamClassroomComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
