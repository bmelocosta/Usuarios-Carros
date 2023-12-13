import { Component } from '@angular/core';
import { Credenciais } from '../../Credenciais';
import { LoginService } from '../../services/login.service';
import { Router } from '@angular/router';
import { RespostaLoginUsuario } from '../../RespostaLoginUsuario';

@Component({
  selector: 'app-pagina-inicial',
  templateUrl: './pagina-inicial.component.html',
  styleUrl: './pagina-inicial.component.css'
})
export class PaginaInicialComponent {

respostaLogin: RespostaLoginUsuario = {};

user:Credenciais ={
login: "",
password: ""
}

constructor(private loginService: LoginService , private router: Router){

}

logar() {
this.loginService.logar(this.user).subscribe((resposta=>{
  this.respostaLogin = resposta;
  localStorage.setItem('usuario', JSON.stringify(this.respostaLogin));
  if(this.respostaLogin.token!==null){
    sessionStorage.setItem('token', resposta.token||"");
    this.router.navigate(['/me'])
  }else sessionStorage.setItem('token', "");
  console.log("Token: "+sessionStorage.getItem('token'));
  console.log(this.respostaLogin)}));
  if(this.respostaLogin===undefined){
  alert("Usuario Invalido, tente novamente!");
  }
}





}
