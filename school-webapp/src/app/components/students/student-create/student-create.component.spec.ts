import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {StudentCreateComponent} from './student-create.component';

describe('StudentCreateComponent', () => {
  let component: StudentCreateComponent;
  let fixture: ComponentFixture<StudentCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [StudentCreateComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StudentCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
