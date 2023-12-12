package com.cibertec.service;


import java.util.List;

import com.cibertec.entity.OrdenDetalle;

public interface OrdenDetalleService {
	public abstract OrdenDetalle insertar(OrdenDetalle ca);
	public abstract  List<OrdenDetalle> listaDetalles(int id);
}
