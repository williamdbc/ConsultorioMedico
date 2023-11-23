import { ConsultasService } from '../../services/consultas.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ConfirmationService } from 'primeng/api';

@Component({
  selector: 'cadastros',
  templateUrl: './cadastros.component.html',
  styleUrls: ['./cadastros.component.scss']
})
export class CadastrosComponent implements OnInit{

  cols: any[] = [];
  results: any[] = [];
  nomeEntidade: string = "";
  atributos: string[] = ['nome', 'teste'];
  openDialog: boolean = false;
  idEntidade: number = 0;
  rota: string = '';
  dataInicial: Date = new Date();
  dataFinal: Date = new Date();
  tipo: string = '';

  constructor(private route: ActivatedRoute,
              private router: Router,
              private consultasService: ConsultasService,
              private confirmationService: ConfirmationService
    ) { }

  ngOnInit(): void {
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
    this.route.params.subscribe(params => {
      this.nomeEntidade = this.getNome(params['tipo']);
      this.cols = this.getColsByTipoEnt(params['tipo']);
      this.rota = this.getRota(params['tipo']);
      this.tipo = params['tipo'];
      this.getAll(params['tipo'])
    });

  }

  getAll(tipo: string){
    this.consultasService.getAll(this.getRota(tipo) + '/listar').subscribe(resp => {
      if(resp){
        this.results = resp;
      }
    });
  }

  aplicarFiltro(){}

  getNome(tipo: string): string{
    let nome = '';
    switch(tipo){
      case 'Medico':
        nome = 'Médicos'
        break;
      case 'Recepcionista':
        nome = 'Recepcionistas'
        break;
      case 'Agendamento':
        nome = 'Agendamentos';
        break;
      case 'Consulta':
        nome = 'Consultas';
        break;
      case 'Paciente':
        nome = 'Pacientes';
        break;
    }

    return nome;
  }

  getRota(tipo: string): string{
    let rota = '';
    console.log('tipo', tipo)
    switch(tipo){
      case 'Medico':
        rota = '/medicos'
        break;
      case 'Recepcionista':
        rota = '/recepcionistas'
        break;
      case 'Agendamento':
        rota = '/agendamentos';
        break;
      case 'Consulta':
          rota = '/consultas';
          break;
      case 'Paciente':
          rota = '/pacientes';
          break;
    }

    return rota;
  }

  getColsByTipoEnt(tipo: string): any[]{
    let cols: any = [];
    switch(tipo){
      case 'Medico':
        cols = [
          { field: 'nome_completo', header: 'Nome', type: 'text' , isShow: true},
          { field: 'telefone', header: 'Telefone', type: 'text' , isShow: true},
          { field: 'endereco', header: 'Endereço', type: 'text' , isShow: true},
          { field: 'CPF', header: 'CPF', type: 'text' , isShow: true},
          { field: 'data_nascimento', header: 'Data de Nascimento', type: 'date' , isShow: true},
          { field: 'CRM', header: 'CRM', type: 'text' , isShow: true},
        ];
        break;
      case 'Recepcionista':
        cols = [
          { field: 'nome_completo', header: 'Nome', type: 'text' , isShow: true},
          { field: 'telefone', header: 'Telefone', type: 'text' , isShow: true},
          { field: 'endereco', header: 'Endereço', type: 'text' , isShow: true},
          { field: 'CPF', header: 'CPF', type: 'text' , isShow: true},
          { field: 'data_nascimento', header: 'Data de Nascimento', type: 'date' , isShow: true},
        ];
        break;
      case 'Agendamento':
        cols = [
          { field: 'data_hora_inicial', header: 'Hora Inicial', type: 'date' , isShow: true},
          { field: 'data_hora_fim', header: 'Data Final', type: 'date'  , isShow: true},
          { field: 'valor_consulta', header: 'Valor da Consulta', type: 'number'  , isShow: true},
        ];
        break;
      case 'Consulta':
        cols = [
          { field: 'sintomas', header: 'Sintomas', type: 'text'  , isShow: true},
          { field: 'diagnostico', header: 'Diagnóstico', type: 'text'  , isShow: true},
          { field: 'status_consulta', header: 'Status', type: 'text'  , isShow: true},
        ];
        break;
      case 'Paciente':
        cols = [
          { field: 'nome_completo', header: 'Nome', type: 'text' , isShow: true},
          { field: 'telefone', header: 'Telefone', type: 'text' , isShow: true},
          { field: 'endereco', header: 'Endereço', type: 'text' , isShow: true},
          { field: 'CPF', header: 'CPF', type: 'text' , isShow: true},
          { field: 'data_nascimento', header: 'Data de Nascimento', type: 'date' , isShow: true},
        ];
        break;
    }

    console.log('cols', cols)
    return cols;
  }

  setEntidade(entity: any, newEntity: any) {
    for (const key in entity) {
      if (newEntity.hasOwnProperty(key)) {
        entity[key] = newEntity[key];
      }
    }
  }

  processarFormulario(event: any){
    let item;
    if(this.results && this.results.length > 0){
      for (const key in event) {
        if (key.startsWith('id')) {
          item = this.results.filter(x => x[key] == event[key])[0];
          // const index = this.results.indexOf(item);
        }
      }
    }

    if(!item){
      this.results.push(event);
    }else{
      this.setEntidade(item, event);
    }

    this.openDialog = false;
    this.idEntidade = 0;
  }

  showConfirmationToDelete(entity: any): void {
    this.confirmationService.confirm({
      message: 'Tem certeza que deseja excluir esta entidade?',
      accept: () => {
        // O código que será executado quando o usuário confirmar a exclusão
        this.deletarEntidade(entity);
      },
      reject: () => {
        // O código que será executado quando o usuário cancelar a exclusão (opcional)
      }
    });
  }

  deletarEntidade(entity: any){
    var idValue = this.getIdEntidade(entity);
    this.rota = this.getRota(this.tipo);
    console.log('this.rota', this.rota)
    this.consultasService.delete(idValue, this.rota + '/excluir').subscribe(resp => {
      this.setResults(entity);
    });
  }

  setResults(entity: any){
    for (const key in entity) {
      if (key.startsWith('id')) {
        this.results = this.results.filter(x => x[key] != entity[key]);
      }
    }
  }

  getIdEntidade(entity: any): any{
    for (const key in entity) {
      if (key.startsWith('id')) {
        const idValue = entity[key];
        return idValue;
      }
    }

    return null;
  }

  editarEntidade(entity: any){
    this.idEntidade = this.getIdEntidade(entity);
    this.rota = this.getRota(this.tipo);
    this.openDialog = true;
  }

  openModal(){
    //this.rota = this.getRota(this.nomeEntidade) + '/adicionar';
    this.idEntidade = 0;
    this.openDialog = true;
  }


}
