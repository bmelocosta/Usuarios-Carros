import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IncluirUsuarioComponent } from './incluir-usuario.component';

describe('IncluirUsuarioComponent', () => {
  let component: IncluirUsuarioComponent;
  let fixture: ComponentFixture<IncluirUsuarioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [IncluirUsuarioComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(IncluirUsuarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
