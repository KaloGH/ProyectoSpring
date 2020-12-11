package org.alumno.kalo.kalo_primera_app_spring_mvc.mvc;

import org.alumno.kalo.kalo_primera_app_spring_mvc.excepciones.ModuloDuplicadoException;
import org.alumno.kalo.kalo_primera_app_spring_mvc.model.LogError;
import org.alumno.kalo.kalo_primera_app_spring_mvc.model.Modulo;
import org.alumno.kalo.kalo_primera_app_spring_mvc.model.Pagina;
import org.alumno.kalo.kalo_primera_app_spring_mvc.model.Usuario;
import org.alumno.kalo.kalo_primera_app_spring_mvc.srv.LogErrorService;
import org.alumno.kalo.kalo_primera_app_spring_mvc.srv.ModuloService;
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
public class ModuloController {
	@Autowired
	ModuloService moduloService;
	@Autowired
	PaginaService paginaService;
	@Autowired
	LogErrorService servicioLogError;

	@RequestMapping(value = "list-modulo", method = RequestMethod.GET)
	public String listarAlumno(@RequestParam(required = false) String criterio, ModelMap model) {
		
		Usuario user = (Usuario) model.getAttribute("usuario");
		if (user.getNickname() == "") {
			servicioLogError.addLogError(new LogError(servicioLogError.asignarId(),"Acceso Denegado","Usuario anónimo ha intentado acceder a la web"));
			return "redirect:login";
		}
		
		model.put("modulos", moduloService.listar(criterio == null ? "" : criterio));
		paginaService.setPagina(new Pagina("Lista de módulos", "list-modulo"));
		model.put("pagina", paginaService.getPagina());
		return "list-modulo";
	}

	@RequestMapping(value = "del-modulo", method = RequestMethod.GET)
	public String elimnarModulo(@RequestParam String id, ModelMap model) {
		model.addAttribute("modulo",moduloService.getModulo(Integer.parseInt(id)));
		paginaService.setPagina(new Pagina("Lista de módulos", "list-modulo"));
		model.put("pagina", paginaService.getPagina());
		return "del-modulo";
	}

	@RequestMapping(value = "del-modulo", method = RequestMethod.POST)
	public String elimnarModuloPost(Modulo modulo, ModelMap model) {
		paginaService.setPagina(new Pagina("Lista de módulos", "list-modulo"));
		model.put("pagina", paginaService.getPagina());
		moduloService.deleteModulo(modulo.getId());
		model.clear();
		return "redirect:list-modulo";
	}

	@RequestMapping(value = "add-modulo", method = RequestMethod.GET)
	public String anadirModulo(ModelMap model) {
		model.addAttribute("modulo", new Modulo(moduloService.obtenerId(), "Nuevo módulo", 12, "Abv"));
		paginaService.setPagina(new Pagina("Lista de módulos", "list-modulo"));
		model.put("pagina", paginaService.getPagina());
		return "add-modulo";
	}

	@RequestMapping(value = "add-modulo", method = RequestMethod.POST)
	public String anadirModuloPost(Modulo modulo, ModelMap model) {
		paginaService.setPagina(new Pagina("Lista de módulos", "list-modulo"));
		model.put("pagina", paginaService.getPagina());
		try {
			moduloService.add(modulo);
			model.clear();
			return "redirect:list-modulo";
		} catch (ModuloDuplicadoException e) {
			model.addAttribute("errores", e.toString());
		}
		return "add-modulo";

	}

}
