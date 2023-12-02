package com.usuarioscarros.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usuarioscarros.crm.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
