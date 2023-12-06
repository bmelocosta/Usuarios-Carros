package com.usuarioscarros.crm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usuarioscarros.crm.model.Carro;
import com.usuarioscarros.crm.model.Usuario;
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
	public Carro update(Usuario usuarioLogado, Long id ,  @Valid Carro carro) throws Exception {
		
		Optional<Carro> carroConsultado = carroRepository.findById(id);
		if (!usuarioLogado.getCars().contains(carroConsultado.get())) {
			return null;
		}
		if (carroConsultado.isEmpty()) {
			throw new RuntimeException("Carro com id: "+id+" não foi encontrado.");
		}
		carro.setId(id);
		if (carroRepository.existsByLicensePlate(carro.getLicensePlate()) && !carroConsultado.get().getLicensePlate().equals(carro.getLicensePlate())){
			throw new Exception("License plate already exists");
		}
		return carroRepository.save(carro);					
	}
	
public Carro update(Long id ,  @Valid Carro carro) throws Exception {
		
		Optional<Carro> carroConsultado = carroRepository.findById(id);		
		if (carroConsultado.isEmpty()) {
			throw new RuntimeException("Carro com id: "+id+" não foi encontrado.");
		}
		carro.setId(id);
		if (carroRepository.existsByLicensePlate(carro.getLicensePlate()) && !carroConsultado.get().getLicensePlate().equals(carro.getLicensePlate())){
			throw new Exception("License plate already exists");
		}
		return carroRepository.save(carro);					
	}
	
	public void delete(Usuario usuarioLogado, Long id) throws Exception {
		Optional<Carro> carroADeletar = carroRepository.findById(id);
		if (carroADeletar.isEmpty()) {
			throw new RuntimeException("Carro com id: "+id+" não foi encontrado.");
		}
		if (usuarioLogado.getCars().contains(carroADeletar)) {
			throw new RuntimeException("Carro com id: "+id+" eprtence a outro usuario.");
		}		
		carroRepository.deleteById(id);
		
	}

	public List<Carro> listarCarrosDoUsuario() {		
		
		return carroRepository.findAll();
	}
	
	public Optional<Carro> getCarro(Usuario usuarioLogado , Long id) {
		Optional<Carro> carro = carroRepository.findById(id);
		if (usuarioLogado.getCars().contains(carro.get())) {
			return carro;		
		}else {
			return null;
		}
	}
	
	public List<Carro> listarCarrosDoUsuario(Usuario usuarioLogado){
		List<Carro> lista = usuarioLogado.getCars();
		return lista; 
	}

}
