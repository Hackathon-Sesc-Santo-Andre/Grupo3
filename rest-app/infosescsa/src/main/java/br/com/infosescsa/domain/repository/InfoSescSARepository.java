/**
 * 
 */
package br.com.infosescsa.domain.repository;

import java.util.List;
import java.util.Properties;


import org.ektorp.CouchDbConnector;
import org.ektorp.Page;
import org.ektorp.PageRequest;
import org.ektorp.ViewQuery;
import org.ektorp.support.CouchDbRepositorySupport;
import org.ektorp.support.GenerateView;
//import org.ektorp.CouchDbConnector;
//import org.ektorp.Page;
//import org.ektorp.PageRequest;
//import org.ektorp.ViewQuery;
//import org.ektorp.support.CouchDbRepositorySupport;
//import org.ektorp.support.GenerateView;
//import org.ektorp.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.com.infosescsa.domain.entities.Evento;

/**
 * @author saulo - Hackaton Sesc Santo Andre
 * @D08/06/2013
 */
//@Service("InfoSescSARepository")
public class InfoSescSARepository extends CouchDbRepositorySupport<Evento>{

	protected InfoSescSARepository(Class<Evento> type, CouchDbConnector db) {
		super(type, db);
		initStandardDesignDocument();
		// TODO Auto-generated constructor stub
	}

	@GenerateView @Override
	public List<Evento> getAll() {
		ViewQuery q = createQuery("all")
						.descending(true)
						.includeDocs(true);
		return db.queryView(q, Evento.class);
	}
	
	public Page<Evento> getAll(PageRequest pr) {
		ViewQuery q = createQuery("all")
						.descending(true)
						.includeDocs(true);
		return db.queryForPage(q, pr, Evento.class);
	}
	
//	@GenerateView
//	public List<Evento> findByTag(String tag) {
//		return queryView("by_tag", tag);
//	}
	
//	public void addComment(Comment c) {
//		Assert.notNull(c, "Comment may not be null");
//		Assert.hasText(c.getBlogPostId(), "Comment must have a blog post id");
//		db.create(c);
//	}
	
	
}
