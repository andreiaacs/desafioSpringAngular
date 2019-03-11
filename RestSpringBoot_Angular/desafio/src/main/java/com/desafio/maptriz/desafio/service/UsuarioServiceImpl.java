package com.desafio.maptriz.desafio.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.maptriz.desafio.model.Usuario;
import com.desafio.maptriz.desafio.repository.UsuarioRepository;


@Service("usuarioService")
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	UsuarioRepository usuarioRepository; 

	@Override
	public Usuario getUsuarioById(long id) {
		return usuarioRepository.findOne(id);
	}

	@Override
	public List<Usuario> getAllUsuario() {
		return usuarioRepository.findAll();
	}
	
	@Override
	public void saveUsuario(final Usuario usuario) {
		usuarioRepository.save(usuario);
	}

	@Override
	public void deleteUsuario(final Usuario usuario) {		
		usuarioRepository.delete(usuario);
	}

	@Override
	public void updateUsuario(long id, final Usuario usuario) {
		Usuario u = new Usuario();
		u = usuarioRepository.findOne(id);
		u.setDataNascimento(usuario.getDataNascimento());
		u.setCpf(usuario.getCpf());
		u.setDataUltimoLogin(usuario.getDataUltimoLogin());
		usuarioRepository.save(u);	
		
	}
}
