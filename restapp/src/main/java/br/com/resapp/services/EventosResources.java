/**
 * 
 */
package br.com.resapp.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.resapp.entities.Evento;

/**
 * @author saulo - Hackaton Sesc Santo Andre
 * @01/06/2013
 * 
 */
@Path("eventos")
public class EventosResources {
	static private Map<Long, Evento> eventosMap; 
	
	static { 
		eventosMap = new HashMap<Long, Evento>();
		
		Evento evento1 = new Evento();
		evento1.setId(1l);
		evento1.setData("12/02/1988");
		evento1.setDescricao("Aniversario Saulo");
		
		Evento evento2 = new Evento();
		evento2.setId(2l);
		evento2.setData("19/07/1991");
		evento2.setDescricao("Aniversario Patricia");
		
		eventosMap.put(evento1.getId(),evento1);
		eventosMap.put(evento2.getId(),evento2);
	}

	@GET
	//@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public List<Evento> getEventos(){
		List<Evento> eventoMock = new ArrayList<Evento>(eventosMap.values());
		return eventoMock;
	}

	@Path("{id}") 
	@GET 
	@Produces("application/json")
	public Evento getEvento(@PathParam("id") int id){
		return eventosMap.get(new Long(id));
	}
	
	@POST 
	@Consumes("text/xml") 
	@Produces("text/plain")
	public String adicionaEvento(Evento evento){
		evento.setId(eventosMap.size()+1l);
		eventosMap.put(evento.getId(), evento);
		return evento.getId() + " Adicionado ! ";
	}	
	
	@Path("{id}") 
	@PUT 
	@Consumes("text/xml") 
	@Produces("text/plain")
	public String atualizaEvento(Evento evento, @PathParam("id") int id){
		Evento eventoEncontrado = eventosMap.get(new Long(id));
		eventoEncontrado.setData(evento.getData());
		eventoEncontrado.setDescricao(evento.getDescricao());
		return eventoEncontrado.getId() + " Atualizado ! ";
	}
	
	@Path("{id}") 
	@DELETE 
	@Produces(MediaType.APPLICATION_XML)
	public String removeEvento(@PathParam("id") int id){
		eventosMap.remove(new Long(id));
		return "Evento Removido";
		
	}
}
