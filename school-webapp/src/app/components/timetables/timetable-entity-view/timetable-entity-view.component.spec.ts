import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {TimetableEntityViewComponent} from './timetable-entity-view.component';

describe('TimetableEntityViewComponent', () => {
  let component: TimetableEntityViewComponent;
  let fixture: ComponentFixture<TimetableEntityViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [TimetableEntityViewComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TimetableEntityViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
