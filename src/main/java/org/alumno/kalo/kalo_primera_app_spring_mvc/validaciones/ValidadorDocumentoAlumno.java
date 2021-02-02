package org.alumno.kalo.kalo_primera_app_spring_mvc.validaciones;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;
/*
 * tiposDeDocumentos=Arrays.asList("image/png", "image/jpg", "image/jpeg",
"image/gif","text/plain","application/pdf","application/msword","application/vnd.ms-excel",
"application/vnd.openxmlformats-officedocument.wordprocessingml.document","application/excel",

"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
"application/vnd.oasis.opendocument.text","application/vnd.oasis.opendocument.spreadsheet",

"application/x-rar-compressed","application/zip",
"application/x-zip-compressed","multipart/x-zip");
 * 
 * */

public class ValidadorDocumentoAlumno implements ConstraintValidator<DocumentoAlumnoValido , MultipartFile> {
	public static final List<String> tiposDeDocumentos = Arrays.asList("image/png", 
			"image/jpg", 
			"image/jpeg",
			"image/gif",
			"text/plain",
			"application/pdf",
			"application/msword",
			"application/vnd.ms-excel",
			"application/vnd.openxmlformats-officedocument.wordprocessingml.document",
			"application/excel",
			"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
			"application/vnd.oasis.opendocument.text",
			"application/vnd.oasis.opendocument.spreadsheet",
			"application/x-rar-compressed",
			"application/zip",
			"application/x-zip-compressed",
			"multipart/x-zip");
	
	public static final long MAX_BYTES =  2097152; //2mb de tamaño maximo   //52488 //512kb de tama�o maximo
	
	public static ArrayList<String> mensajesErrorDocumento(MultipartFile fichero) {
		ArrayList<String> errores = new ArrayList<String>();
		
		//Fichero no vacio
		if (fichero.isEmpty())
			errores.add("El documento no puede estar vacio");
		
		//Validar tipo de fichero
		String contentType = fichero.getContentType();
		if (!tipoDeDocumentoValido(contentType))
			errores.add("Solo se permiten ficheros PNG,JPG,GIF,PLAIN,PDF,EXCEL,ZIP,RAR");
		
		//Comprobar tama�o maximo
		if (fichero.getSize()>MAX_BYTES)
			errores.add("Tamaño maximo del fichero excedido ("+MAX_BYTES+" bytes)");
		
		return errores;
	}
	
	//Comprueba si el tipo de imagen es uno de los predefinidos en tiposDeImagenes
	private static boolean tipoDeDocumentoValido(String contentType) {
		
		return tiposDeDocumentos.contains(contentType);
//		for (String tipo : tiposDeImagenes) {
//			if (contentType == tipo)
//				return true;
//		}
//		return false;
	}
	
	/** METODOS PUBLICOS PARA UTILIZAR EN OTRAS CLASES POR EJEMPLO FileService.java */
	public static boolean documentoValido(MultipartFile fichero) {
		ArrayList<String> listaErrores = mensajesErrorDocumento(fichero);
		// Si esta vacia no hay errores
		return listaErrores.isEmpty();
	}
	
	//Comprueba si el tipo de fichero esta en la lista "tiposDeImagenes" y devuelve solo la extension. Si no esta devuelve ""
	public static String getExtension(MultipartFile fichero) {
		
		if (tipoDeDocumentoValido(fichero.getContentType())) {
			
			return fichero.getContentType().substring(6);
		}
		
		return "";
		
	}

	
	@Override
	public void initialize(DocumentoAlumnoValido constraintAnnotation) {
		
	}
	
	
	@Override
	public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext context) {
		//Por defecto resultado de la comprobacion valido hasta que encontremos un error
		boolean result = true;
		
		//comprobar lista de errores
		ArrayList<String> listaErrores = mensajesErrorDocumento(multipartFile);
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
