package com.usuarioscarros.crm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class ErroDTO {
	
	private String message;
	private int errorCode;

}
