import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BidClientListComponent } from './bid-client-list.component';

describe('BidClientListComponent', () => {
  let component: BidClientListComponent;
  let fixture: ComponentFixture<BidClientListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BidClientListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BidClientListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
