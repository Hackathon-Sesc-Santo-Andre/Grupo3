package br.com.infosescsa.mbeans;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.model.StreamedContent;

import br.com.infosescsa.domain.entities.Evento;
import br.com.infosescsa.domain.repository.CouchDbFactory;
import br.com.infosescsa.utils.QRCodeGenerateUtil;

import com.google.api.services.urlshortener.Urlshortener;
import com.google.api.services.urlshortener.model.Url;

@ManagedBean(name="eventoMBean")
@ViewScoped
public class EventosMbean implements Serializable{
	
	private static final long serialVersionUID = 1L;

//	private InfoSescSARepository infoSescSARepository;
	
	private Evento evento;
	
	private StreamedContent scImage;
	
	private Evento eventoSelecionado;

	private List<Evento> listaEventos;
	
	@PostConstruct
	public void init(){
		this.evento = new Evento();
		
		this.listaEventos = CouchDbFactory.getInstance().getAll();
		generateQRCode();
//		this.listaEventos = new ArrayList<Evento>();
//		listaEventos.add(CouchDbFactory.getInstance().find(Evento.class, "doc-id", new Params().addParam("id", 7)));
		
//		this.scImage= QRCodeGenerateUtil.generateQRCode("teste", null);
		
		System.out.println("Inicializando");
	}


	private void generateQRCode() {
		if(listaEventos !=null && !listaEventos.isEmpty()){
			for(Evento ev : listaEventos){
				FacesContext facesContext = FacesContext.getCurrentInstance();  
				ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();  
				String arquivo = scontext.getRealPath("/resources/qrcode/evento_"+ev.getId()+".png");
				if(!new File(arquivo).isFile()){
					QRCodeGenerateUtil.generateQRCode(ev.getId(), arquivo);
				}
			}
		}
	}
	
	private String urlShort(String url){
//		AppIdentityCredential credential =
////		        new AppIdentityCredential(Arrays.asList(UrlshortenerScopes.URLSHORTENER));
//		Urlshortener urlshortener =  new Urlshortener.Builder(new UrlFetchTransport(), new JacksonFactory(), null)
//		        .build();
		
//		Urlshortener urlshortener = new 
//	    Url toInsert = new Url().setLongUrl(longUrl);

		return null;
	}
	
	public void salvaEvento(){
		if(evento !=null){
		//	CouchDbFactory.getInstance().create(evento);
			CouchDbFactory.getInstance().add(evento);
			listaEventos.add(evento);
			generateQRCode();
		}
	}
	
	public void excluirEvento(){
		if(eventoSelecionado!=null){
			CouchDbFactory.getInstance().remove(eventoSelecionado);
		}
		
	}
	
	
	public void limpaEvento(){
		evento = new Evento();
	}
	
	public void alterarEvento(){
		if(eventoSelecionado!=null){
			evento=eventoSelecionado;
			
		}
			
	}


	public Evento getEvento() {
		return evento;
	}


	public void setEvento(Evento evento) {
		this.evento = evento;
	}


	public List<Evento> getListaEventos() {
		return listaEventos;
	}


	public void setListaEventos(List<Evento> listaEventos) {
		this.listaEventos = listaEventos;
	}


	public StreamedContent getScImage() {
		return scImage;
	}


	public void setScImage(StreamedContent scImage) {
		this.scImage = scImage;
	}


	public Evento getEventoSelecionado() {
		return eventoSelecionado;
	}


	public void setEventoSelecionado(Evento eventoSelecionado) {
		this.eventoSelecionado = eventoSelecionado;
	}
}
