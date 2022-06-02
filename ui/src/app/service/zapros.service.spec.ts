import { TestBed } from '@angular/core/testing';

import { ZaprosService } from './zapros.service';

describe('ZaprosService', () => {
  let service: ZaprosService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ZaprosService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
