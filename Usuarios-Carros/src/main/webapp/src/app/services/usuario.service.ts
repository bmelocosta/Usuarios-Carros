import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Usuario } from '../Usuario';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  url:string = 'http://localhost:8080/api/users'
  constructor(private http:HttpClient) { }

  buscar(id: number): Observable<Usuario> {
    console.log('chegou no service')
    return this.http.get<Usuario>(`${this.url}/${id}`);
  }

  salvar(usuario: Usuario): Observable<Usuario>{
    console.log('chegou no service')
    return this.http.post<Usuario>(this.url , usuario);
  }

}
