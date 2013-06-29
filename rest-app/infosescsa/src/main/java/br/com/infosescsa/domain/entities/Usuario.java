/**
 * 
 */
package br.com.infosescsa.domain.entities;

/**
 * @author saulo - Hackaton Sesc Santo Andre
 * @01/06/2013
 * 
 */

public class Usuario {
	
	private String nome;
	
	private String senha;
	
	private String email;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
}
