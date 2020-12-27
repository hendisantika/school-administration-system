import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {TimetableEntityCreateComponent} from './timetable-entity-create.component';

describe('TimetableEntityCreateComponent', () => {
  let component: TimetableEntityCreateComponent;
  let fixture: ComponentFixture<TimetableEntityCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [TimetableEntityCreateComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TimetableEntityCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
