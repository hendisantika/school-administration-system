import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {HeadteacherPanelComponent} from './headteacher-panel.component';

describe('HeadteacherPanelComponent', () => {
  let component: HeadteacherPanelComponent;
  let fixture: ComponentFixture<HeadteacherPanelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [HeadteacherPanelComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HeadteacherPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
