import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CarroPopupComponent } from './carro-popup.component';

describe('CarroPopupComponent', () => {
  let component: CarroPopupComponent;
  let fixture: ComponentFixture<CarroPopupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CarroPopupComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CarroPopupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
