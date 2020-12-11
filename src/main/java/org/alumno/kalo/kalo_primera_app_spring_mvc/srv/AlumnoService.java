package org.alumno.kalo.kalo_primera_app_spring_mvc.srv;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.alumno.kalo.kalo_primera_app_spring_mvc.excepciones.AlumnoDuplicadoException;
import org.alumno.kalo.kalo_primera_app_spring_mvc.model.Alumno;
import org.alumno.kalo.kalo_primera_app_spring_mvc.model.LogError;
import org.alumno.kalo.kalo_primera_app_spring_mvc.model.Ts;
import org.alumno.kalo.kalo_primera_app_spring_mvc.model.order.ComparadorAlumnoCicloNombre;
import org.alumno.kalo.kalo_primera_app_spring_mvc.model.order.ComparadorAlumnoCursoNombre;
import org.alumno.kalo.kalo_primera_app_spring_mvc.model.order.ComparadorAlumnoDni;
import org.alumno.kalo.kalo_primera_app_spring_mvc.model.order.ComparadorAlumnoEdadNombre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlumnoService {
	// Creamos la lista de alumnos.
	private static List<Alumno> alumnos = new ArrayList<Alumno>();
	
	private static List<String> interesadoEnLista = new ArrayList<String>();
	
	private static List<String> generoLista = new ArrayList<String>();
	
	private static List<String> horarioLista = new ArrayList<String>();

	@Autowired
	LogErrorService servicioLogError;


	// Añadimos alumnos a la lista.
	static {
		
//		String dni, int edad, String ciclo, int curso, String nombre || boolean erasmus,String[] interesadoEn,String lenguajeFavorito
		String[] superInteresao = {"Backend","Frontend"};
		alumnos.add(new Alumno("Y0006447K", 20, "DAW", 2, "Jose",true,superInteresao,"Python","Mañana"));
		alumnos.add(new Alumno("87453598J", 25, "ASIX", 1, "Pedro",true,superInteresao,"Python","Mañana"));
		alumnos.add(new Alumno("20931113D", 17, "ESO", 4, "Juan",true,superInteresao,"Python","Mañana"));
		interesadoEnLista.add("Backend");
		interesadoEnLista.add("Frontend");
		generoLista.add("Masculino");
		generoLista.add("Femenino");
		horarioLista.add("Mañana");
		horarioLista.add("Tarde");
		
	}

	// Lista de alumnos
	public List<Alumno> listaAlumnos() {
		return alumnos;
	}

	
	// Lista Alumnos con Parametro
	public List<Alumno> listaAlumnos(String criterioOrden) {
		switch (criterioOrden) {
		case "Dni":
			Collections.sort(alumnos, new ComparadorAlumnoDni());
			break;

		case "Nombre":
			Collections.sort(alumnos);
			break;

		case "Edad":
			Collections.sort(alumnos, new ComparadorAlumnoEdadNombre());
			break;

		case "Ciclo":
			Collections.sort(alumnos, new ComparadorAlumnoCicloNombre());
			break;

		case "Curso":
			Collections.sort(alumnos, new ComparadorAlumnoCursoNombre());
			break;

		default:
			break;
		}

		return alumnos;
	}

	// Funcion añadir Alumno a la lista.
	public void addAlumno(Alumno alumno) throws AlumnoDuplicadoException {

		if (existeAlumno(alumno)) {
			servicioLogError.addLogError(new LogError(servicioLogError.asignarId(),"Insercion duplicada","Insercion duplicada del alumno '"+alumno.getDni()+"'"));
			throw new AlumnoDuplicadoException(devuelveAlumno(alumno.getDni()), alumno);
			
		} else {
			alumnos.add(alumno);
		}

	}

	// Funcion eliminar Alumno de la lista.
	public void delAlumno(Alumno alumno) {

		alumnos.remove(alumno);
	}

	// Funcion comprobar si existe alumno.
	public boolean existeAlumno(Alumno alumno) {

		for (Alumno alumnoActivo : alumnos) {

			if (alumnoActivo.getDni().equals(alumno.getDni())) {
				return true;
			}
		}
		return false;

	}

	// Funcion para encontrar y devolver un Alumno.
	public Alumno devuelveAlumno(String dni) {

		for (Alumno alumno : alumnos) {

			if (alumno.getDni().equals(dni))
				return alumno;

		}

		return null;

	}
	
	// Funcion para modificar el Alumno.
	public void updateAlumno(Alumno alumnoModificado,String usuarioModificacion) throws Exception {
		
		//La opcion mas facil es eliminar el alumno anterior y crear uno nuevo con los datos que desea modificar.
			Alumno alumnoActual = devuelveAlumno(alumnoModificado.getDni());
			
			if (!alumnoActual.sePuedeModificarUtilizando(alumnoModificado))
				throw new Exception(alumnoActual.mensajeNoSePuedeModificar());
			
			delAlumno(alumnoActual);
			alumnoModificado.setUser(usuarioModificacion);
			alumnoModificado.setTs(new Date());
			addAlumno(alumnoModificado);
			
			// Revisar esto de arriba
			
		
	}
	
	// Funcion devolver lista de interesadosEn
	public List<String> listaInteresadoEn(){
		return interesadoEnLista;
	};
	
	public List<String> listaGeneros(){
		return generoLista;
	}
	
	public List<String> listaHorario() {
		return horarioLista;
	}

}