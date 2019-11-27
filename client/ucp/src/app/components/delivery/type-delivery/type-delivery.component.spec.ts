import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TypeDeliveryComponent } from './type-delivery.component';

describe('TypeDeliveryComponent', () => {
  let component: TypeDeliveryComponent;
  let fixture: ComponentFixture<TypeDeliveryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TypeDeliveryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TypeDeliveryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
