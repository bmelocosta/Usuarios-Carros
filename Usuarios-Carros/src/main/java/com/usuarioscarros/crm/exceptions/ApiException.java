package com.usuarioscarros.crm.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiException {

	private String message;
	private int errorCode;
}
