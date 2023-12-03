package com.usuarioscarros.crm.security;

import java.security.Signature;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.usuarioscarros.crm.model.Usuario;

@Service
public class TokenService {

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
	
	private Date genExpirationDate() {
		Date expiracao = new Date();
		expiracao = new Date(expiracao.getTime()+7200000);
		return expiracao;
	}
}
