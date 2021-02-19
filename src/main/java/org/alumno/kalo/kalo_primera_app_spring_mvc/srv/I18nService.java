package org.alumno.kalo.kalo_primera_app_spring_mvc.srv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Service
public class I18nService {
	@Autowired
	private ReloadableResourceBundleMessageSource i18n_mensaje;
	@Autowired
	private SessionLocaleResolver idiomaPeticiones; //bean definido en alumno-service.xml

	//Consulta el idioma configurado en la peticion y devuelve su traducción	
	private String getI18nMessage(String msg) {
		 return i18n_mensaje.getMessage(msg, null,LocaleContextHolder.getLocale());
	}
	
	// Devuelve la lista traducida al idioma configurado en la petición
	public List<String> getTraduccion(List<String> listaMsgOriginal) {
		List<String> resultado=new ArrayList<String>();
		
		for (String msgOriginal : listaMsgOriginal) {
			resultado.add(getTraduccion(msgOriginal));
		}
		//FALTA Implementar método
		return resultado;
	}
	
	// Devuelve el mapa con el contenido (2º String)traducido al idioma configurado en la petición
	public Map<String, String> getTraduccion(Map<String, String> mapMsgOriginal) {
		Map<String, String> resultado=new HashMap<String,String>();
		

		mapMsgOriginal.forEach((k,v) -> resultado.put(k,getTraduccion(v)));
		
		//FALTA Implementar método
		return resultado;
	}
	
	public String getTraduccion(String msgOriginal) {
		//Idioma actual de la aplicación = LocaleContextHolder.getLocale()
		try {
		return getI18nMessage(msgOriginal);
		} catch(NoSuchMessageException e) {
			System.out.println("ERROR: String I18nService.getTraduccion(String):"+ e.getMessage());
			return msgOriginal;
		}
	}
	
	//Detectamos idioma de la petición (navegador) y lo cambiamos si es distinto al 
	//valor por defecto.
	public void configuraIdiomaPeticion(HttpServletRequest request,
			HttpServletResponse response, Locale locale) {
		//Consultamos el idioma de la petición. El navegador suele incluir en
		//su petición el idioma configurado por defecto
		//System.out.println("Idioma por defecto: "+locale.getLanguage());
		Optional<Locale> idiomaPeticion= getIdiomaPeticion(request);
		if (idiomaPeticion.isPresent()) {//La petición contiene idioma 
			//porque lo rellena el navegador
			//System.out.println("Idioma Petición: "+idiomaPeticion.get().getLanguage());
			if (!locale.equals(idiomaPeticion.get())){
				//Cambiamos el idioma de la sesión al de la petición
				//System.out.println("Actualizamos idioma al de la petición");
				idiomaPeticiones.setLocale(request, response, idiomaPeticion.get());	
			}				
		}		
	}
	public Optional<Locale> getIdiomaPeticion(HttpServletRequest request) {
		Locale locale = request.getLocale();
		if (locale == null)// la petición no ha enviado 'Accept-Language'
			return Optional.empty();
		else // Devolvemos el idioma de la petición (idioma del navegador)
			return Optional.of(locale);
	}
}
