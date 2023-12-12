package com.cibertec.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.entity.Usuario;
import com.cibertec.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl   implements UsuarioService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public List<Usuario> listar() {
		
		return usuarioRepository.findAll();
	}

	@Override
	public Optional<Usuario> buscarId(int id) {
			return usuarioRepository.findById(id);
	}

	@Override
	public Usuario insertar(Usuario usuario) {
		
		return usuarioRepository.save(usuario);
	}

	@Override
	public Usuario actualizar(Usuario usuario) {
		
		return usuarioRepository.save(usuario);
	}

	@Override
	public Optional<Usuario> buscarPorEmail(String email) {
		
		return usuarioRepository.findByEmail(email);
	}

	@Override
	public Usuario nombreUsuarioSesion(int id) {
		// TODO Auto-generated method stub
		return usuarioRepository.nombreUsuarioSesion(id);
	}

}
