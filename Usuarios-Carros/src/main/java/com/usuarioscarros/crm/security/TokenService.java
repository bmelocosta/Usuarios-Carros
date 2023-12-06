package com.usuarioscarros.crm.security;

import java.security.Signature;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.usuarioscarros.crm.model.Usuario;
import com.usuarioscarros.crm.services.UsuarioService;

@Service
public class TokenService {
	
	@Autowired
	UsuarioService usuarioService;

	@Value("${api.security.token.secret}")
	private String secret;
	
	public String generateToken(Usuario usuario) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			String token = JWT.create().
					withIssuer("Usuarios-Carros").
					withSubject(usuario.getLogin()).
					withExpiresAt(genExpirationDate()).
					sign(algorithm);
			try {
				SimpleDateFormat dataFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSZ");
				Date dataLogin = new Date();
				String dataFormatada = dataFormater.format(dataLogin);
				dataLogin = dataFormater.parse(dataFormatada);				
				usuario.setLastLogin(dataLogin);
				usuarioService.update(usuario.getId(), usuario);
				
			} catch (Exception e) {
				return token; 
			}
			return token;	
			
		} catch (JWTCreationException e) {
			throw new RuntimeException("Erro na geração do token", e);
		}			
	}
	
	public String validateToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			return JWT.require(algorithm)
					.withIssuer("Usuarios-Carros")
					.build()
					.verify(token)
					.getSubject();
		} catch (JWTCreationException e) {
			return "";
		}
	}
	
	public UserDetails getUsuarioAutenticado(String token){
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			var loginUsuario = JWT.require(algorithm)
					.withIssuer("Usuarios-Carros")
					.build()
					.verify(token)
					.getSubject();			
			return usuarioService.getUsuario(loginUsuario);
		} catch (JWTCreationException e) {
			return null;
		}
	}
	private Date genExpirationDate() {
		Date expiracao = new Date();
		expiracao = new Date(expiracao.getTime()+3600000);
		return expiracao;
	}
}
