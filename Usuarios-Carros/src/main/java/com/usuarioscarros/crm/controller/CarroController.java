package com.usuarioscarros.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.usuarioscarros.crm.dto.ErroDTO;
import com.usuarioscarros.crm.model.Carro;
import com.usuarioscarros.crm.services.CarroService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cars")
public class CarroController {

	@Autowired
	private CarroService carroService;
	
	@GetMapping
	public ResponseEntity<List<Carro>> listar() {
		return ResponseEntity.ok().body(carroService.listar());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getCarroPorId(@PathVariable("id") Long id) {
		return ResponseEntity.ok().body(carroService.getCarro(id));
	}
	
	@PostMapping
	public ResponseEntity<?> adicionarCarro(@RequestBody @Valid Carro carro) {	
		
		try {
			carroService.save(carro);
			return ResponseEntity.status(HttpStatus.CREATED).body(carro);			
		} catch (Exception e) {
			ErroDTO erro = new ErroDTO(e.getMessage(), HttpStatus.BAD_REQUEST.value());
			return ResponseEntity.ok().body(erro);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizarCarro(@PathVariable("id")Long id, @RequestBody @Valid Carro carro) {	
		
		try {
			carroService.update(id, carro);
			return ResponseEntity.status(HttpStatus.CREATED).body(carro);			
		} catch (Exception e) {
			ErroDTO erro = new ErroDTO(e.getMessage(), HttpStatus.BAD_REQUEST.value());
			return ResponseEntity.ok().body(erro);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarCarroPorId(@PathVariable("id")Long id) {	
		
		try {
			carroService.delete(id);
			return ResponseEntity.ok().body("Usuario excluido com sucesso!");			
		} catch (Exception e) {
			ErroDTO erro = new ErroDTO(e.getMessage(), HttpStatus.BAD_REQUEST.value());
			return ResponseEntity.ok().body(erro);
		}
	}
	
}
