/**
 * 
 */
package br.com.resapp.entities;

import javax.xml.bind.annotation.*;

/**
 * @author saulo - Hackaton Sesc Santo Andre
 * @01/06/2013
 * 
 */
@XmlRootElement
public class Evento {

	private Long id;
	
	private String descricao;
	
	private String data;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	
}
