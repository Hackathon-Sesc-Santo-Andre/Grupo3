/**
 * 
 */
package br.com.resapp.couchdb;

import java.net.MalformedURLException;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbInstance;

/**
 * @author saulo - Hackaton Sesc Santo Andre
 * @02/06/2013
 * 
 */
public class CouchDB {
	
	public static void main(String[] args) {
		
		HttpClient httpClient;
		try {
			System.out.println("Iniciando a Conexao !");
			httpClient = new StdHttpClient.Builder().url("http://localhost:5984")
													.username("")
													.password("")
													.build();
			CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
			// if the second parameter is true, the database will be created if it doesn't exists
			CouchDbConnector db = dbInstance.createConnector("infosesc", true);
			
			
			System.out.println("Fim a Conexao !");

			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
