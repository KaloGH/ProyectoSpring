package org.alumno.kalo.kalo_primera_app_spring_mvc.validaciones;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.alumno.kalo.kalo_primera_app_spring_mvc.srv.I18nService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

public class ValidadorImagenes implements ConstraintValidator<ImagenValida , MultipartFile> {
	public static final List<String> tiposDeImagenes = Arrays.asList("image/png","image/jpg","image/jpeg","image/gif");
	public static final long MAX_BYTES = 52488; //512kb de tama�o maximo
	
	@Autowired
	I18nService servicioIdioma;
	
	public static ArrayList<String> mensajesErrorImagen(MultipartFile fichero) {
		ArrayList<String> errores = new ArrayList<String>();
		
		//Fichero no vacio
		if (fichero.isEmpty())
			errores.add("La imagen no puede estar vacia");
		
		//Validar tipo de fichero
		String contentType = fichero.getContentType();
		if (!tipoDeImagenValido(contentType))
			errores.add("Solo se permiten imagenes PNG , JPG o GIF");
		
		//Comprobar tama�o maximo
		if (fichero.getSize()>MAX_BYTES)
			errores.add("Tama�o maximo de la imagen excedido ("+MAX_BYTES+" bytes)");
		
		return errores;
	}
	
	//Comprueba si el tipo de imagen es uno de los predefinidos en tiposDeImagenes
	private static boolean tipoDeImagenValido(String contentType) {
		
		return tiposDeImagenes.contains(contentType);
//		for (String tipo : tiposDeImagenes) {
//			if (contentType == tipo)
//				return true;
//		}
//		return false;
	}
	
	/** METODOS PUBLICOS PARA UTILIZAR EN OTRAS CLASES POR EJEMPLO FileService.java */
	public static boolean imagenValida(MultipartFile fichero) {
		ArrayList<String> listaErrores = mensajesErrorImagen(fichero);
		// Si esta vacia no hay errores
		return listaErrores.isEmpty();
	}
	
	//Comprueba si el tipo de fichero esta en la lista "tiposDeImagenes" y devuelve solo la extension. Si no esta devuelve ""
	public static String getExtension(MultipartFile fichero) {
		
		if (tipoDeImagenValido(fichero.getContentType())) {
			
			return fichero.getContentType().substring(6);
		}
		
		return "";
		
	}

	
	@Override
	public void initialize(ImagenValida constraintAnnotation) {
		
	}
	
	
	@Override
	public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext context) {
		//Por defecto resultado de la comprobacion valido hasta que encontremos un error
		boolean result = true;
		
		//comprobar lista de errores
		ArrayList<String> listaErrores = 
				(ArrayList<String>) servicioIdioma.getTraduccion(mensajesErrorImagen(multipartFile));
		// Si hay errores a�adirlos al contexto
		
		
		if(!listaErrores.isEmpty()) {
			context.disableDefaultConstraintViolation();
			
			// iteramos por la lista de errores para a�adirlos al contexto
			
			for (String textoError : listaErrores) {
				context.buildConstraintViolationWithTemplate(
						textoError)
						.addConstraintViolation();
			}
			//Comprobacion incorrecta (resultado no valido)
			result = false;
			
		}
		
		//Devolvemos resultado de la comprobacion
		return result;
		
	}
	
	
}
