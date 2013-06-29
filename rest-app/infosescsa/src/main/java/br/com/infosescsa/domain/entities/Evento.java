/**
 * 
 */
package br.com.infosescsa.domain.entities;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;
import org.ektorp.support.TypeDiscriminator;

/**
 * @author saulo - Hackaton Sesc Santo Andre
 * @01/06/2013
 * 
 */
@XmlRootElement
public class Evento{
	
	private String id;
	
    private String revision;

    @TypeDiscriminator
	private Long codigoEvento;

    @TypeDiscriminator
	private String descricao;

    @TypeDiscriminator
	private Date data;
    
    @TypeDiscriminator
	private String titulo;
	
    private Integer categoria;
    
    
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

	public Long getCodigoEvento() {
		return codigoEvento;
	}

	public void setCodigoEvento(Long codigoEvento) {
		this.codigoEvento = codigoEvento;
	}

    @JsonProperty("_rev")
	public String getRevision() {
		return revision;
	}
    
    @JsonProperty("_rev")
	public void setRevision(String revision) {
		this.revision = revision;
	}

	@JsonProperty("_id")
	public String getId() {
		return id;
	}
    
	@JsonProperty("_id")
	public void setId(String id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getCategoria() {
		return categoria;
	}

	public void setCategoria(Integer categoria) {
		this.categoria = categoria;
	}


	
}
