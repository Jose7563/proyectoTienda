package com.cibertec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cibertec.entity.Orden;
import com.cibertec.entity.OrdenDetalle;



public interface OrdenDetalleRepository extends JpaRepository<OrdenDetalle, Integer>{

	@Query("select e from OrdenDetalle e where e.orden.id=?1")
	public abstract  List<OrdenDetalle> listaDetalles(int id);
}
