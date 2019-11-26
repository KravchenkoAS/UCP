import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RouteDitailsComponent } from './route-ditails.component';

describe('RouteDitailsComponent', () => {
  let component: RouteDitailsComponent;
  let fixture: ComponentFixture<RouteDitailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RouteDitailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RouteDitailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
