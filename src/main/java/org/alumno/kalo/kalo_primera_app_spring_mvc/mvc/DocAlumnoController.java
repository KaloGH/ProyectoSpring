package org.alumno.kalo.kalo_primera_app_spring_mvc.mvc;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.alumno.kalo.kalo_primera_app_spring_mvc.model.Alumno;
import org.alumno.kalo.kalo_primera_app_spring_mvc.model.DocAlumno;
import org.alumno.kalo.kalo_primera_app_spring_mvc.model.Pagina;
import org.alumno.kalo.kalo_primera_app_spring_mvc.model.Usuario;
import org.alumno.kalo.kalo_primera_app_spring_mvc.srv.AlumnoService;
import org.alumno.kalo.kalo_primera_app_spring_mvc.srv.DocAlumnoService;
import org.alumno.kalo.kalo_primera_app_spring_mvc.srv.PaginaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes("usuario")
    public class DocAlumnoController {
	
	@Autowired
	PaginaService servicioPagina;
	

	@Autowired
	AlumnoService servicioAlumno;
	
	@Autowired
	DocAlumnoService servicioDocumento;
	
	@ModelAttribute("listaTipos")
	public List<String> getListaTipos(){
		return servicioDocumento.listaTipos();
	}

	Pagina paginaAlumno = new Pagina("Alumnos", "list-alumno");
	
      @RequestMapping(value = ("doc-alumno") ,method=RequestMethod.GET)
      public String muestraDocumentacion(@RequestParam String dni ,ModelMap model) {
    	model.put("pagina", paginaAlumno);
  		servicioPagina.setPagina(paginaAlumno);
  		
  		model.put("documento", new DocAlumno(servicioDocumento.asignarId()));
    	model.put("alumno", servicioAlumno.devuelveAlumno(dni));
        return "doc-alumno";
      }
      
      @RequestMapping(value = ("doc-alumno") ,method=RequestMethod.POST)
      public String addDocumentacion(@RequestParam String dni ,ModelMap model) {
    	  
		  model.put("pagina", paginaAlumno);
		  servicioPagina.setPagina(paginaAlumno);
		  
		  
    	servicioDocumento.addDoc(new DocAlumno(model.getAttribute("dni").toString(),servicioDocumento.asignarId(),model.getAttribute("tipo").toString(),model.getAttribute("comentario").toString()));
  		
  		model.put("documento", new DocAlumno("",1,"",""));
  		model.put("documentos", servicioDocumento.listaDocumentacion());
    	model.put("alumno", servicioAlumno.devuelveAlumno(dni));
        return "doc-alumno";
      }
      
    }