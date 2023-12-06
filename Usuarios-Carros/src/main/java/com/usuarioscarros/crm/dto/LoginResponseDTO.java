package com.usuarioscarros.crm.dto;

import com.usuarioscarros.crm.model.Usuario;

public record LoginResponseDTO(String token , Usuario usuario) {

}
