import { Injectable } from '@angular/core';
import { Credenciais } from '../Credenciais';
import { Observable } from 'rxjs';
import { HttpClient , HttpHeaders} from '@angular/common/http';
import { RespostaLoginUsuario } from '../RespostaLoginUsuario';
import { RespostaConsultaUsuarioLogado } from '../RespostaConsultaUsuarioLogado';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  signin:string = 'http://localhost:8080/api/signin'
  me:string = 'http://localhost:8080/api/me'

  constructor(private http:HttpClient) { }


  headers: HttpHeaders = new HttpHeaders().set("Authorization", 'Bearer ' + sessionStorage.getItem('token')).set('Content-Type', 'application/json');


  logar(usuario: Credenciais): Observable<RespostaLoginUsuario> {
    console.log('chegou no service')
    return this.http.post<RespostaLoginUsuario>(this.signin, usuario);
  }


  buscarme(): Observable<RespostaConsultaUsuarioLogado> {
  return this.http.get<RespostaConsultaUsuarioLogado>(this.me ,  {headers: this.headers})
  }
}
