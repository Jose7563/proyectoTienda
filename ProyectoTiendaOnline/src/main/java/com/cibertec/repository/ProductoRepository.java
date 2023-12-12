package com.cibertec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cibertec.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

	@Query("select e from Producto e where e.marca.nombre=?1")
	public abstract  List<Producto> listaPorNombreProductoMarca(String nombre);
	@Query("select e from Producto e where e.categoria.nombre=?1")
	public abstract  List<Producto> listaPorNombreProductoCategoria(String nombre);
	
	@Modifying
	@Query("update Producto e set e.cantidad=?1 where e.id=?2")
	public   void actualizarStock(int cantidad, int id );
	
}
