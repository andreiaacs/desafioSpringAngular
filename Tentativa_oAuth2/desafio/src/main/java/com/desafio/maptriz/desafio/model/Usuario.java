package com.desafio.maptriz.desafio.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	private String dataNascimento;
	private String cpf;
	private String dataUltimoLogin;
	
	
	
	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Usuario(Long id, String nome, String dataNascimento, String cpf, String dataUltimoLogin) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.cpf = cpf;
		this.dataUltimoLogin = dataUltimoLogin;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getDataNascimento() {
		return dataNascimento;
	}



	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}



	public String getCpf() {
		return cpf;
	}



	public void setCpf(String cpf) {
		this.cpf = cpf;
	}



	public String getDataUltimoLogin() {
		return dataUltimoLogin;
	}



	public void setDataUltimoLogin(String dataUltimoLogin) {
		this.dataUltimoLogin = dataUltimoLogin;
	}
	
	
	
	
	

}
