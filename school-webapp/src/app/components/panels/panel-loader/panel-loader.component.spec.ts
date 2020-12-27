import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {PanelLoaderComponent} from './panel-loader.component';

describe('PanelLoaderComponent', () => {
  let component: PanelLoaderComponent;
  let fixture: ComponentFixture<PanelLoaderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [PanelLoaderComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PanelLoaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
