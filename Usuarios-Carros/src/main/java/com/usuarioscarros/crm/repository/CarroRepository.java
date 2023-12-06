package com.usuarioscarros.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usuarioscarros.crm.model.Carro;

import jakarta.servlet.Filter;

public interface CarroRepository extends JpaRepository<Carro, Long>{
	
	boolean existsByLicensePlate(String licensePlate);	
}
