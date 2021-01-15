package org.alumno.kalo.kalo_primera_app_spring_mvc.srv;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.alumno.kalo.kalo_primera_app_spring_mvc.excepciones.AlumnoDuplicadoException;
import org.alumno.kalo.kalo_primera_app_spring_mvc.model.Alumno;
import org.alumno.kalo.kalo_primera_app_spring_mvc.model.DocAlumno;
import org.alumno.kalo.kalo_primera_app_spring_mvc.model.FiltroAlumno;
import org.alumno.kalo.kalo_primera_app_spring_mvc.model.LogError;
import org.alumno.kalo.kalo_primera_app_spring_mvc.model.Modulo;
import org.alumno.kalo.kalo_primera_app_spring_mvc.model.Ts;
import org.alumno.kalo.kalo_primera_app_spring_mvc.model.order.ComparadorAlumnoCicloNombre;
import org.alumno.kalo.kalo_primera_app_spring_mvc.model.order.ComparadorAlumnoCursoNombre;
import org.alumno.kalo.kalo_primera_app_spring_mvc.model.order.ComparadorAlumnoDni;
import org.alumno.kalo.kalo_primera_app_spring_mvc.model.order.ComparadorAlumnoEdadNombre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocAlumnoService {
	// Creamos la lista de alumnos.

	private static List<DocAlumno> listaDocumentacion = new ArrayList<DocAlumno>(); 
	
	private static List<String> tipoLista = new ArrayList<String>();


	@Autowired
	LogErrorService servicioLogError;

	@Autowired
	ModuloService servicioModulo;

	// Añadimos alumnos a la lista.
	static {
		listaDocumentacion.add(new DocAlumno("Y0006447K",0,"certificado","Prueba de intento con 10 caracteres como minimo"));
		
		tipoLista.add("Certificado");
		tipoLista.add("Justificante");
		tipoLista.add("Solicitud");
		
	}

	// Lista de documento
	public List<DocAlumno> listaDocumentacion() {
		return listaDocumentacion;
	}

	
	
	
	// Funcion añadir Documentacion a la lista.
	public void addDoc(DocAlumno documento) {
		
		listaDocumentacion.add(documento);
		
	}

	// Funcion eliminar documento de la lista.
	public void delDoc(DocAlumno documento) {

		listaDocumentacion.remove(documento);
	}

	// Funcion comprobar si existe documento.
	public boolean existeDocumento(DocAlumno documento) {

		for (DocAlumno documentoActivo : listaDocumentacion) {

			if (documentoActivo.getId().equals(documento.getId())) {
				return true;
			}
		}
		return false;

	}

	// Funcion para encontrar y devolver un documento.
	public DocAlumno devuelveDocumento(Integer id) {
		
		Optional<DocAlumno> documento = listaDocumentacion.stream().filter(doc -> doc.getId() == id).findFirst();

		return documento.isPresent() ? documento.get() : null;

	}
	

	
	public List<String> listaTipos(){
		return tipoLista;
	}
	

}