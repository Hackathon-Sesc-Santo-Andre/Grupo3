package br.com.infosescsa.utils;

import java.io.Serializable;
import java.util.Date;

import org.primefaces.model.StreamedContent;

public class EventoVO implements Serializable{
	

	 
	private static final long serialVersionUID = 1L;

	public EventoVO(){
		
	}
	
	public EventoVO(Long id,  Date data, String descricao){
		this.id = id;
		this.descricao = descricao;
		this.data = data;
	}
	
	private Long id;

	private String descricao;

	private Date data;
	
	private StreamedContent imageQRCode;

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

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public StreamedContent getImageQRCode() {
		return imageQRCode;
	}

	public void setImageQRCode(StreamedContent imageQRCode) {
		this.imageQRCode = imageQRCode;
	}	

}
