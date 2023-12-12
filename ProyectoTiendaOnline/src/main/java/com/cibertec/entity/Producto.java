package com.cibertec.entity;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="productos")
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotEmpty(message = "No debe estar vacio nombre ")
	//@Pattern(regexp = "[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ\\s+]{2,45}", message = "La campo debe contener mas de 2 carateres")
	private String nombre;
	@NotEmpty(message = "No debe estar vacio  ")
	//@Pattern(regexp = "[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ\\s+]{2,45}", message = "La descripcion debe contener mas de 2 carateres")
	private String descripcion;

	private String imagenRuta;
	@Transient
	private MultipartFile imagen;
	
	@NotNull(message = "El precio es obligatorio")
	//@Pattern(regexp = "^[0-9]+([\\.][0-9]{1,})?$", message = "La campo debe contener digitos")
	@Min(value=2, message="Valor minimo es 2 ")
	private Double precio;
	
	@NotNull(message = "La cantidad es obligatorio")
	private Integer cantidad;
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "idmarca")
	private Marca marca;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "idcategoria")
	private Categoria categoria;
//	
//	@ManyToOne
//	private Usuario usuario;
}
