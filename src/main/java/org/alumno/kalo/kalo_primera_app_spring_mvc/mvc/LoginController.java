package org.alumno.kalo.kalo_primera_app_spring_mvc.mvc;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.alumno.kalo.kalo_primera_app_spring_mvc.model.Alumno;
import org.alumno.kalo.kalo_primera_app_spring_mvc.model.LogError;
import org.alumno.kalo.kalo_primera_app_spring_mvc.model.Pagina;
import org.alumno.kalo.kalo_primera_app_spring_mvc.model.Usuario;
import org.alumno.kalo.kalo_primera_app_spring_mvc.srv.I18nService;
import org.alumno.kalo.kalo_primera_app_spring_mvc.srv.LogErrorService;
import org.alumno.kalo.kalo_primera_app_spring_mvc.srv.LoginService;
import org.alumno.kalo.kalo_primera_app_spring_mvc.srv.PaginaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"usuario","loginName","loginNickName"})
public class LoginController {
	
	@Autowired
	LoginService servicioLogin;
	
	@Autowired
	PaginaService servicioPagina;
	
	@Autowired
	LogErrorService servicioLogError;
	
	@Autowired
	I18nService servicioIdiomas;
	
	Pagina paginaLogin = new Pagina("Login","login");
	
	//LoginService servicioLogin = new LoginService();
	
	
	@RequestMapping(value={"/","login"},method = RequestMethod.GET)
	public String urlInicial(HttpServletRequest request ,HttpServletResponse response , Locale locale , ModelMap model) {
		
		//Traza i18n
		//Informacion idioma de la peticion del navegador
		System.out.println("Accept-Language: "
				+request.getHeader("Accept-Language"));
		//Informacion del localeResolver
		System.out.println(String
				.format("Peticion recibida. Languaje: %s, Pais: %s %n", 
						locale.getLanguage(), locale.getDisplayCountry()));
		
		servicioIdiomas.configuraIdiomaPeticion(request, response, locale);
		
		Usuario user =(Usuario) model.getAttribute("usuario");
		
		model.put("pagina", paginaLogin);
						
		// Si no existe el usuario crea uno nuevo - de lo contrario cada vez que el usuario apriete home se borrara el usuario ingresado anteriormente
		if (user == null) {
			
			user = new Usuario("","","");
			
			model.put("usuario", user);
			
			model.addAttribute("loginName","Desconocido");
			model.addAttribute("loginNickName","Desconocido");
		}
		if (user.getNickname() != ""){			
			return "bienvenida";
		} 
		return "login";
		
		
			
		
		
	}
	
	@RequestMapping(value="login",method = RequestMethod.POST)
	public String procesaLogin(@RequestParam(required = false) String errores,ModelMap model , @Valid Usuario usuario ,BindingResult validacion) {

		if (validacion.hasErrors()) {
			
			model.put("usuario", new Usuario("","",""));
			return"login";
		}
		
		model.put("pagina", paginaLogin);
		
		if (!servicioLogin.usuarioValido(usuario)) {
			//Usuario inválido, volver a intentar logearse
			String msgError = "Usuario '"+usuario.getNickname()+"' o contraseña incorrectos.";
			model.put("errores", msgError);
			servicioLogError.addLogError(new LogError(servicioLogError.asignarId(),"Login Incorrecto","Login incorrecto de '"+usuario.getNickname()+"'"));
			return "login";
		} else {
			
			model.put("loginNickName",usuario.getNickname());
			model.put("loginName",usuario.getNombre());
			model.put("usuario", usuario);
			return "bienvenida";
		}
		
	}
}