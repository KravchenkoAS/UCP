import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MyTrpComponent } from './my-trp.component';

describe('MyTrpComponent', () => {
  let component: MyTrpComponent;
  let fixture: ComponentFixture<MyTrpComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MyTrpComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MyTrpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
