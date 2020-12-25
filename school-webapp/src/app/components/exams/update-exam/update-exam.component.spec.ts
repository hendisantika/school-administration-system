import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {UpdateExamComponent} from './update-exam.component';

describe('UpdateExamComponent', () => {
  let component: UpdateExamComponent;
  let fixture: ComponentFixture<UpdateExamComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [UpdateExamComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateExamComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
