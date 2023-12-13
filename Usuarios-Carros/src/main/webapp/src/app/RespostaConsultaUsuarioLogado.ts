import { Usuario } from "./Usuario";

export class RespostaConsultaUsuarioLogado {
  createdAt?: Date;
  lastLogin?: Date;
  usuario?: Usuario;

  constructor(createdAt?: Date, lastLogin?: Date, usuario?: Usuario){
  this.createdAt = createdAt;
  this.lastLogin = lastLogin;
  this.usuario = usuario;
  }

}
