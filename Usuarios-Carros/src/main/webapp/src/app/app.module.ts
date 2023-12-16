import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms'
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PaginaInicialComponent } from './components/pagina-inicial/pagina-inicial.component';
import { HttpClientModule } from '@angular/common/http';
import { UsuarioLogadoComponent } from './components/usuario-logado/usuario-logado.component';
import { IncluirUsuarioComponent } from './components/incluir-usuario/incluir-usuario.component';
import { CarroPopupComponent } from './components/carro-popup/carro-popup.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatDialogModule } from '@angular/material/dialog';
import { ReactiveFormsModule } from '@angular/forms';
import { GerenciarUsuarioComponent } from './components/gerenciar-usuario/gerenciar-usuario.component';

@NgModule({
  declarations: [
    AppComponent,
    PaginaInicialComponent,
    UsuarioLogadoComponent,
    IncluirUsuarioComponent,
    CarroPopupComponent,
    GerenciarUsuarioComponent
  ],
  imports: [
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatDialogModule,
    BrowserModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
