import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {TimetableListComponent} from './timetable-list.component';

describe('TimetableListComponent', () => {
  let component: TimetableListComponent;
  let fixture: ComponentFixture<TimetableListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [TimetableListComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TimetableListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
