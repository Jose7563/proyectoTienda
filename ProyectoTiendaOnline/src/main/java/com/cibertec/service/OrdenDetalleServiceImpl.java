package com.cibertec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.entity.OrdenDetalle;
import com.cibertec.repository.OrdenDetalleRepository;
@Service
public class OrdenDetalleServiceImpl implements OrdenDetalleService{

	@Autowired
	private OrdenDetalleRepository repo; 
	@Override
	public OrdenDetalle insertar(OrdenDetalle ca) {
		
		return repo.save(ca);
	}
	@Override
	public List<OrdenDetalle> listaDetalles(int id) {
		
		return repo.listaDetalles(id);
	}

}
