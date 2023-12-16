import { Component, EventEmitter, Input, Output} from '@angular/core';
import { Usuario } from '../../Usuario';
import { Carro } from '../../Carro';
import { MatDialog } from '@angular/material/dialog';
import { CarroPopupComponent } from '../carro-popup/carro-popup.component';
import { UsuarioService } from '../../services/usuario.service';
import { Router } from '@angular/router';
import { DadosCarros } from '../../DadosCarros';


@Component({
  selector: 'app-incluir-usuario',
  templateUrl: './incluir-usuario.component.html',
  styleUrl: './incluir-usuario.component.css'
})
export class IncluirUsuarioComponent {

  constructor(private dialog: MatDialog, private usuarioService: UsuarioService, private router: Router){}
  usuario: Usuario = new Usuario();

  carros: Carro[] = [];

  popupCarros(){
    const dadosPopup: DadosCarros = {};
    dadosPopup.carro = new Carro();
    dadosPopup.carros = this.carros;
    console.log("Abrindo Popup Carros.")
  this.dialog.open(CarroPopupComponent,{
    data : dadosPopup
  });

  }

  onEnvioFormulario(){
    console.log("Enviando Dados.");
    this.usuario.cars = this.carros;
    this.usuarioService.salvar(this.usuario).subscribe((resposta=>{
      this.usuario = resposta
      console.log(this.usuario);
    }))
    alert("Incluido com sucesso!")
    this.router.navigate([''])

  }



}
