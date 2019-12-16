import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CarrierTransportComponent } from './carrier-transport.component';

describe('CarrierTransportComponent', () => {
  let component: CarrierTransportComponent;
  let fixture: ComponentFixture<CarrierTransportComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CarrierTransportComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CarrierTransportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
