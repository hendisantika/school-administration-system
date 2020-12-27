import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {SemesterViewComponent} from './semester-view.component';

describe('SemesterViewComponent', () => {
  let component: SemesterViewComponent;
  let fixture: ComponentFixture<SemesterViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [SemesterViewComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SemesterViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
