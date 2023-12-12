package com.cibertec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cibertec.entity.Orden;

public interface OrdenRepository extends JpaRepository<Orden, Integer> {

	
	@Query("select e from Orden e where e.usuario.id=?1")
	public abstract  List<Orden> listaCompras(int idUsuario);
}
