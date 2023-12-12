package com.cibertec.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cibertec.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	Optional<Usuario> findByEmail(String email);
	
	@Query("select e from Usuario e where e.id=?1")
	public abstract Usuario nombreUsuarioSesion(int id );
}
