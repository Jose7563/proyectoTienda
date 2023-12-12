package com.cibertec.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "categorias")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; 
	
	@NotEmpty(message = "No debe estar vacio nombre ")
	@Column(length = 30)
	//@Pattern(regexp = "[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ\\s+]{2,45}", message = "La categoria debe contener mas de 2 carateres")
	private String nombre;
	
	 private int estado; 
	
	 
}
