package org.alumno.kalo.kalo_primera_app_spring_mvc.mvc;

import javax.validation.Valid;

import org.alumno.kalo.kalo_primera_app_spring_mvc.excepciones.AlumnoDuplicadoException;

import org.alumno.kalo.kalo_primera_app_spring_mvc.model.Alumno;
import org.alumno.kalo.kalo_primera_app_spring_mvc.model.Pagina;
import org.alumno.kalo.kalo_primera_app_spring_mvc.srv.AlumnoService;
import org.alumno.kalo.kalo_primera_app_spring_mvc.srv.PaginaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("nombre")
public class AlumnoController {

	@Autowired
	AlumnoService servicioAlumno;

	@Autowired
	PaginaService servicioPagina;

	Pagina paginaAlumno = new Pagina("Alumnos", "list-alumno");

	// *************************************************************************************************
	// ******************* Peticion GET de LIST-ALUMNO => Reenvia a list-alumno ************************
	// *************************************************************************************************

	@RequestMapping(value = "list-alumno", method = RequestMethod.GET)
	public String listarAlumno(String ordenar,ModelMap model) {
		
		if (ordenar != "") {
			
			model.put("alumnos", servicioAlumno.listaAlumnos(ordenar));
 		} else {
 			
 			model.put("alumnos", servicioAlumno.listaAlumnos());
 		}
		model.put("pagina", paginaAlumno);
		servicioPagina.setPagina(paginaAlumno);

		return "list-alumno";
	}

	
	// *************************************************************************************************
	// ******************** Peticion GET de ADD-ALUMNO => Reenvia add-alumno ***************************
	// *************************************************************************************************

	@RequestMapping(value = "add-alumno", method = RequestMethod.GET)
	public String mostrarAddAlumno(ModelMap model) {

		model.put("alumnos", servicioAlumno.listaAlumnos());
		model.put("pagina", paginaAlumno);
//		public Alumno(String dni, int edad, String ciclo, int curso, String nombre) {
		model.addAttribute("alumno", new Alumno("", 18, "DAW", 2, "Nuevo Alumno"));
		servicioPagina.setPagina(paginaAlumno);

		return "add-alumno";
	}

	// *************************************************************************************************
	// ******************** Peticion POST de ADD-ALUMNO => Añadir un alumno  ***************************
	// *************************************************************************************************

	@RequestMapping(value = "add-alumno", method = RequestMethod.POST)
	public String addAlumno(ModelMap model, @Valid Alumno alumno, BindingResult validacion) {

		if (validacion.hasErrors()) {
			// Hay errores de validacion y debemos volver al formulario de alta.
			model.put("pagina", paginaAlumno);
			return "add-alumno";
		}

		// Si llega aqui no hay errores de Validación
		String errores = "";
		servicioPagina.setPagina(paginaAlumno);
		model.put("pagina", paginaAlumno);

		try {
			servicioAlumno.addAlumno(alumno);

			// Para evitar pasar parametros innecesarios
			model.clear();

			return "redirect:list-alumno?ordenar=";
		} catch (NumberFormatException e) {
			errores = e.toString();

		} catch (AlumnoDuplicadoException e) {
			errores = e.toString();
		}
		// Si llegamos aqui ha habido un error porque no se ejecuta la linea 54
		model.put("errores", errores);
		return "add-alumno";
	}

	// *************************************************************************************************
	// ******************** Peticion GET de DEL-ALUMNO => Eliminar Alumno ******************************
	// *************************************************************************************************

	@RequestMapping(value = "del-alumno", method = RequestMethod.GET)
	public String delAlumno(@RequestParam String dni, ModelMap model) {

//		model.put("pagina", paginaAlumno);
//		servicioPagina.setPagina(paginaAlumno);

		servicioAlumno.delAlumno(servicioAlumno.devuelveAlumno(dni));

		model.clear();

		return "redirect:list-alumno?ordenar=";
	}

// *************************************************************************************************
// *****************  Peticion GET de UPDATE-ALUMNO => Modificar un alumno *************************
// *************************************************************************************************

	@RequestMapping(value = "update-alumno", method = RequestMethod.GET)
	public String mostrarUpdateAlumno(@RequestParam String dni,ModelMap model) {

	
		model.put("pagina", paginaAlumno);
		model.addAttribute("alumno", servicioAlumno.devuelveAlumno(dni));
		return "update-alumno";
	}
	
	// *************************************************************************************************
	// ******************** Peticion POST de UPDATE-ALUMNO => Modificar un alumno  *********************
	// *************************************************************************************************

		@RequestMapping(value = "update-alumno", method = RequestMethod.POST)
		public String updateAlumno(ModelMap model, @Valid Alumno alumno, BindingResult validacion) {

			if (validacion.hasErrors()) {
				// Hay errores de validacion y debemos volver al formulario de alta.
				model.put("pagina", paginaAlumno);
				return "update-alumno";
			}

			// Si llega aqui no hay errores de Validación
			String errores = "";
			servicioPagina.setPagina(paginaAlumno);
			model.put("pagina", paginaAlumno);

			try {
				servicioAlumno.updateAlumno(alumno, "nombre");

				// Para evitar pasar parametros innecesarios
				model.clear();

				return "redirect:list-alumno?ordenar=";
			} catch (NumberFormatException e) {
				errores = e.toString();

			} catch (AlumnoDuplicadoException e) {
				errores = e.toString();
			} catch (Exception e) {
				errores = e.toString();
			}
			// Si llegamos aqui ha habido un error porque no se ejecuta la linea 54
			model.put("errores", errores);
			return "update-alumno";
		}
}