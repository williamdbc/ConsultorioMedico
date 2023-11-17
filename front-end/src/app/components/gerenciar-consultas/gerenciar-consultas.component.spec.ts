import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GerenciarConsultasComponent } from './gerenciar-consultas.component';

describe('GerenciarConsultasComponent', () => {
  let component: GerenciarConsultasComponent;
  let fixture: ComponentFixture<GerenciarConsultasComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GerenciarConsultasComponent]
    });
    fixture = TestBed.createComponent(GerenciarConsultasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
