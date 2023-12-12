package com.cibertec.service;

import java.util.List;
import java.util.Optional;

import com.cibertec.entity.Usuario;

public interface UsuarioService {
	public abstract List<Usuario> listar();
	public abstract Optional<Usuario> buscarId(int id);
	public abstract Usuario insertar (Usuario usuario);
	public abstract Usuario actualizar(Usuario usuario);
	public abstract Optional<Usuario> buscarPorEmail(String email);

	public abstract Usuario nombreUsuarioSesion(int id );
	
}
