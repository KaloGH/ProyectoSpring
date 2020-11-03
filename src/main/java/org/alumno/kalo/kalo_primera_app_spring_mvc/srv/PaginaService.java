package org.alumno.kalo.kalo_primera_app_spring_mvc.srv;

import org.alumno.kalo.kalo_primera_app_spring_mvc.model.Pagina;
import org.springframework.stereotype.Service;

@Service
public class PaginaService {
	
	
	private static Pagina pagina = new Pagina("Login","Home");
	
	public Pagina getPagina() {
		return pagina;
	}
	
	public void setPagina(Pagina nuevaPagina) {
		pagina = nuevaPagina;
	}

}
