package org.alumno.kalo.kalo_primera_app_spring_mvc.mvc;

import org.alumno.kalo.kalo_primera_app_spring_mvc.model.Pagina;
import org.alumno.kalo.kalo_primera_app_spring_mvc.srv.AlumnoService;
import org.alumno.kalo.kalo_primera_app_spring_mvc.srv.PaginaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AlumnoController {
	
	@Autowired
	AlumnoService servicioAlumno;
	
	@Autowired
	PaginaService servicioPagina;
	
	Pagina paginaAlumno = new Pagina("Alumnos","list-alumno");
	
	@RequestMapping (value="/list-alumno", method = RequestMethod.GET)
	public String listarAlumno() {
		
		servicioPagina.setPagina(paginaAlumno);
		
		return "list-alumno";
	}
	
	
}