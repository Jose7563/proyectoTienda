package com.cibertec.entity;

import org.springframework.core.annotation.Order;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "orden_detalle")
public class OrdenDetalle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private Integer cantidad;
	private double precio;
	private double total;
	@ManyToOne
	private Producto producto;

	@ManyToOne(targetEntity = Orden.class)
	@JoinColumn(name = "orden_id")
	private Orden orden;
}
