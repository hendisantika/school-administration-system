import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {TimetableEntityUpdateComponent} from './timetable-entity-update.component';

describe('TimetableEntityUpdateComponent', () => {
  let component: TimetableEntityUpdateComponent;
  let fixture: ComponentFixture<TimetableEntityUpdateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [TimetableEntityUpdateComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TimetableEntityUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
