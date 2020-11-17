package org.alumno.kalo.kalo_primera_app_spring_mvc.mvc;

import org.alumno.kalo.kalo_primera_app_spring_mvc.model.Pagina;
import org.alumno.kalo.kalo_primera_app_spring_mvc.srv.LoginService;
import org.alumno.kalo.kalo_primera_app_spring_mvc.srv.PaginaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("nombre")
public class LoginController {
	
	@Autowired
	LoginService servicioLogin;
	
	@Autowired
	PaginaService servicioPagina;
	
	Pagina paginaLogin = new Pagina("Login","login");
	
	//LoginService servicioLogin = new LoginService();
	
	@RequestMapping(value="login",method = RequestMethod.GET)
	public String mostrarLogin(ModelMap model, String errores) {
		
		model.put("errores", errores);
		model.put("pagina", paginaLogin);
		
		return "login";
	}
	
	@RequestMapping(value="/",method = RequestMethod.GET)
	public String urlInicial(ModelMap model) {
		
		model.put("pagina", paginaLogin);
		
		return "redirect:login";
	}
	
	@RequestMapping(value="login",method = RequestMethod.POST)
	public String procesaLogin(@RequestParam String nombre,
			String password ,String errores,ModelMap model) {

		model.put("pagina", paginaLogin);
		
		if (!servicioLogin.usuarioValido(nombre, password)) {
			//Usuario inválido, volver a intentar logearse
			model.put("errores", "Usuario '"+nombre+"' o contraseña incorrectos.");
			return "login";
		}
		
		model.put("nombre", nombre);
		model.put("password", password);
		return "bienvenida";
	}
}