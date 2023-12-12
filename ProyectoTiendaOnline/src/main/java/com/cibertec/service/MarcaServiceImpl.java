package com.cibertec.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.entity.Marca;
import com.cibertec.repository.MarcaRepository;

@Service
public class MarcaServiceImpl implements MarcaService {

	
	@Autowired
	private MarcaRepository marcaRepository; 
	@Override
	public Marca insertar(Marca ca) {
		return  marcaRepository.save(ca);
	}

	@Override
	public List<Marca> listar() {
		return  marcaRepository.findAll();
	}

	@Override
	public Optional<Marca> buscarId(int id) {
		
		return marcaRepository.findById(id);
	}

	@Override
	public Marca actualizar(Marca marca) {
		return marcaRepository.save(marca);
	}

	@Override
	public List<Marca> listaMarcasActivo() {
		return marcaRepository.listaMarcasActivo();
	}

}
