package com.desafio.maptriz.desafio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.desafio.maptriz.desafio.model.Usuario;
import com.desafio.maptriz.desafio.service.UsuarioService;

@RestController
@RequestMapping("")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
		
    @RequestMapping(value = "/usuario", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Usuario> getUsuario() {
		return usuarioService.getAllUsuario();
	}

    @RequestMapping(value = "/usuario/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:4200")
    public Usuario getUsuario(@PathVariable("id") long id) {
		return usuarioService.getUsuarioById(id);
	}
    
  //-------------------Create a User--------------------------------------------------------
    
    @RequestMapping(value = "/usuario", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Void> createUser(@RequestBody Usuario usuario, UriComponentsBuilder ucBuilder) {
        
    	System.out.println("Creating User " + usuario.getNome()); 
 
        usuarioService.saveUsuario(usuario);
 
        HttpHeaders headers = new HttpHeaders();
        
        headers.setLocation(ucBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 
     
    //------------------- Update a User --------------------------------------------------------
     
    @RequestMapping(value = "/updateusuario/{id}", method = RequestMethod.PUT)
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Usuario> updateUser(@PathVariable("id") long id, @RequestBody Usuario usuario) {
        System.out.println("Updating User " + id);
         
        Usuario currentUsuario = usuarioService.getUsuarioById(id);
         
        if (currentUsuario==null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
        }
 
        currentUsuario.setNome(usuario.getNome());
        currentUsuario.setDataNascimento(usuario.getDataNascimento());
        currentUsuario.setCpf(usuario.getCpf());
        currentUsuario.setDataUltimoLogin(usuario.getDataUltimoLogin());
         
        usuarioService.updateUsuario(id, currentUsuario);
        
        return new ResponseEntity<Usuario>(currentUsuario, HttpStatus.OK);
    }
 
    //------------------- Delete a User --------------------------------------------------------
     
    @RequestMapping(value = "/deleteusuario/{id}", method = RequestMethod.DELETE)
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Usuario> deleteUser(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting User with id " + id);
 
        Usuario usuario = usuarioService.getUsuarioById(id);
        if (usuario == null) {
            System.out.println("Unable to delete. User with id " + id + " not found");
            return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
        }
 
        usuarioService.deleteUsuario(usuario);
        return new ResponseEntity<Usuario>(HttpStatus.NO_CONTENT);
    }
 

}
