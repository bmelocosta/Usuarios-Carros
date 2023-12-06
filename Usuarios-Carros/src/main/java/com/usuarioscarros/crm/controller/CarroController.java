package com.usuarioscarros.crm.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usuarioscarros.crm.dto.ErroDTO;
import com.usuarioscarros.crm.model.Carro;
import com.usuarioscarros.crm.model.Usuario;
import com.usuarioscarros.crm.repository.CarroRepository;
import com.usuarioscarros.crm.services.CarroService;
import com.usuarioscarros.crm.services.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cars")
public class CarroController {

	@Autowired
	private CarroService carroService;
	
	@Autowired	
	CarroRepository carroRepository;
	
	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping
	public ResponseEntity<List<Carro>> listarCarrosDoUsuario(@AuthenticationPrincipal Usuario usuarioLogado) {		
		return ResponseEntity.ok().body(carroService.listarCarrosDoUsuario(usuarioLogado));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getCarroPorId(@PathVariable("id") Long id , @AuthenticationPrincipal Usuario usuarioLogado){
	
		try {				
			usuarioLogado = (Usuario) usuarioService.getUsuario(usuarioLogado.getLogin());
			Optional<Carro> carro = carroService.getCarro(usuarioLogado , id);
			if (carro!=null) {
				return ResponseEntity.status(HttpStatus.CREATED).body(carro.get());				
			}else {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);	
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> adicionarCarro(@RequestBody @Valid Carro carro, @AuthenticationPrincipal Usuario usuarioLogado) {	
		
		try {		
			usuarioLogado = (Usuario) usuarioService.getUsuario(usuarioLogado.getLogin());
			usuarioService.updateCarros(usuarioLogado, carro);
			return ResponseEntity.status(HttpStatus.CREATED).body(carro);			
		} catch (Exception e) {
			ErroDTO erro = new ErroDTO(e.getMessage(), HttpStatus.BAD_REQUEST.value());
			return ResponseEntity.ok().body(erro);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizarCarro(@PathVariable("id")Long id, @RequestBody @Valid Carro carro , @AuthenticationPrincipal Usuario usuarioLogado) {	
		
		try {					
			usuarioLogado = (Usuario) usuarioService.getUsuario(usuarioLogado.getLogin());		
			Carro carroAtualizado = carroService.update(usuarioLogado, id, carro);
			if (carroAtualizado!=null) {
				return ResponseEntity.status(HttpStatus.CREATED).body(carro);				
			}else {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
			}
		} catch (Exception e) {
			ErroDTO erro = new ErroDTO(e.getMessage(), HttpStatus.BAD_REQUEST.value());
			return ResponseEntity.ok().body(erro);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarCarroPorId(@PathVariable("id")Long id , @AuthenticationPrincipal Usuario usuarioLogado) {	
		
		try {					
			usuarioLogado = (Usuario) usuarioService.getUsuario(usuarioLogado.getLogin());
			Optional<Carro> carroADeletar = carroService.getCarro(usuarioLogado,id);
			usuarioService.deleteCarro(usuarioLogado, carroADeletar.get());
			carroRepository.deleteById(id);
			return ResponseEntity.ok().body("Usuario excluido com sucesso!");			
		} catch (Exception e) {
			ErroDTO erro = new ErroDTO(e.getMessage(), HttpStatus.BAD_REQUEST.value());
			return ResponseEntity.ok().body(erro);
		}
	}
	
}
