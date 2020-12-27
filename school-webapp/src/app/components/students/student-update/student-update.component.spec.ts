import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {StudentUpdateComponent} from './student-update.component';

describe('StudentUpdateComponent', () => {
  let component: StudentUpdateComponent;
  let fixture: ComponentFixture<StudentUpdateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [StudentUpdateComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StudentUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
