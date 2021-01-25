package org.alumno.kalo.kalo_primera_app_spring_mvc.mvc;

import java.util.ArrayList;

import javax.validation.Valid;

import org.alumno.kalo.kalo_primera_app_spring_mvc.model.ImagenUsuario;
import org.alumno.kalo.kalo_primera_app_spring_mvc.model.Usuario;
import org.alumno.kalo.kalo_primera_app_spring_mvc.srv.FileService;
import org.alumno.kalo.kalo_primera_app_spring_mvc.srv.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.server.ResponseStatusException;

@Controller
@SessionAttributes("usuario")
public class UsuarioController {
	
	@Autowired
	FileService fileService;
	
	@Autowired
	LoginService servicioLogin;
	
	@RequestMapping(value= "/imagenUsuario/{nickName}", method= RequestMethod.GET)
	public ResponseEntity<FileSystemResource> getFile(@PathVariable("nickName") String nickName) {
		try {
			
		/** Implementar lógica en esta sección (y otras...) para rellenar “nombreFicheroConImagen”:
		
		Tener en cuenta que en caso de no estar logeado {loginNickName} será
		“Desconocido”, por lo que la imagen a mostrar será imagenUsuario/Desconocido.
		Si estamos logeados deberemos de consultar la imagen del usuario que por
		defecto (hasta que el usuario la cambie en un próximo paso)
		deberá ser “Desconocido.jpg”. */
			
			String nombreFicheroConImagen;
			
			if (nickName.equals("Desconocido")) {
				nombreFicheroConImagen = "Desconocido.jpg";
			} else {
				Usuario usuario = servicioLogin.encontrarUsuarioPorNickname(nickName);
				
				nombreFicheroConImagen = usuario.getNombreFicheroConImagen();
			}
			
		//El servicio nos devolverá la imagen y nos abstrae de saber donde esta guardada realmente
		FileSystemResource resource = fileService.getImagenUsuario(nombreFicheroConImagen);
		if (!resource.exists()) {
			throw new Exception("La imagen no existe");
		}
		ResponseEntity<FileSystemResource> responseEntity = new ResponseEntity<>(resource , HttpStatus.OK);
		return responseEntity;
		
		} catch (Exception e) {
			// Ante cualquier error devolver error 404 recurso no encontrado
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@RequestMapping(value="/update-imagenUsuario" , method= RequestMethod.GET)
	public String updateImagenUsuario(ModelMap model , @RequestParam String nickName) {
		
		
		model.addAttribute("imagenUsuario" , new ImagenUsuario(nickName));
		
		return "update-ImagenUsuario";
		
	}
	
	@RequestMapping(value="/guardar-imagen-usuario" , method= RequestMethod.POST)
	public String guardarImagenUsuario(ModelMap model ,
			@Valid ImagenUsuario imagenUsuario, BindingResult validacion) throws Exception {
		
		 if (validacion.hasErrors()) {
	            return "update-ImagenUsuario";
	        }

	        ArrayList <String> listaErroresAlGuardar = fileService.guardaImagenUsuario(imagenUsuario.getImagen(), imagenUsuario.getNickname()); //Lista de errores
	        if (listaErroresAlGuardar.size() == 0) { //No hay errores
	            //Al usuari cambiarli el ficher per el que estic ficant y guardar el ts i user
	            //Un get pa traure el user
	            Usuario usuario = servicioLogin.encontrarUsuarioPorNickname(imagenUsuario.getNickname());
	            //Un set pa modificar el camp de la imatge
	            usuario.setNombreFicheroConImagen(fileService.getNombreImagenUsuario(imagenUsuario.getImagen(), imagenUsuario.getNickname()));
	            //Modificar usuario que li pase ixe usuari
	            servicioLogin.modificaUsuario(usuario, imagenUsuario.getNickname());
	            return "update-ImagenUsuario";

	        } else { //Hay errores
	            model.put("errores", "Lista errores: " + listaErroresAlGuardar);
	            return "update-ImagenUsuario";
	        }
				
	}

}
