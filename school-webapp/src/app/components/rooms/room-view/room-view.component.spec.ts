import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {RoomViewComponent} from './room-view.component';

describe('RoomViewComponent', () => {
  let component: RoomViewComponent;
  let fixture: ComponentFixture<RoomViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [RoomViewComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RoomViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
