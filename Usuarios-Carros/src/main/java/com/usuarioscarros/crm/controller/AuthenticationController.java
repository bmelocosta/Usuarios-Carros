package com.usuarioscarros.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usuarioscarros.crm.dto.AuthenticationDTO;
import com.usuarioscarros.crm.dto.ErroDTO;
import com.usuarioscarros.crm.dto.LoginResponseDTO;
import com.usuarioscarros.crm.dto.RetornoConsultaUsuarioDTO;
import com.usuarioscarros.crm.dto.UsuarioLogadoDTO;
import com.usuarioscarros.crm.model.Usuario;
import com.usuarioscarros.crm.security.TokenService;
import com.usuarioscarros.crm.services.UsuarioService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://servico-front-pitang.s3-website-us-west-2.amazonaws.com")
@RestController
@RequestMapping
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping("/api/signin")
	@CrossOrigin(origins = "http://servico-front-pitang.s3-website-us-west-2.amazonaws.com")
	public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
		try {
			SecurityContextHolder.clearContext();
			var loginPassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
			var auth = this.authenticationManager.authenticate(loginPassword); 		
			var token = tokenService.generateToken((Usuario)auth.getPrincipal());		
			return ResponseEntity.ok().body(new LoginResponseDTO(token.toString(), (Usuario)auth.getPrincipal()));			
		} catch (Exception e) {
			ErroDTO erro = new ErroDTO("invalid fields", HttpStatus.BAD_REQUEST.value());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
		}
	}
	
	@GetMapping("/api/me")
	@CrossOrigin(origins = "http://servico-front-pitang.s3-website-us-west-2.amazonaws.com")
	public ResponseEntity getUsuarioLogado(@AuthenticationPrincipal Usuario usuarioLogado) {	
		try {
			
			usuarioLogado = (Usuario) usuarioService.getUsuario(usuarioLogado.getLogin());						
			UsuarioLogadoDTO usuarioLogadoDTO = new UsuarioLogadoDTO();
			usuarioLogadoDTO.setBirthday(usuarioLogado.getBirthday());
			usuarioLogadoDTO.setCars(usuarioLogado.getCars());
			usuarioLogadoDTO.setEmail(usuarioLogado.getEmail());
			usuarioLogadoDTO.setFirstName(usuarioLogado.getFirstName());
			usuarioLogadoDTO.setLastName(usuarioLogado.getLastName());
			usuarioLogadoDTO.setLogin(usuarioLogado.getLogin());
			usuarioLogadoDTO.setPhone(usuarioLogado.getPhone());			
			usuarioLogadoDTO.setId(usuarioLogado.getId());			
			RetornoConsultaUsuarioDTO retorno = new RetornoConsultaUsuarioDTO(usuarioLogadoDTO, usuarioLogado.getCreatedAt(), usuarioLogado.getLastLogin());			
			return ResponseEntity.ok().body(retorno);				
		} catch (Exception e) {
			ErroDTO erro = new ErroDTO("nvalid fields", HttpStatus.BAD_REQUEST.value());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
		}			
	}
	
}
