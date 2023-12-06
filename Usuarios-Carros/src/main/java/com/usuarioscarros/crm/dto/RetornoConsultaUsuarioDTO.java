package com.usuarioscarros.crm.dto;

import java.util.Date;

public record RetornoConsultaUsuarioDTO(UsuarioLogadoDTO usuario , Date createdAt , Date lastLogin) {

}
