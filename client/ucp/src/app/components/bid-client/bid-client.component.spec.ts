import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BidClientComponent } from './bid-client.component';

describe('BidClientComponent', () => {
  let component: BidClientComponent;
  let fixture: ComponentFixture<BidClientComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BidClientComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BidClientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
