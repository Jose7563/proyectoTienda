package com.cibertec.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "ordenes")
public class Orden {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Date fechaCreacion;
	//private Date fechaRecibida;

	private double total;
	
	@ManyToOne
	private Usuario usuario;
	
	@OneToMany(mappedBy = "orden")
	private List<OrdenDetalle> detalle;
}
