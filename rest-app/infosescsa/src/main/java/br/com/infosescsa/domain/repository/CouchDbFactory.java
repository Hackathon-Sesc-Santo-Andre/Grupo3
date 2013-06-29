/**
 * 
 */
package br.com.infosescsa.domain.repository;

import java.net.MalformedURLException;
import java.util.Date;
import java.util.List;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbInstance;

import br.com.infosescsa.domain.entities.Evento;


/**
 * @author saulo - Hackaton Sesc Santo Andre
 * @D09/06/2013
 */
public final class CouchDbFactory{
	
	private static CouchDbConnector couchdb; 
	
	private static InfoSescSARepository repository;
	
	public static InfoSescSARepository getInstance() {
		if(couchdb==null){
			HttpClient httpClient=null;
			System.out.println("Iniciando a Conexao !");
			try {
				httpClient = new StdHttpClient.Builder().url("http://localhost:5984")
														.username("")
														.password("")
														.build();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
			// if the second parameter is true, the database will be created if it doesn't exists
			CouchDbConnector db = dbInstance.createConnector("infosesc", true);
			
			
			repository = new InfoSescSARepository(Evento.class, db);
			
//			List<Evento> evento = repository.getAll();
//			System.out.println();
		}
		return repository;
	}
	
	public static void main(String[] args) {
		CouchDbFactory.getInstance();
	}
}
