import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {ArchivesListComponent} from './archives-list.component';

describe('ArchivesListComponent', () => {
  let component: ArchivesListComponent;
  let fixture: ComponentFixture<ArchivesListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ArchivesListComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ArchivesListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
