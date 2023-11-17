import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CadastrosComponent } from './components/cadastros/cadastros.component';
import { GerenciarConsultasComponent } from './components/gerenciar-consultas/gerenciar-consultas.component';

const routes: Routes = [
  { path: 'cadastros/:tipo', component: CadastrosComponent },
  { path: 'gerenciarConsultas', component: GerenciarConsultasComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {


}
