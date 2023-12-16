import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginService } from '../../services/login.service';
import { RespostaConsultaUsuarioLogado } from '../../RespostaConsultaUsuarioLogado';
import { Usuario } from '../../Usuario';
import { Carro } from '../../Carro';


@Component({
  selector: 'app-usuario-logado',
  templateUrl: './usuario-logado.component.html',
  styleUrl: './usuario-logado.component.css'
})
export class UsuarioLogadoComponent {
constructor(private router: ActivatedRoute , private loginService: LoginService) {
}
resposta: RespostaConsultaUsuarioLogado = {};
usuario: Usuario = {};
carros: Carro[]=[];

buscarme(){
  this.loginService.buscarme().subscribe((resultado)=>{
  const resp: RespostaConsultaUsuarioLogado = resultado;
    this.resposta = resp;
    this.usuario = this.resposta.usuario||{};
    this.carros = this.usuario.cars||[];
    localStorage.setItem('usuario', JSON.stringify( this.usuario));
  console.log(this.resposta.usuario);
})
}

ngOnInit(): void {
this.buscarme();
}


}
