package com.cibertec.service;

import java.util.List;
import java.util.Optional;

import com.cibertec.entity.Marca;

public interface MarcaService {

	// crud
		public abstract Marca insertar(Marca ca);
		public abstract List<Marca> listar();
		public abstract Optional<Marca> buscarId(int id);
		public abstract Marca actualizar(Marca Marca);
		public abstract  List<Marca> listaMarcasActivo();
}
