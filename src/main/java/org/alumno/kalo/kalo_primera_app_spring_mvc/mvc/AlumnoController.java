package org.alumno.kalo.kalo_primera_app_spring_mvc.mvc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.alumno.kalo.kalo_primera_app_spring_mvc.excepciones.AlumnoDuplicadoException;

import org.alumno.kalo.kalo_primera_app_spring_mvc.model.Alumno;
import org.alumno.kalo.kalo_primera_app_spring_mvc.model.FiltroAlumno;
import org.alumno.kalo.kalo_primera_app_spring_mvc.model.LogError;
import org.alumno.kalo.kalo_primera_app_spring_mvc.model.Modulo;
import org.alumno.kalo.kalo_primera_app_spring_mvc.model.Pagina;
import org.alumno.kalo.kalo_primera_app_spring_mvc.model.Usuario;
import org.alumno.kalo.kalo_primera_app_spring_mvc.srv.AlumnoService;
import org.alumno.kalo.kalo_primera_app_spring_mvc.srv.LogErrorService;
import org.alumno.kalo.kalo_primera_app_spring_mvc.srv.ModuloService;
import org.alumno.kalo.kalo_primera_app_spring_mvc.srv.PaginaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("usuario")
public class AlumnoController {
	
	Pagina paginaAlumno = new Pagina("Alumnos", "list-alumno");

	@Autowired
	AlumnoService servicioAlumno;

	@Autowired
	PaginaService servicioPagina;
	
	@Autowired
	LogErrorService servicioLogError;
	
	@Autowired
	ModuloService servicioModulo;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		
	}


	// *************************************************************************************************
	// ******************* Peticion GET de LIST-ALUMNO => Reenvia a list-alumno ************************
	// *************************************************************************************************

	@RequestMapping(value = "list-alumno", method = RequestMethod.GET)
	public String listarAlumno(@RequestParam(required = false)String ordenar,ModelMap model) {
		Usuario user = (Usuario) model.getAttribute("usuario");
		
		System.out.println(((Usuario) model.getAttribute("usuario")).getNickname()); //TODO: Arreglar esto. Falla porque funciona sin nombre.
		
		if (user.getNickname() == "") {
			servicioLogError.addLogError(new LogError(servicioLogError.asignarId(),"Acceso Denegado","Usuario anónimo ha intentado acceder a la web"));
			model.clear();
			return "redirect:login";
		}
		
		if (model.getAttribute("usuario") == null) {
			model.put("errores", "Usuario no Logeado. Porfavor Inicia Sesion");
			model.clear();
			return "redirect:login";
		}
		
		model.put("filtroAlumno", new FiltroAlumno("",""));
		
		if (ordenar != null) {
			model.put("alumnos", servicioAlumno.listaAlumnos(ordenar));
 		} else {
 			model.put("alumnos", servicioAlumno.listaAlumnos());
 		}
		model.put("pagina", paginaAlumno);
		servicioPagina.setPagina(paginaAlumno);

		return "list-alumno";
	}
	
	// *************************************************************************************************
	// ******************* Peticion POST de LIST-ALUMNO => Reenvia a list-alumno ************************
	// *************************************************************************************************

	@RequestMapping(value = "filter-alumno", method = RequestMethod.POST)
	public String listarAlumnoConFiltro(@Valid FiltroAlumno filtroAlumno,ModelMap model) {
		
		model.put("alumnos", servicioAlumno.filtraAlumnos(filtroAlumno));
		model.put("pagina", paginaAlumno);
		servicioPagina.setPagina(paginaAlumno);

		return "list-alumno";
	}

	
	// *************************************************************************************************
	// ******************** Peticion GET de ADD-ALUMNO => Reenvia add-alumno ***************************
	// *************************************************************************************************

	@RequestMapping(value = "add-alumno", method = RequestMethod.GET)
	public String mostrarAddAlumno(ModelMap model) {
		String[] superInteresao = {"Backend","Frontend"};
		int[] modulaso = {0,1};

		model.put("alumnos", servicioAlumno.listaAlumnos());
		model.put("pagina", paginaAlumno);
//		public Alumno(String dni, int edad, String ciclo, int curso, String nombre) {
//		model.addAttribute("interesadoEnLista",servicioAlumno.listaInteresadoEn().toArray());
		model.addAttribute("alumno", new Alumno("", 18, "DAW", 2, "Nuevo Alumno",true,superInteresao,"Python","Tarde","FR",modulaso,"Ingresa los Hobbies del alumno aquí."));
		servicioPagina.setPagina(paginaAlumno);

		return "add-alumno";
	}
	
	@ModelAttribute("interesadoEnLista")
	public Object[] getInteresadoEnLista() {
		return servicioAlumno.listaInteresadoEn().toArray();
	}
	
	@ModelAttribute("generoLista")
	public Object[] getGeneroLista() {
		return servicioAlumno.listaGeneros().toArray();
	}
	
	@ModelAttribute("horarioLista")
	public Object[] getHorarioLista() {
		return servicioAlumno.listaHorario().toArray();
	}
	
	@ModelAttribute("paisLista")
	public Map<String,String> getPais() {
		return servicioAlumno.devuelvePais();
	}
	
	@ModelAttribute("moduloLista")
	public List<Modulo> modelLista(){
		return servicioModulo.listar("");
	}
	
	@ModelAttribute("listaFiltrar")
	public HashMap<String,String> getListaFiltros(){
		return servicioAlumno.listarCamposBusqueda();
	}
	

	// *************************************************************************************************
	// ******************** Peticion POST de ADD-ALUMNO => A�adir un alumno  ***************************
	// *************************************************************************************************

	@RequestMapping(value = "add-alumno", method = RequestMethod.POST)
	public String addAlumno(ModelMap model, @Valid Alumno alumno, BindingResult validacion) {

		if (validacion.hasErrors()) {
			// Hay errores de validacion y debemos volver al formulario de alta.
			model.put("pagina", paginaAlumno);
			return "add-alumno";
		}

		// Si llega aqui no hay errores de Validaci�n
		String errores = "";
		servicioPagina.setPagina(paginaAlumno);
		model.put("pagina", paginaAlumno);

		try {
			servicioAlumno.addAlumno(alumno);

			// Para evitar pasar parametros innecesarios
			model.clear();

			return "redirect:list-alumno";
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

			// Si llega aqui no hay errores de Validaci�n
			String errores = "";
			servicioPagina.setPagina(paginaAlumno);
			model.put("pagina", paginaAlumno);
				try {
					servicioAlumno.updateAlumno(alumno, model.getAttribute("usuario").toString()); // Cogemos variable sesion nombre y hacemos String.
	
					// Para evitar pasar parametros innecesarios
					model.clear();
	
					model.put("errores", errores);
					return "redirect:list-alumno?ordenar=";
				} catch (Exception e) {
					errores = e.getMessage();
//					errores = e.toString();
				}
				// Si llegamos aqui ha habido un error porque no se ejecuta la linea 54
				model.put("errores", errores);
				return "update-alumno";
			
			
		}
}