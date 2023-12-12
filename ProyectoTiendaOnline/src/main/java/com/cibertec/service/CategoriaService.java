package com.cibertec.service;

import java.util.List;
import java.util.Optional;

import com.cibertec.entity.Categoria;

public interface CategoriaService {
// crud
	public abstract Categoria insertar(Categoria ca);
	public abstract List<Categoria> listar();
	public abstract Optional<Categoria> buscarId(int id);
	public abstract Categoria actualizar(Categoria categoria);
	public abstract  List<Categoria> listaCategoriasActivo();
	
}
