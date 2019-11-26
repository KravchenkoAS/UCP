import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BidClientDetailsComponent } from './bid-client-details.component';

describe('BidClientDetailsComponent', () => {
  let component: BidClientDetailsComponent;
  let fixture: ComponentFixture<BidClientDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BidClientDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BidClientDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
