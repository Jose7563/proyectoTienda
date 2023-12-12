package com.cibertec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cibertec.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

	@Query("select e from Categoria e where e.estado=1")
	public abstract  List<Categoria> listaCategoriasActivo();
}
