import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AcessarConsultasComponent } from './acessar-consultas.component';

describe('AcessarConsultasComponent', () => {
  let component: AcessarConsultasComponent;
  let fixture: ComponentFixture<AcessarConsultasComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AcessarConsultasComponent]
    });
    fixture = TestBed.createComponent(AcessarConsultasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
