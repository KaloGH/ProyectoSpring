package org.alumno.kalo.kalo_primera_app_spring_mvc.srv;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.alumno.kalo.kalo_primera_app_spring_mvc.validaciones.ValidadorDocumentoAlumno;
import org.alumno.kalo.kalo_primera_app_spring_mvc.validaciones.ValidadorImagenes;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
	//En los FICHEROS ESTATICOS, los datos se almacenan en una carpeta dentro del fichero
	// comprimido .WAR que se ha cargado en TOMCAT.
	
	//En los FICHEROS DINAMICOS los ficheros se almacenan fuera del fichero comprimido .WAR,
	// esto significa que se guardara en una CARPETA DEL SERVIDOR WEB
	
	/** CONSTANTES CON DATOS PARA SABER LA CARPETA PRINCIPAL DE LA APP DONDE GUARDAR/LEER LOS FICHEROS */
	//Podemos almacenar los ficheros dinamicos donde queramos, pero si ponemos una
	// carpeta fija, dicha ruta tiene codificacion Windows o Linux, por lo que nuestra app
	// solo podriamos ejecutarla en ese sistema operativo
	// La carpeta $HOME del usuario se puede extraer gracias a la clase System
	// independientemente de que estemos en Windows , Linux o Mac.
	
	private static final String USER_HOME_TOMCAT = System.getProperty("user.home");
	
	//En linux el separador de carpetas es "/", en Windows "\", para hacer nuestra APP
	// independientemente del sistema donde se cargue utilizamos el File.separator.
	private static final String SEPARATOR = File.separator;
	
	//Carpeta donde se alacenaran todos los ficheros dinamicos de la app
	private static final String CARPETA_FICHEROS_DINAMICOS_WEBAPP = USER_HOME_TOMCAT+SEPARATOR+"AlumnosWebApp_DynamicFiles"+SEPARATOR;
	
	/** CONSTANTES CON EL NOMBRE DE LAS CARPETAS DONDE GUARDAR LOS FICHEROS */
	
	//Carpeta con las imagenes de los usuarios
	private static final String CARPETA_IMAGENES_USUARIOS = CARPETA_FICHEROS_DINAMICOS_WEBAPP+"ImagenesUsuarios";
	
//	TODO: Crear carpeta para los documentos.
	private static final String CARPETA_DOCUMENTOS_USUARIOS = CARPETA_FICHEROS_DINAMICOS_WEBAPP+"DocumentacionUsuarios";
	
	//Aqui iran el resto de las carpetas.

	
	
	public FileSystemResource getImagenUsuario(String fichero) {
		return new FileSystemResource(new File(CARPETA_IMAGENES_USUARIOS, fichero));
	}
	
	/** METODO PRINCIPAL AL QUE LLAMAN TODOS LOS METODOS QUE GUARDEN FICHEROS */
	// Guardar el 'fichero' del formulario en la 'ruta'
	// Si falla devuelve un String con el error , en caso contrario nulo
	// 'ruta' ya incluye el nombre final del fichero
	// Ejemplo:
	// 			ruta='/home/joseramon/AlumnosWebApp_DynamicFiles/ImagenesUsuarios/imagenJoseRamon.jpg'
	
	private String guardarFichero(String ruta,MultipartFile fichero) {
		try {
			//Obtener  datos del fichero
			byte[] fileBytes = fichero.getBytes();
			//Obtener la ruta
			Path path = Paths.get(ruta);
			//Guardar el fichero
			// El fichero no tiene porque existir en la ruta , pero
			// si la carpeta destino no existe se producira una excepcion
			Files.write(path, fileBytes);
		} catch (NoSuchFileException e) {
			return "No existe la carpeta para poder guardar '"
					+ e.getMessage() + "'";
		} catch (IOException e) {
			//Si hay error devolver mensaje de error
			return e.getMessage();
		}
		
		//Si no hay error devolver null
		return null;
	}
	
	/** Guardar imagenes de los usuarios*/
	//Guardar fichero en la carpeta de las imagenes de usuario con un nombre determinado
	//Si falla devuelve una lista con los errores detectados
	public ArrayList<String> guardaImagenUsuario(MultipartFile fichero, String nickName){
		String nombreFichero = getNombreImagenUsuario(fichero,nickName);
		
		//Comprobacion de errores
		if (!ValidadorImagenes.imagenValida(fichero)) {
			return ValidadorImagenes.mensajesErrorImagen(fichero);
		}
		
		// Guardar fichero
		String errorAlGuardar = guardarFichero(CARPETA_IMAGENES_USUARIOS + SEPARATOR+nombreFichero, fichero);
		if (errorAlGuardar == null ) {
			return new ArrayList<String>();
			
		} else {
			
			return new ArrayList<String>(List.of(errorAlGuardar));
		}
	}
	
	
	//Consulta el tipo de extension del fichero y devuelve un String "nickname.ext"
	public String getNombreImagenUsuario(MultipartFile fichero,String nickName) {
		String extension = ValidadorImagenes.getExtension(fichero);
		String nombreFichero = nickName+"."+extension;
		return nombreFichero;
	}
	
	public String getExtensionMultipartFile(MultipartFile file) {
		
		return file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.')+1);
		
	}
	// TODO: Borrar esta funcion porque se realiza en el alumnoController.
	public String generaNombreDocumento(String DNI , String ID,String EXT) {
		return DNI+"_idDoc_"+ID+"."+EXT;
	}
	
	public ArrayList<String> guardaDocumentoAlumno(MultipartFile fichero, String nombreFichero){ //TODO: Guardar documento en la carpeta correspondiente
		
		//Comprobacion de errores
				if (!ValidadorDocumentoAlumno.documentoValido(fichero)) {
					return ValidadorDocumentoAlumno.mensajesErrorDocumento(fichero);
				}
				
				// Guardar fichero
				String errorAlGuardar = guardarFichero(CARPETA_DOCUMENTOS_USUARIOS + SEPARATOR+nombreFichero, fichero);
				if (errorAlGuardar == null ) {
					return new ArrayList<String>();
					
				} else {
					
					return new ArrayList<String>(List.of(errorAlGuardar));
				}
	}
	
	/*
	 *	Devuelve el documento del Alumno. 
	 */
	
	
	public FileSystemResource getDocumentoAlumno(String fichero) {
		return new FileSystemResource(new File(CARPETA_DOCUMENTOS_USUARIOS, fichero));
	}
	
	
	
}
