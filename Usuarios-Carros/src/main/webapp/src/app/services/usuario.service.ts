import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Usuario } from '../Usuario';
import { Carro } from '../Carro';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  sleep = (ms: number) => new Promise(r => setTimeout(r, ms));
  url:string = '//34.212.244.100:8080/api/users'
  urlCarros:string = '//34.212.244.100:8080/api/cars'

  headers: HttpHeaders = new HttpHeaders().set("Authorization", 'Bearer ' + sessionStorage.getItem('token')).set('Content-Type', 'application/json');

  constructor(private http:HttpClient) { }

  buscar(id: number): Observable<Usuario> {
    console.log('chegou no service')
    return this.http.get<Usuario>(`${this.url}/${id}`);
  }

  salvar(usuario: Usuario): Observable<Usuario>{
    console.log('chegou no service')
    return this.http.post<Usuario>(this.url , usuario);
  }
  atualizar(usuario: Usuario): Observable<Usuario>{
    console.log('chegou no service')
    return this.http.put<Usuario>(`${this.url}/${usuario.id}` , usuario);
  }
  excluirCarro(carro: Carro) {
    console.log('chegou no service')
    return this.http.delete(`${this.urlCarros}/${carro.id}` ,  {headers: this.headers})
  }

}
