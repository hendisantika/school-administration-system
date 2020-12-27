import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {CreateReportClassroomComponent} from './create-report-classroom.component';

describe('CreateReportClassroomComponent', () => {
  let component: CreateReportClassroomComponent;
  let fixture: ComponentFixture<CreateReportClassroomComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [CreateReportClassroomComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateReportClassroomComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
