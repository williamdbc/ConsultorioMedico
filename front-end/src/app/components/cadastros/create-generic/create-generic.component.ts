import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ConsultasService } from 'src/app/services/consultas.service';

@Component({
  selector: 'create-generic',
  templateUrl: './create-generic.component.html',
  styleUrls: ['./create-generic.component.scss']
})
export class CreateGenericComponent implements OnInit{
  @Input() atributos: any[] = [];
  @Input() idEntidade: any;
  @Output() formularioEnviado = new EventEmitter<any>();
  @Input() rota: string = '';
  @Input() nomeEntidade: string = '';
  @Input() disabled: boolean = false;

  public form:FormGroup;
  submitted: boolean = false;
  atores: any[] = [];
  itens: any[] = [];
  diretores: any[] = [];
  classes: any[] = [];
  titulos: any[] = [];
  pacientes: any[] = [];
  medicos: any[] = [];
  recepcionistas: any[] = [];

  selectedAtores: any[] = [];
  selectedItens: any[] = [];
  selectedDiretor: any;
  selectedClasse: any;
  selectedTitulo: any;
  selectedMedico: any;
  selectedPaciente: any;
  selectedRecepcionista: any;

  entidade: any;

  constructor(private fb:FormBuilder,
              private consultasService: ConsultasService) {
      this.form = fb.group({});
  }

  ngOnInit() {
    console.log('nomeEntidade', this.nomeEntidade);
    this.form = this.fb.group({});

    this.atributos.forEach((atributo) => {
      this.form.addControl(atributo.field, this.fb.control(null, Validators.required));
    });

    if(this.idEntidade > 0){
      this.getById();
    }else{
      this.getAllEntidades();
    }

  }

  getAllEntidades(){
    switch(this.nomeEntidade){
      case  'Titulo':
        this.getAllAtores();
        this.getAllClasses();
        this.getAllDiretores();
        this.getAllItens();
        break;
      case 'Item':
        this.getAllTitulos();
        break;
      case 'Agendamentos':
        this.getAllPacientes();
        this.getAllMedicos();
        this.getAllRecepcionistas();
        break;
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

  getById(){
    this.consultasService.getById(this.idEntidade, this.rota).subscribe(resp => {
      if(resp){
        this.entidade = resp;
        if(this.nomeEntidade == 'Agendamentos'){
          this.entidade.data_hora_fim = this.extrairData(resp.data_hora_fim);
          this.entidade.data_hora_inicio = this.extrairData(resp.data_hora_inicio);
        }
        this.preencherForm(this.entidade);
        this.getAllEntidades();
      }
    });
  }

  extrairData(dataStr: any) {
    const dataObj = new Date(dataStr);
    const dataApenas = dataObj.toISOString().slice(0, 16); // Obtém a data e a hora até os minutos
    return dataApenas;
  }


  preencherForm(entity: any){

    this.atributos.forEach(atributo => {
      for (const key in entity) {
         if(key == atributo.field){
          this.form.patchValue({
            [atributo.field]: entity[key]
          });
         }
      }
    });
  }

  onSubmit() {
    if (this.form.valid) {
      this.submitted = false;
      const formData = this.form.value;

      if (!this.idEntidade || this.idEntidade <= 0) {
        this.consultasService.create(formData, this.rota + '/adicionar').subscribe((resp) => {
          this.formularioEnviado.emit(resp);
          this.form.reset();
        });
      } else {
        this.consultasService.update(formData, this.rota + '/editar', this.idEntidade).subscribe((resp) => {
          this.formularioEnviado.emit(resp);
          this.form.reset();
        });
      }
    } else {
      this.submitted = true;
    }
  }


  getAllAtores(){
    this.consultasService.getAll('/atores').subscribe(resp => {
      if(resp){
        this.atores = resp;

        if (this.idEntidade > 0) {
          const idsParaFiltrar: number[] = this.entidade.listaAtores.map((x: any) => x.id_ator); // Certifique-se de que 'id_ator' seja do tipo correto (por exemplo, number)
          this.selectedAtores = this.atores.filter(ator => idsParaFiltrar.includes(ator.id_ator));

          // this.atores.sort((d1, d2) => {
          //   if (d1.nome === this.selectedAtores.nome) {
          //       return -1; // selectedAtores vem antes
          //   } else if (d2.nome === this.selectedAtores.nome) {
          //       return 1; // selectedAtores vem depois
          //   } else {
          //       return d1.nome.localeCompare(d2.nome); // ordenar pelo nome
          //   }
          // });
        }

      }
    });
  }

  getAllDiretores(){
    this.consultasService.getAll('/diretores').subscribe(resp => {
      if(resp){
        this.diretores = resp;

        if (this.idEntidade > 0) {
          console.log('diretores', this.diretores, this.idEntidade)
          this.selectedDiretor = this.diretores.filter(x => x.id_diretor == this.entidade.diretor.id_diretor)[0];

          this.diretores.sort((d1, d2) => {
            if (d1.nome === this.selectedDiretor.nome) {
                return -1; // selectedDiretor vem antes
            } else if (d2.nome === this.selectedDiretor.nome) {
                return 1; // selectedDiretor vem depois
            } else {
                return d1.nome.localeCompare(d2.nome); // ordenar pelo nome
            }
          });

          console.log('this.diretores', this.diretores)

        }
      }
    });
  }

  getAllClasses(){
    this.consultasService.getAll('/classes').subscribe(resp => {
      if(resp){
        this.classes = resp;

        if (this.idEntidade > 0) {
          this.selectedClasse = this.classes.filter(x => x.id_classe == this.entidade.classe.id_classe)[0];

          this.classes.sort((d1, d2) => {
            if (d1.nome === this.selectedClasse.nome) {
                return -1;
            } else if (d2.nome === this.selectedClasse.nome) {
                return 1;
            } else {
                return d1.nome.localeCompare(d2.nome);
            }
          });
        }
      }
    });
  }

  getAllItens(){
    this.consultasService.getAll('/itens').subscribe(resp => {
      if(resp){
        this.itens = resp;
        console.log('this.itens', this.itens)

      }
    });
  }

  getAllTitulos(){
    this.consultasService.getAll('/titulos').subscribe(resp => {
      if(resp){
        this.titulos = resp;

        if (this.idEntidade > 0) {
          this.selectedTitulo = this.titulos.filter(x => x.id_classe == this.entidade.titulo.nome)[0];

          this.classes.sort((d1, d2) => {
            if (d1.nome === this.selectedTitulo.nome) {
                return -1;
            } else if (d2.nome === this.selectedTitulo.nome) {
                return 1;
            } else {
                return d1.nome.localeCompare(d2.nome);
            }
          });
        }
      }
    });
  }

  getAllPacientes(){
    this.consultasService.getAll('/pacientes/listar').subscribe(resp => {
      if(resp){
        this.pacientes = resp;

        if (this.idEntidade > 0) {
          this.selectedPaciente = this.pacientes.filter(x => x.id_diretor == this.entidade.diretor.id_diretor)[0];

          this.pacientes.sort((d1, d2) => {
            if (d1.nome === this.selectedPaciente.nome) {
                return -1; // selectedDiretor vem antes
            } else if (d2.nome === this.selectedPaciente.nome) {
                return 1; // selectedDiretor vem depois
            } else {
                return d1.nome.localeCompare(d2.nome); // ordenar pelo nome
            }
          });

          console.log('this.pacientes', this.pacientes)

        }
      }
    });
  }


  getAllMedicos(){
    this.consultasService.getAll('/medicos/listar').subscribe(resp => {
      if(resp){
        this.medicos = resp;

        if (this.idEntidade > 0) {
          console.log('medicos', this.medicos, this.idEntidade)
          this.selectedDiretor = this.medicos.filter(x => x.id_diretor == this.entidade.diretor.id_diretor)[0];

          this.medicos.sort((d1, d2) => {
            if (d1.nome === this.selectedMedico.nome) {
                return -1; // selectedMedico vem antes
            } else if (d2.nome === this.selectedMedico.nome) {
                return 1; // selectedDiretor vem depois
            } else {
                return d1.nome.localeCompare(d2.nome); // ordenar pelo nome
            }
          });
        }
      }
    });
  }

  getAllRecepcionistas(){
    this.consultasService.getAll('/recepcionistas/listar').subscribe(resp => {
      if(resp){
        this.recepcionistas = resp;

        if (this.idEntidade > 0) {
          console.log('recepcionistas', this.recepcionistas, this.idEntidade)
          this.selectedDiretor = this.recepcionistas.filter(x => x.id_diretor == this.entidade.diretor.id_diretor)[0];

          this.recepcionistas.sort((d1, d2) => {
            if (d1.nome === this.selectedRecepcionista.nome) {
                return -1; // selectedRecepcionista vem antes
            } else if (d2.nome === this.selectedRecepcionista.nome) {
                return 1; // selectedDiretor vem depois
            } else {
                return d1.nome.localeCompare(d2.nome); // ordenar pelo nome
            }
          });
        }
      }
    });
  }

}
