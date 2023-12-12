package com.cibertec.service;

import java.util.List;

import com.cibertec.entity.Orden;
import com.cibertec.entity.OrdenDetalle;
import com.cibertec.entity.Usuario;

public interface OrdenService {

	public abstract Orden insertar(Orden ca);
	public abstract List<Orden> listar();
	public abstract void realizarVenta(Orden o,  List<OrdenDetalle> detalles, Usuario usuario); 
	
	public abstract  List<Orden> listaCompras(int idUsuario);
}
