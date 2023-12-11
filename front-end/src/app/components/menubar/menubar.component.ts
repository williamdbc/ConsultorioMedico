import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'menubar',
  templateUrl: './menubar.component.html',
  styleUrls: ['./menubar.component.scss']
})
export class MenubarComponent implements OnInit{

  items: any | undefined;

  constructor(private router: Router){

  }
  ngOnInit(){
    this.items = [
      {
          label: 'Cadastros',
          icon: 'pi pi-fw pi-file',
          items: [
              {
                  label: 'Médicos',
                  icon: 'pi pi-fw pi-plus',
                  command: () => {
                    this.router.navigate(['/cadastros/Medico']);
                }
              },
              {
                  label: 'Recepcionistas',
                  icon: 'pi pi-fw pi-trash',
                  command: () => {
                    this.router.navigate(['/cadastros/Recepcionista']);
                }
              },
              {
                  label: 'Agendamentos',
                  icon: 'pi pi-fw pi-external-link',
                  command: () => {
                    this.router.navigate(['/cadastros/Agendamento']);
                }
              },
              {
                label: 'Pacientes',
                icon: 'pi pi-fw pi-external-link',
                command: () => {
                  this.router.navigate(['/cadastros/Paciente']);
                },
              },
              {
                label: 'Consultas',
                icon: 'pi pi-fw pi-external-link',
                command: () => {
                  this.router.navigate(['/cadastros/Consulta']);
                }
              }
          ]
      },
      {
        label: 'Gerenciar Consultas',
        icon: 'pi pi-cog',
        command: () => {
          this.router.navigate(['/gerenciarConsultas']);
        }
      },
      {
        label: 'Acessar Consultas',
        icon: 'pi pi-cog',
        command: () => {
          this.router.navigate(['/cadastros/AcessarConsulta']);
        }
      }
    ];

  }
}
