/**
 * 
 */
package br.com.resapp.services;

import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 * @author saulo
 *
 */
@Path("version")
public class AppVersion {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String showVersion(){
		return " App Version 1.0";
	}

}
