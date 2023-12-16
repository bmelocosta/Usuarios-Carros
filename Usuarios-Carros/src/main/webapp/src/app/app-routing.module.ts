import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PaginaInicialComponent } from './components/pagina-inicial/pagina-inicial.component';
import { UsuarioLogadoComponent } from './components/usuario-logado/usuario-logado.component';
import { IncluirUsuarioComponent } from './components/incluir-usuario/incluir-usuario.component';
import { CarroPopupComponent } from './components/carro-popup/carro-popup.component';
import { GerenciarUsuarioComponent } from './components/gerenciar-usuario/gerenciar-usuario.component';

const routes: Routes = [
  {path: '', component: PaginaInicialComponent},
  {path: 'users', component: IncluirUsuarioComponent},
  {path: 'me', component: UsuarioLogadoComponent},
  {path: 'users/:id', component: GerenciarUsuarioComponent},
  {path: 'incluirCarro', component: CarroPopupComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
