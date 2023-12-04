package com.usuarioscarros.crm.exceptions;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.usuarioscarros.crm.dto.ErroDTO;


@RestControllerAdvice
public class AppExceptionHandler {
		
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ArrayList<ErroDTO> tratarArgumentoInvalido (MethodArgumentNotValidException e){		
		ArrayList<ErroDTO> listaErros =  new ArrayList<ErroDTO>();
		e.getBindingResult().getFieldErrors().forEach(error->{
			ErroDTO erro = new ErroDTO(error.getDefaultMessage(),HttpStatus.BAD_REQUEST.value());
			listaErros.add(erro);
		});
		return listaErros;
	}	
}
