import { TestBed } from '@angular/core/testing';

import { TypeDeliveryService } from './type-delivery.service';

describe('TypeDeliveryService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TypeDeliveryService = TestBed.get(TypeDeliveryService);
    expect(service).toBeTruthy();
  });
});
