package com.cibertec.service;

import java.util.List;
import java.util.Optional;

import com.cibertec.entity.Producto;

public interface ProductoService {
	public abstract Producto insertar(Producto ca);
	public abstract List<Producto> listar();
	public abstract Optional<Producto> buscarId(int id);
	public abstract Producto actualizar(Producto categoria);
	
	public abstract List<Producto> listaPorNombreProductoCategoria(String nombre);
	public abstract  List<Producto> listaPorNombreProductoMarca(String nombre);
}
