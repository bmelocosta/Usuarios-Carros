import { Usuario } from "./Usuario";

export interface RespostaLoginUsuario {
  token?: string;
  usuario?: Usuario;
}
