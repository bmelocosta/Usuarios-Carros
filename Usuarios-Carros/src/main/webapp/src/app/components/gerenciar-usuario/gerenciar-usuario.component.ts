import { Component, OnInit } from '@angular/core';
import { Usuario } from '../../Usuario';
import { UsuarioService } from '../../services/usuario.service';
import { Carro } from '../../Carro';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { CarroPopupComponent } from '../carro-popup/carro-popup.component';
import { DadosCarros } from '../../DadosCarros';

@Component({
  selector: 'app-gerenciar-usuario',
  templateUrl: './gerenciar-usuario.component.html',
  styleUrls: ['./gerenciar-usuario.component.css']
})

export class GerenciarUsuarioComponent implements OnInit {

usuario: Usuario = new Usuario;
carros: Carro[] = [];
firstName: String = "teste";

  constructor(private usuarioService: UsuarioService,private router: Router, private dialog: MatDialog) { }

  ngOnInit() {
    this.usuario = JSON.parse(localStorage.getItem('usuario')||"");
    console.log(this.usuario)
    this.carros = this.usuario.cars!
  }

  onEnvioFormulario(){
    console.log("Enviando Dados.");
    this.usuario.cars = this.carros;
    this.usuarioService.atualizar(this.usuario).subscribe((resposta=>{
      this.usuario = resposta
      console.log(this.usuario);
      alert("Incluido com sucesso!")
      this.usuarioService.sleep(2000);
      this.usuarioService.buscar(this.usuario.id!).subscribe((res)=>{
        this.usuario = res
        this.carros = this.usuario.cars!
        localStorage.setItem('usuario', JSON.stringify( this.usuario));
        this.usuarioService.sleep(2000);
      this.router.navigate(['/me'])
      })
    }))

  }

  excluirCarro(carro: Carro){
    this.carros = this.carros.filter(res=> carro.id !== res.id);
    this.usuario.cars = this.carros;
    if(carro.id){
      this.usuarioService.excluirCarro(carro).subscribe()
    }
  }
  editarCarro(carro: Carro){
    const dadosPopup: DadosCarros = {};
    dadosPopup.carro = carro;
    dadosPopup.carros = this.carros;
    console.log("Abrindo Popup Carros.")
    this.dialog.open(CarroPopupComponent,{
      data : dadosPopup
    });
  }

  popupCarros(){
    const dadosPopup: DadosCarros = {};
    dadosPopup.carro = new Carro();
    dadosPopup.carros = this.carros;
    console.log("Abrindo Popup Carros.")
  this.dialog.open(CarroPopupComponent,{
    data : dadosPopup
  });
}

}
