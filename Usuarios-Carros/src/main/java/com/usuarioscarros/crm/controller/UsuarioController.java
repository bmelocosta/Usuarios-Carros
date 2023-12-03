package com.usuarioscarros.crm.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.usuarioscarros.crm.model.Carro;
import com.usuarioscarros.crm.model.Usuario;
import com.usuarioscarros.crm.repository.CarroRepository;
import com.usuarioscarros.crm.repository.UsuarioRepository;

@RestController
@RequestMapping("/users")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	CarroRepository carroRepository;
	
	@GetMapping
	public List<Usuario> listar() {
		return usuarioRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity adicionarUsuario(@RequestBody Usuario user) {
		if (user.getCars()!=null && user.getCars().size()>0) {			
			for (Iterator<Carro> iterator = user.getCars().iterator(); iterator.hasNext();) {
				Carro carro = (Carro) iterator.next();
				carroRepository.save(carro);				
			}			
		}

		String encryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
		user.setPassword(encryptedPassword);
		usuarioRepository.save(user);
		return ResponseEntity.ok().build(); 
	}
	
}
