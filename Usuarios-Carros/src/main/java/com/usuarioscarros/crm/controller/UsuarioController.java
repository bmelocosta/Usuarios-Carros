package com.usuarioscarros.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usuarioscarros.crm.dto.ErroDTO;
import com.usuarioscarros.crm.model.Usuario;
import com.usuarioscarros.crm.services.CarroService;
import com.usuarioscarros.crm.services.UsuarioService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/users")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	CarroService carroService;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> listar() {
		return ResponseEntity.ok().body(usuarioService.listar());
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> getUsuarioPorId(@PathVariable ("id") Long id) {
		return ResponseEntity.ok().body(usuarioService.getUsuario(id));
	}
	
	@PostMapping	
	public ResponseEntity<?> adicionarUsuario(@RequestBody @Valid Usuario user) {	
		try {
			usuarioService.save(user);
			return ResponseEntity.status(HttpStatus.CREATED).body(user);				
		} catch (Exception e) {
			ErroDTO erro = new ErroDTO(e.getMessage(), HttpStatus.BAD_REQUEST.value());
			return ResponseEntity.ok().body(erro);
		}
	}
	@PutMapping("/{id}")	
	public ResponseEntity<?> atualizarUsuario(@PathVariable("id") Long id, @RequestBody @Valid Usuario user) {	
		try {
			usuarioService.update(id , user);
			return ResponseEntity.status(HttpStatus.CREATED).body(user);				
		} catch (Exception e) {
			ErroDTO erro = new ErroDTO(e.getMessage(), HttpStatus.BAD_REQUEST.value());
			return ResponseEntity.ok().body(erro);
		}
	}
	@DeleteMapping("/{id}")	
	public ResponseEntity<?> deletarUsuario(@PathVariable("id") Long id) {	
		try {
			usuarioService.delete(id);
			return ResponseEntity.ok().body("Usuario excluido com sucesso!");				
		} catch (Exception e) {
			ErroDTO erro = new ErroDTO(e.getMessage(), HttpStatus.BAD_REQUEST.value());
			return ResponseEntity.ok().body(erro);
		}
	}
	
}
