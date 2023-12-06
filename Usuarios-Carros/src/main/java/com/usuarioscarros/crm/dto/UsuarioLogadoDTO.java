package com.usuarioscarros.crm.dto;

import java.util.Date;
import java.util.List;

import com.usuarioscarros.crm.model.Carro;

import lombok.Data;

@Data
public class UsuarioLogadoDTO {
	
	private String firstName;
	private String lastName;
	private String email;
	private Date birthday;
	private String login;
	private String phone;
	private List<Carro> Cars;

}
