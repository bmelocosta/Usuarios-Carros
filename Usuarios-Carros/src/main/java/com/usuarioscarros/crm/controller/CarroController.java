package com.usuarioscarros.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.usuarioscarros.crm.model.Carro;
import com.usuarioscarros.crm.repository.CarroRepository;

@RestController
@RequestMapping("/cars")
public class CarroController {

	@Autowired
	private CarroRepository carroRepository;
	
	@GetMapping
	public List<Carro> listar() {
		return carroRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity adicionarCarro(@RequestBody Carro carro) {
		carroRepository.save(carro);
		return ResponseEntity.ok().build();
	}
	
}
