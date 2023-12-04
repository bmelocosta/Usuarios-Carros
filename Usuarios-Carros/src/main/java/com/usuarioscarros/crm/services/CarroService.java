package com.usuarioscarros.crm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usuarioscarros.crm.model.Carro;
import com.usuarioscarros.crm.repository.CarroRepository;

import jakarta.validation.Valid;

@Service
public class CarroService {

	@Autowired	
	private CarroRepository carroRepository;
	
	public Carro save(@Valid Carro carro) throws Exception {
		carro.setId(null);
		if (carroRepository.existsByLicensePlate(carro.getLicensePlate())){
			throw new Exception("License plate already exists");
		}
		return carroRepository.save(carro);
		
	}
	public Carro update(Long id ,  @Valid Carro carro) throws Exception {
		if (carroRepository.findById(id).isEmpty()) {
			throw new RuntimeException("Carro com id: "+id+" não foi encontrado.");
		}
		carro.setId(id);
		if (carroRepository.existsByLicensePlate(carro.getLicensePlate())){
			throw new Exception("License plate already exists");
		}
		return carroRepository.save(carro);
		
	}
	public void delete(Long id) throws Exception {
		if (carroRepository.findById(id).isEmpty()) {
			throw new RuntimeException("Carro com id: "+id+" não foi encontrado.");
		}		
		carroRepository.deleteById(id);
		
	}

	public List<Carro> listar() {		
		return carroRepository.findAll();
	}
	
	public Optional<Carro> getCarro(Long id) {
		return carroRepository.findById(id);
	}

}
