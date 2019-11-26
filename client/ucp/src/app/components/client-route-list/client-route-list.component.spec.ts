import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientRouteListComponent } from './client-route-list.component';

describe('ClientRouteListComponent', () => {
  let component: ClientRouteListComponent;
  let fixture: ComponentFixture<ClientRouteListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClientRouteListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientRouteListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
