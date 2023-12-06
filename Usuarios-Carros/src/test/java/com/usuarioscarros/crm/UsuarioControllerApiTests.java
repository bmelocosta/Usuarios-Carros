package com.usuarioscarros.crm;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.usuarioscarros.crm.controller.UsuarioController;
import com.usuarioscarros.crm.model.Usuario;
import com.usuarioscarros.crm.services.UsuarioService;

@WebMvcTest(UsuarioController.class)
public class UsuarioControllerApiTests {

	private static final String END_POINT_PATH = "/api/users";	;
	
	@Autowired
	private MockMvc mockmvc;	
	@MockBean
	private UsuarioService usuarioService;
	
	@Test
	public ResponseEntity<List<Usuario>> testShuldReturnEmptyList() throws Exception {
		mockmvc.perform(get(END_POINT_PATH).contentType("application/json")).andReturn();
		return ResponseEntity.ok().body(usuarioService.listar());
	}
	
  	
}
