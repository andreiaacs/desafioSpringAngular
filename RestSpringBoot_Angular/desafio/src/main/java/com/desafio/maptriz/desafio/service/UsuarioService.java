package com.desafio.maptriz.desafio.service;

import java.util.List;

import com.desafio.maptriz.desafio.model.Usuario;

public interface UsuarioService {
	
	Usuario getUsuarioById(long id);
	List<Usuario> getAllUsuario();
	void saveUsuario(Usuario usuario);
	void deleteUsuario(Usuario usuario);
	void updateUsuario(long id, Usuario usuario);

}
