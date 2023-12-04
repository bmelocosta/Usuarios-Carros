package com.usuarioscarros.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usuarioscarros.crm.dto.AuthenticationDTO;
import com.usuarioscarros.crm.dto.LoginResponseDTO;
import com.usuarioscarros.crm.model.Usuario;
import com.usuarioscarros.crm.security.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/signin")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
		var loginPassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
		var auth = this.authenticationManager.authenticate(loginPassword); 		
		var token = tokenService.generateToken((Usuario)auth.getPrincipal());
		
		return ResponseEntity.ok(new LoginResponseDTO(token));
	}
	
}
