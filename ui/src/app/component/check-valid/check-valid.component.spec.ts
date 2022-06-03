import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CheckValidComponent } from './check-valid.component';

describe('CheckValidComponent', () => {
  let component: CheckValidComponent;
  let fixture: ComponentFixture<CheckValidComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CheckValidComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CheckValidComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
