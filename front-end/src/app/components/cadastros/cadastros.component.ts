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
  nomeFiltro: string = '';
  codigoFilter: string = '';
  senhaFilter: string = '';

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

  setDatasSemana() {
    const hoje = new Date();
    const inicioDaSemana = new Date(hoje);
    inicioDaSemana.setDate(hoje.getDate() - hoje.getDay());

    const finalDaSemana = new Date(hoje);
    finalDaSemana.setDate(hoje.getDate() + (6 - hoje.getDay()));
    finalDaSemana.setHours(23, 59, 59, 999);

    this.dataInicial = inicioDaSemana;
    this.dataFinal = finalDaSemana;
  }

  getAll(tipo: string){
    if(this.nomeEntidade != 'Agendamentos'){
      this.consultasService.getAll(this.getRota(tipo) + '/listar').subscribe(resp => {
        if(resp){
          this.results = resp;
        }
      });
    }else{
      this.findAgendamentoBetweenDates();
    }
  }

  aplicarFiltro() {
    this.findAgendamentoBetweenDates();
  }

  aplicarFiltroPorNome(){
    this.consultasService.findByNome(this.nomeFiltro, this.rota  + '/nome?nome=').subscribe(resp => {
      if(resp) this.results = resp;
    })
  }

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
      case 'AcessarConsulta':
          nome = 'Acessar Consulta';
        break;
    }

    return nome;
  }

  pesquisarConsulta(){
    this.consultasService.findConsultaByPaciente(this.codigoFilter, this.senhaFilter).subscribe(resp => {
      this.results = resp;
    })
  }

  formatarData(data: Date): string {
    const ano = data.getFullYear();
    const mes = (data.getMonth() + 1).toString().padStart(2, '0');
    const dia = data.getDate().toString().padStart(2, '0');
    const horas = data.getHours().toString().padStart(2, '0');
    const minutos = data.getMinutes().toString().padStart(2, '0');
    const segundos = data.getSeconds().toString().padStart(2, '0');

    return `${ano}-${mes}-${dia} 00:00:00`;
  }

  findAgendamentoBetweenDates(){
    // console.log('this.dataInicial', this.dataInicial);
    // console.log('this.dataFinal', this.dataFinal)
    this.consultasService.findAgendamentoBetweenDates(this.rota + '/buscar', this.formatarData(this.dataInicial), this.formatarData(this.dataFinal)).subscribe(resp => {
      if(resp){
        this.results = resp;
      }
    });
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
        this.setDatasSemana();
        rota = '/agendamentos';
        break;
      case 'Consulta':
          rota = '/consultas';
          break;
      case 'Paciente':
          rota = '/pacientes';
          break;
      case 'AcessarConsulta':
          rota = '';
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
          { field: 'cpf', header: 'CPF', type: 'text' , isShow: true},
          { field: 'data_nascimento', header: 'Data de Nascimento', type: 'date' , isShow: true},
          { field: 'crm', header: 'CRM', type: 'text' , isShow: true},
        ];
        break;
      case 'Recepcionista':
        cols = [
          { field: 'nome_completo', header: 'Nome', type: 'text' , isShow: true},
          { field: 'telefone', header: 'Telefone', type: 'text' , isShow: true},
          { field: 'endereco', header: 'Endereço', type: 'text' , isShow: true},
          { field: 'cpf', header: 'CPF', type: 'text' , isShow: true},
          { field: 'data_nascimento', header: 'Data de Nascimento', type: 'date' , isShow: true},
        ];
        break;
      case 'Agendamento':
        cols = [
          { field: 'data_hora_inicio', header: 'Inicio', type: 'date' , isShow: true},
          { field: 'data_hora_fim', header: 'Fim', type: 'date'  , isShow: true},
          { field: 'medico', header: 'Médico', type: 'medico'  , isShow: true},
          { field: 'paciente', header: 'Paciente', type: 'paciente'  , isShow: true},
          { field: 'recepcionista', header: 'Recepcionista', type: 'recepcionista'  , isShow: true},
          { field: 'valor_consulta', header: 'Valor da Consulta', type: 'number'  , isShow: true},
        ];
        break;
      case 'Consulta':
        cols = [
          { field: 'sintomas', header: 'Sintomas', type: 'text'  , isShow: true},
          { field: 'diagnostico', header: 'Diagnóstico', type: 'text'  , isShow: true},
          { field: 'observacoes', header: 'Observações', type: 'text'  , isShow: true},
          { field: 'status_consulta', header: 'Status', type: 'status'  , isShow: true},
        ];
        break;
      case 'Paciente':
        cols = [
          { field: 'nome_completo', header: 'Nome', type: 'text' , isShow: true},
          { field: 'telefone', header: 'Telefone', type: 'text' , isShow: true},
          { field: 'endereco', header: 'Endereço', type: 'text' , isShow: true},
          { field: 'cpf', header: 'CPF', type: 'text' , isShow: true},
          { field: 'data_nascimento', header: 'Data de Nascimento', type: 'date' , isShow: true},
        ];
        break;
      case 'AcessarConsulta':
          cols = [
            { field: 'data_hora_inicio', header: 'Inicio', type: 'date' , isShow: true},
            { field: 'data_hora_fim', header: 'Fim', type: 'date'  , isShow: true},
            { field: 'recepcionista', header: 'Recepcionista', type: 'recepcionista' , isShow: true},
            { field: 'medico', header: 'Médico', type: 'medico'  , isShow: true},
            { field: 'paciente', header: 'Paciente', type: 'paciente'  , isShow: true},
            { field: 'sintomas', header: 'Sintomas', type: 'text'  , isShow: true},
            { field: 'diagnostico', header: 'Diagnóstico', type: 'text'  , isShow: true},
            { field: 'observacoes', header: 'Observações', type: 'text'  , isShow: true},
            { field: 'status_consulta', header: 'Status', type: 'text'  , isShow: true},
            { field: 'valor_consulta', header: 'Valor da Consulta', type: 'number'  , isShow: true},
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
