/**
 * 
 */
package br.com.infosescsa.mbeans;

import javax.faces.bean.ManagedBean;

import br.com.infosescsa.domain.entities.Usuario;

/**
 * @author saulo
 * 
 */
@ManagedBean(name = "loginMBean")
public class LoginMBean {

	private Usuario usuario;

	public void init() {
		this.usuario = new Usuario();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public void verificaUsuario(){
		
		
	}

}
