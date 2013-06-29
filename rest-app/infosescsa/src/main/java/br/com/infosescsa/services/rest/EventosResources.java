package br.com.infosescsa.services.rest;


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
import org.ektorp.ViewQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import br.com.infosescsa.domain.entities.Evento;
import br.com.infosescsa.domain.repository.CouchDbFactory;
import br.com.infosescsa.domain.repository.InfoSescSARepository;

@Path("eventos")
public class EventosResources {


	@GET
	// @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public List<Evento> getEventos() {
		return CouchDbFactory.getInstance().getAll();
	}

	@Path("{id}")
	@GET
	@Produces("application/json")
	public Evento getEvento(@PathParam("id") String id) {
		return CouchDbFactory.getInstance().get(id);
	}

	@POST
	@Consumes("text/xml")
	@Produces("text/plain")
	public String adicionaEvento(Evento evento) {
		CouchDbFactory.getInstance().add(evento);
		return evento.getId() + " Adicionado ! ";
	}

	@Path("{id}")
	@PUT
	@Consumes("text/xml")
	@Produces("text/plain")
	public String atualizaEvento(Evento evento, @PathParam("id") String id) {
		Evento eventoEncontrado = CouchDbFactory.getInstance().get(id);
		eventoEncontrado.setData(evento.getData());
		eventoEncontrado.setDescricao(evento.getDescricao());
		eventoEncontrado.setTitulo(evento.getTitulo());
		eventoEncontrado.setCategoria(evento.getCategoria());
		CouchDbFactory.getInstance().update(eventoEncontrado);
		return eventoEncontrado.getId() + " Atualizado ! ";
	}

	@Path("{id}")
	@DELETE
	@Produces(MediaType.APPLICATION_XML)
	public String removeEvento(@PathParam("id") String id) {
		Evento evento = CouchDbFactory.getInstance().get(id);
		CouchDbFactory.getInstance().remove(evento);
		return "Evento Removido";

	}

	/*
	 * public static CouchDbConnector connectionCouch(){
	 * 
	 * HttpClient httpClient=null; try { httpClient = new
	 * StdHttpClient.Builder().url("http://127.0.0.1:5984").build(); } catch
	 * (MalformedURLException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } CouchDbInstance dbInstance = new
	 * StdCouchDbInstance(httpClient); // if the second parameter is true, the
	 * database will be created if it doesn't exists CouchDbConnector db =
	 * dbInstance.createConnector("infosesc", true); return db; }
	 */

}