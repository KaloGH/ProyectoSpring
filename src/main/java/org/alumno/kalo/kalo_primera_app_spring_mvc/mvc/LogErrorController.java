package org.alumno.kalo.kalo_primera_app_spring_mvc.mvc;


import org.alumno.kalo.kalo_primera_app_spring_mvc.model.LogError;
import org.alumno.kalo.kalo_primera_app_spring_mvc.model.Pagina;
import org.alumno.kalo.kalo_primera_app_spring_mvc.model.Usuario;
import org.alumno.kalo.kalo_primera_app_spring_mvc.srv.LogErrorService;
import org.alumno.kalo.kalo_primera_app_spring_mvc.srv.PaginaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("usuario")
public class LogErrorController {
	
	Pagina paginaLogError = new Pagina("Errores", "list-logerror");


	@Autowired
	PaginaService servicioPagina;
	
	@Autowired
	LogErrorService servicioLogError;
	

	// *************************************************************************************************
	// ******************* Peticion GET de LIST-Errores => Reenvia a list-errores ************************
	// *************************************************************************************************

	@RequestMapping(value = "list-logerror", method = RequestMethod.GET)
	public String listarLogError(@RequestParam(required = false) String ordenar ,@RequestParam(required = false) String campoFiltro,
			@RequestParam(required = false) String textoFiltro,ModelMap model) {
			Usuario user = (Usuario) model.getAttribute("usuario");

			if (user.getNickname() == "") {
				servicioLogError.addLogError(new LogError(servicioLogError.asignarId(),"Acceso Denegado","Usuario anónimo ha intentado acceder a la web"));
				return "redirect:login";
			}
		
		//Pasamos el titulo que llevara la pagina.
				model.put("pagina", paginaLogError);
				
				//Pasamos la lista de errores.
				if (ordenar != null) {	
					model.put("listaErrores", servicioLogError.listaErrores(ordenar));
				} 
				
				if ((campoFiltro != null) && (textoFiltro != null) ) {
					
					model.put("listaErrores", servicioLogError.listaConFiltro(campoFiltro, textoFiltro));
					
				} else {
					model.put("listaErrores", servicioLogError.listaErrores());
				}
				
				//Reenviamos a la vista JSP.
				return "list-logerror";
	}
	
	// *************************************************************************************************
	// ******************* Peticion GET de Del-Errores => Reenvia a del-errores ************************
	// *************************************************************************************************

	@RequestMapping(value = "del-logerror", method = RequestMethod.GET)
	public String mostrarDelLogError(@RequestParam String id , ModelMap model) {
		
		//Pasamos el titulo que llevara la pagina.
				model.put("pagina", paginaLogError);
				
				model.put("logError", servicioLogError.devuelveError(Integer.parseInt(id)));
				
				//Reenviamos a la vista JSP.
				return "del-logerror";
	}
	
	// *************************************************************************************************
	// ******************* Peticion POST de Del-Errores => Reenvia a del-errores ************************
	// *************************************************************************************************

	@RequestMapping(value = "del-logerror", method = RequestMethod.POST)
	public String delLogError(@RequestParam String id ,ModelMap model) {
		
		//Pasamos el titulo que llevara la pagina.
				model.put("pagina", paginaLogError);
				
				servicioLogError.delLogError(servicioLogError.devuelveError(Integer.parseInt(id)));
				
				//Reenviamos a la vista JSP.
				return "redirect:list-logerror";
	}

	
	
}