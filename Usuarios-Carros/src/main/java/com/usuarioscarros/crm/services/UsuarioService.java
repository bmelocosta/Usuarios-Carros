package com.usuarioscarros.crm.services;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.usuarioscarros.crm.model.Carro;
import com.usuarioscarros.crm.model.Usuario;
import com.usuarioscarros.crm.repository.UsuarioRepository;

import jakarta.validation.Valid;


@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	CarroService carroService;

	public List<Usuario> listar() {		
		return usuarioRepository.findAll();
	}
	
	public Usuario save(@Valid Usuario user) throws Exception {
		if (user.getCars()!=null && user.getCars().size()>0) {			
			for (Iterator<Carro> iterator = user.getCars().iterator(); iterator.hasNext();) {
				Carro carro = (Carro) iterator.next();
				if (carro.getId()==null) {
					carroService.save(carro);
				}else {
					carroService.update(carro.getId(), carro);
				}				
			}			
		}

		String encryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
		user.setPassword(encryptedPassword);
		user.setId(null);
		if (usuarioRepository.existsByEmail(user.getEmail())){
			throw new Exception("Email already exists");
		}
		if (usuarioRepository.existsByLogin(user.getLogin())){
			throw new Exception("Login already exists");
		}
		return usuarioRepository.save(user);
		
	}
	public Usuario update(Long id , @Valid Usuario user) throws Exception {
		if (usuarioRepository.findById(id).isEmpty()) {
			throw new RuntimeException("Usuario com id: "+id+" não foi encontrado.");
		}
		if (user.getCars()!=null && user.getCars().size()>0) {			
			for (Iterator<Carro> iterator = user.getCars().iterator(); iterator.hasNext();) {
				Carro carro = (Carro) iterator.next();
				if (carro.getId()==null) {
					carroService.save(carro);
				}else {
					carroService.update(carro.getId(), carro);
				}
			}			
		}
		
		String encryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
		user.setPassword(encryptedPassword);
		user.setId(id);
		if (usuarioRepository.existsByEmail(user.getEmail())){
			throw new RuntimeException("Email already exists");
		}
		if (usuarioRepository.existsByLogin(user.getLogin())){
			throw new RuntimeException("Login already exists");
		}
		return usuarioRepository.save(user);
		
	}
	public void delete(Long id) throws Exception {	
		if (usuarioRepository.findById(id).isEmpty()) {
			throw new RuntimeException("Usuario com id: "+id+" não foi encontrado.");
		}
		usuarioRepository.deleteById(id);
		
	}
	
	public Optional<Usuario> getUsuario(Long id) {
		return usuarioRepository.findById(id);
	}
	
}
