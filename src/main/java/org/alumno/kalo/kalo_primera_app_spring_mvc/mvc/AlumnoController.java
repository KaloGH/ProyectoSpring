package org.alumno.kalo.kalo_primera_app_spring_mvc.mvc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.alumno.kalo.kalo_primera_app_spring_mvc.excepciones.AlumnoDuplicadoException;
import org.alumno.kalo.kalo_primera_app_spring_mvc.model.FiltroAlumno;
import org.alumno.kalo.kalo_primera_app_spring_mvc.model.FiltroAvanzadoAlumno;
import org.alumno.kalo.kalo_primera_app_spring_mvc.model.LogError;
import org.alumno.kalo.kalo_primera_app_spring_mvc.model.Modulo;
import org.alumno.kalo.kalo_primera_app_spring_mvc.model.Pagina;
import org.alumno.kalo.kalo_primera_app_spring_mvc.model.Usuario;
import org.alumno.kalo.kalo_primera_app_spring_mvc.model.dto.AlumnoEdit;
import org.alumno.kalo.kalo_primera_app_spring_mvc.model.ram.Alumno;
import org.alumno.kalo.kalo_primera_app_spring_mvc.model.ram.DocAlumno;
import org.alumno.kalo.kalo_primera_app_spring_mvc.srv.AlumnoService;
import org.alumno.kalo.kalo_primera_app_spring_mvc.srv.FileService;
import org.alumno.kalo.kalo_primera_app_spring_mvc.srv.I18nService;
import org.alumno.kalo.kalo_primera_app_spring_mvc.srv.LogErrorService;
import org.alumno.kalo.kalo_primera_app_spring_mvc.srv.ModuloService;
import org.alumno.kalo.kalo_primera_app_spring_mvc.srv.PaginaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.server.ResponseStatusException;

@Controller
@SessionAttributes({"usuario","loginName","loginNickName"})
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
	
	@Autowired
	FileService servicioFile;
	
	@Autowired
	I18nService servicioIdiomas;
	
	
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
		paginaAlumno.setIdioma(servicioIdiomas.getIdioma());
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
		
		model.put("filtroAvanzadoAlumno", new FiltroAvanzadoAlumno());
		
		if (ordenar != null && !ordenar.equals("")) {
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
	// ******************* Peticion POST de LIST-ALUMNO => Reenvia a list-alumno ************************
	// *************************************************************************************************

	@RequestMapping(value = "filtro-avanzado-alumnos", method = RequestMethod.POST)
	public String listarAlumnoConFiltroAvanzado(@Valid FiltroAvanzadoAlumno filtroAvanzadoAlumnos,BindingResult validacion ,ModelMap model) {
		
		model.put("pagina", paginaAlumno);
		servicioPagina.setPagina(paginaAlumno);
		
		model.put("filtroAvanzadoAlumno",filtroAvanzadoAlumnos);
		
		if (validacion.hasErrors()) {
			model.put("alumnos", servicioAlumno.listaAlumnos());
		} else {
			model.put("alumnos",servicioAlumno.filtroAvanzadoAlumnos(filtroAvanzadoAlumnos));
		}
		
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
		model.addAttribute("alumnoEdit",new AlumnoEdit());

		return "add-alumno";
	}
	
	@ModelAttribute("interesadoEnLista")
	public Object[] getInteresadoEnLista() {
		return servicioAlumno.listaInteresadoEn().toArray();
	}
	
	@ModelAttribute("generoLista")
	public List<String> getGeneroLista() {
		List<String> i18nLista = servicioIdiomas.getTraduccion(servicioAlumno.listaGeneros());
//		return servicioAlumno.listaGeneros().toArray();
		return i18nLista;
	}
	
	@ModelAttribute("horarioLista")
	public List<String> getHorarioLista() {
//		return servicioAlumno.listaHorario().toArray();
		List<String> i18nLista = servicioIdiomas.getTraduccion(servicioAlumno.listaHorario());
		return i18nLista;
	}
	
	@ModelAttribute("paisLista")
	public Map<String,String> getPais() {		
		Map<String,String> i18nLista = servicioIdiomas.getTraduccion(servicioAlumno.devuelvePais());
		return i18nLista;
		
	}
	
	@ModelAttribute("moduloLista")
	public List<Modulo> modelLista(){
		return servicioModulo.listar("");
		
	}
	
	@ModelAttribute("listaFiltrar")
	public HashMap<String,String> getListaFiltros(){
		return servicioAlumno.listarCamposBusqueda();
	}
	
	@ModelAttribute("listaTipos")
	public Object[] getListaTiposDocAlumno() {
		return servicioAlumno.listaTiposDocAlumno().toArray();
	}
	
	@ModelAttribute("cicloListaAlumnos")
	public List<String> getCicloListaAlumnos(){
		return servicioAlumno.cicloListaAlumnos();
	}
	
	@ModelAttribute("dniListaAlumnos")
	public List<String> getDniListaAlumnos() {
		return servicioAlumno.dniListaAlumnos();
	}
	
	@ModelAttribute("horarioListaAlumnos")
	public List<String> getHorarioListaAlumnos() {
		return servicioAlumno.horarioListaAlumnos();
	}
	
	
	

	// *************************************************************************************************
	// ******************** Peticion POST de ADD-ALUMNO => A�adir un alumno  ***************************
	// *************************************************************************************************

	@RequestMapping(value = "add-alumno", method = RequestMethod.POST)
	public String addAlumno(ModelMap model, @Valid AlumnoEdit alumnoEdit, BindingResult validacion) {

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
			servicioAlumno.addAlumno(alumnoEdit);

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
		
		
		// *************************************************************************************************
		// ******************** Peticion GET de Docs-Alumno => Mostrar documentacion alumno  ***************
		// *************************************************************************************************
		
		 @RequestMapping(value = ("docs-alumno") ,method=RequestMethod.GET)
	      public String muestraDocumentacion(@RequestParam String dni ,ModelMap model) {
			 
	    	model.put("pagina", paginaAlumno);
	    	
	  		servicioPagina.setPagina(paginaAlumno);
	  		model.put("docAlumno", new DocAlumno(servicioAlumno.siguienteDoc(dni)));
	    	model.addAttribute("alumno", servicioAlumno.devuelveAlumno(dni));
	    	
	        return "doc-alumno";
	      }
		 
		// *************************************************************************************************
		// ******************** Peticion POST de Docs-Alumno => Add docs alumno ****************************
		// *************************************************************************************************
		 
		 @RequestMapping(value = ("add-docAlumno") ,method=RequestMethod.POST)
	      public String addDocumentacion(ModelMap model , @Valid DocAlumno docAlumno , BindingResult validacion) {
	    	  
			//Obtener extension del fichero
			 String extDoc = servicioFile.getExtensionMultipartFile(docAlumno.getFichero());
			 String nombreFichero = servicioFile.generaNombreDocumento(docAlumno.getDni(), docAlumno.getId().toString(), extDoc);
			 
			 model.put("pagina", paginaAlumno);
			 servicioPagina.setPagina(paginaAlumno);
			 
			 if (validacion.hasErrors()) {
				 model.addAttribute("alumno",servicioAlumno.devuelveAlumno(docAlumno.getDni()));
				 return "doc-alumno";
			 }
			 
			 String dni = (String) docAlumno.getDni();
			 Alumno alumno = servicioAlumno.devuelveAlumno(dni);
			 
	  		try {
	  			if (alumno == null)
	  				throw new Exception("Alumno desconocido");
	  			if (model.getAttribute("usuario")== null)
	  				throw new Exception("Para a�adir documentacion debe estar logeado");
	  			
	  		
				 
	  			
	  			servicioAlumno.addDocAlumno(alumno, docAlumno);
	  			Usuario usuarioActivo = (Usuario) model.getAttribute("usuario");
	  			servicioAlumno.updateAlumno(alumno, usuarioActivo.getNickname());
	  			
	  			model.addAttribute("docAlumno",new DocAlumno(servicioAlumno.siguienteDoc(dni)));
	  			
	  			//	Guardar documento del alumno.
	  			String mnsjErroresAlGuardar="";
	  			ArrayList<String> arrayErrores = servicioFile.guardaDocumentoAlumno(docAlumno.getFichero(), nombreFichero);
	  			
	  			if (arrayErrores.size() > 0) {
	  				for (String mnsjError : arrayErrores) {
	  					mnsjErroresAlGuardar += mnsjError+"<br>";
					}
	  				model.addAttribute("errores",mnsjErroresAlGuardar);
	  			}
	  			docAlumno.setTipo(extDoc);
	  			docAlumno.setContentTypeFichero(docAlumno.getFichero().getContentType());
	  			model.addAttribute("alumno",servicioAlumno.devuelveAlumno(docAlumno.getDni()));
	  			
	  			return "doc-alumno";
	  			
	  		} catch (Exception e) {
	  			
	  			model.addAttribute("alumno",servicioAlumno.devuelveAlumno(docAlumno.getDni()));
	  			model.addAttribute("errores",e.getMessage());
	  			
	  			return "doc-alumno";

	  		}
	      }
		 
		 
		  //Atiende a la documentacion 
		 
		 @RequestMapping(value = "/descargar-docAlumno/{dni}/{idDoc}" , method = RequestMethod.GET)
		 public @ResponseBody void descargarDocAlumno(HttpServletResponse response,
				 @PathVariable("dni") String dni,
				 @PathVariable("idDoc") Integer idDoc) throws IOException {
			 
			 try {
				 Optional<DocAlumno> docAlumno = servicioAlumno.encontrarDocAlumnoPorDni_IdDoc(dni, idDoc);
				 if (docAlumno.isPresent()) { // Si existe el documento de ese alumno con ese id
					 //Obtener fichero
					 String nombreFichero = dni+"_idDoc_"+idDoc+"."+servicioFile.getExtensionMultipartFile(docAlumno.get().getFichero());
					 FileSystemResource resource = servicioFile.getDocumentoAlumno(nombreFichero);
					 
					 if (!resource.exists())
						 throw new IOException("El documento con el DNI '"+dni+"' y el id '"+idDoc+"' no existe.");
					 
					 File fichero = resource.getFile();
					 //Montar respuesta para el navegador web
					 response.setContentType(docAlumno.get().getContentTypeFichero());
					 response.setHeader("Content-Disposition", "attachment; filename="+fichero.getName());
					 response.setHeader("Content-Length",String.valueOf(fichero.length()));
					 InputStream in = new FileInputStream(fichero);
					 FileCopyUtils.copy(in, response.getOutputStream());
					 
				} else {
					throw new IOException("El documento con el DNI '"+dni+"' y el id '"+idDoc+"' no existe.");
				}
			 } catch (Exception e) {
				 //Ante cualquier error devolver error 404 recurso no encontrado.
				 throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			 }
			 
		 }
		 
}