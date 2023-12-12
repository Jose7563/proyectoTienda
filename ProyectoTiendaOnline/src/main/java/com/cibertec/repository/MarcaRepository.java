package com.cibertec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.cibertec.entity.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Integer> {
	@Query("select e from Marca e where e.estado=1")
	public abstract  List<Marca> listaMarcasActivo();
}
