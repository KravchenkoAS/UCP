import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddTrpComponent } from './add-trp.component';

describe('AddTrpComponent', () => {
  let component: AddTrpComponent;
  let fixture: ComponentFixture<AddTrpComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddTrpComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddTrpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
