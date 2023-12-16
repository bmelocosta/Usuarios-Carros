import { Component } from '@angular/core';
import { Credenciais } from '../../Credenciais';
import { LoginService } from '../../services/login.service';
import { Router } from '@angular/router';
import { RespostaLoginUsuario } from '../../RespostaLoginUsuario';
import { delay } from 'rxjs';

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
sessionStorage.removeItem('token');
localStorage.removeItem('usuario');
this.loginService.logar(this.user).subscribe((resposta=>{
  this.respostaLogin = resposta;
  localStorage.setItem('usuario', JSON.stringify(this.respostaLogin.usuario));
  if(this.respostaLogin.token!==null){
    sessionStorage.setItem('token', resposta.token||"");
  }else{
    sessionStorage.setItem('token', "");
    if(!this.respostaLogin.token){
      alert("Usuario Invalido, tente novamente!");
      return
    }
  }
  console.log("Token: "+sessionStorage.getItem('token'));
  console.log(this.respostaLogin)
  this.loginService.sleep(5000);
  this.router.navigate(['/me'])
  }));

}





}
