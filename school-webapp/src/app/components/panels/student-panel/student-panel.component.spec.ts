import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {StudentPanelComponent} from './student-panel.component';

describe('StudentPanelComponent', () => {
  let component: StudentPanelComponent;
  let fixture: ComponentFixture<StudentPanelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [StudentPanelComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StudentPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
