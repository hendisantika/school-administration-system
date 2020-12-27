import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {SummaryStudentComponent} from './summary-student.component';

describe('SummaryStudentComponent', () => {
  let component: SummaryStudentComponent;
  let fixture: ComponentFixture<SummaryStudentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [SummaryStudentComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SummaryStudentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
