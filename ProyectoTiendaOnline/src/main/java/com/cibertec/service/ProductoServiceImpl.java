package com.cibertec.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.entity.Producto;
import com.cibertec.repository.ProductoRepository;
@Service
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	private ProductoRepository productoRepository;
	@Override
	public Producto insertar(Producto ca) {
		
		return productoRepository.save(ca); 
	}

	@Override
	public List<Producto> listar() {
		
		return productoRepository.findAll();
	}

	@Override
	public Optional<Producto> buscarId(int id) {
		
		return productoRepository.findById(id);
	}

	@Override
	public Producto actualizar(Producto pr) {
		
		return productoRepository.save(pr);
	}

	@Override
	public List<Producto> listaPorNombreProductoMarca(String nombre) {
		
		return productoRepository.listaPorNombreProductoMarca(nombre);
	}

	@Override
	public List<Producto> listaPorNombreProductoCategoria(String nombre) {
		// TODO Auto-generated method stub
		return productoRepository.listaPorNombreProductoCategoria(nombre);
	}

}
