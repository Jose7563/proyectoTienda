package com.cibertec.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.entity.Categoria;
import com.cibertec.repository.CategoriaRepository;

@Service
public class CategoriaServiceImpl implements CategoriaService  {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Override
	public Categoria insertar(Categoria ca) {
		
		return  categoriaRepository.save(ca);
	}

	@Override
	public List<Categoria> listar() {
		return  categoriaRepository.findAll();
	}

	@Override
	public Optional<Categoria> buscarId(int id) {
		
		return categoriaRepository.findById(id);
	}

	@Override
	public Categoria actualizar(Categoria ca) {
		return categoriaRepository.save(ca);
	}

	@Override
	public List<Categoria> listaCategoriasActivo() {
		
		return categoriaRepository.listaCategoriasActivo();
	}

	
}
