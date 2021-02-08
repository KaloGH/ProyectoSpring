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
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.alumno.kalo.kalo_primera_app_spring_mvc.excepciones.AlumnoDuplicadoException;
import org.alumno.kalo.kalo_primera_app_spring_mvc.model.Alumno;
import org.alumno.kalo.kalo_primera_app_spring_mvc.model.DocAlumno;
import org.alumno.kalo.kalo_primera_app_spring_mvc.model.FiltroAlumno;
import org.alumno.kalo.kalo_primera_app_spring_mvc.model.FiltroAvanzadoAlumno;
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
public class AlumnoService {
	// Creamos la lista de alumnos.
	private static List<Alumno> alumnos = new ArrayList<Alumno>();
	
	private static List<String> interesadoEnLista = new ArrayList<String>();
	
	private static List<String> generoLista = new ArrayList<String>();
	
	private static List<String> horarioLista = new ArrayList<String>();
	
	private static Map<String,String> pais = new HashMap<String,String>();
	
	private static List<Modulo> moduloLista = new ArrayList<Modulo>();
	
	private static List<String> listaTiposDoc = new ArrayList<String>();
	

	@Autowired
	LogErrorService servicioLogError;

	@Autowired
	ModuloService servicioModulo;

	// Añadimos alumnos a la lista.
	static {
		
		
//		String dni, int edad, String ciclo, int curso, String nombre || boolean erasmus,String[] interesadoEn,String lenguajeFavorito
		String[] superInteresao = {"Backend","Frontend"};
		int[] modulaso = {0,1};
		alumnos.add(new Alumno("Y0006447K", 20, "DAW", 2, "Jose",true,superInteresao,"Python","Mañana","FR",modulaso,"Le gusta nadar los fines de semana en charcos."));
		alumnos.add(new Alumno("87453598J", 25, "ASIX", 1, "Pedro",true,superInteresao,"Python","Mañana","FR",modulaso,"Le gusta romper retrovisores los domingos de misa."));
		alumnos.add(new Alumno("20931113D", 17, "ESO", 4, "Juan",true,superInteresao,"Python","Mañana","FR",modulaso,"Roba centimos de máquinas expendedoras 24 horas."));
		interesadoEnLista.add("Backend");
		interesadoEnLista.add("Frontend");
		generoLista.add("Masculino");
		generoLista.add("Femenino");
		horarioLista.add("Mañana");
		horarioLista.add("Tarde");
		pais.put("ES","España");
		pais.put("UK","Inglaterra");
		pais.put("FR","Francia");
		listaTiposDoc.add("Certificado");
		listaTiposDoc.add("Justificante");
		listaTiposDoc.add("Solicitud");
		
	}

	// Lista de alumnos
	public List<Alumno> listaAlumnos() {
		return alumnos;
	}

	
	// Lista Alumnos con Parametro
	public List<Alumno> listaAlumnos(String criterioOrden) {
		List<Alumno> alumnosOrdenados = new ArrayList<Alumno>();
		switch (criterioOrden) {
		case "Dni":
			alumnosOrdenados = alumnos.stream().sorted(new ComparadorAlumnoDni()).collect(Collectors.toList());
			break;

		case "Nombre":
			alumnosOrdenados = alumnos.stream().sorted().collect(Collectors.toList());
			break;

		case "Edad":
			alumnosOrdenados = alumnos.stream().sorted(new ComparadorAlumnoEdadNombre()).collect(Collectors.toList());
			break;

		case "Ciclo":
			alumnosOrdenados = alumnos.stream().sorted(new ComparadorAlumnoCicloNombre()).collect(Collectors.toList());
			break;

		case "Curso":
			alumnosOrdenados = alumnos.stream().sorted(new ComparadorAlumnoCursoNombre()).collect(Collectors.toList());
			break;

		default:
			break;
		}

		return alumnosOrdenados;
	}
	
	//Filtra alumnos.
	public List<Alumno> filtraAlumnos(FiltroAlumno filtrarAlumno){
		
		switch (filtrarAlumno.getCampo()) {
		// DNI
		case "dni":
			return filtrarAlumno.PorDni(filtrarAlumno.getValor(), alumnos);		
		// EDAD			
		case "edad":
			return filtrarAlumno.PorEdad(Integer.parseInt(filtrarAlumno.getValor()), alumnos);
		// CICLO
		case "ciclo":
			return filtrarAlumno.PorCiclo(filtrarAlumno.getValor(), alumnos);
		// CURSO
		case "curso":
			return filtrarAlumno.PorCurso(Integer.parseInt(filtrarAlumno.getValor()), alumnos);
		// HORARIO
		case "horario":
			return filtrarAlumno.PorHorario(filtrarAlumno.getValor(), alumnos);
		// PAIS
		case "pais":
			return filtrarAlumno.PorPais(filtrarAlumno.getValor(), alumnos);

		default:
			return alumnos;
		}
	}
	
	private static HashMap<String,String> listaCampos = new HashMap<String,String>();
	public HashMap<String,String> listarCamposBusqueda(){
		listaCampos.put("dni", "DNI");
		listaCampos.put("edad", "Edad");
		listaCampos.put("pais", "Pais");
		listaCampos.put("horario", "Horario");
		listaCampos.put("curso", "Curso");
		listaCampos.put("modulo", "Modulo");
		return listaCampos;
		
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
		
		Optional<Alumno> alumno = alumnos.stream().filter(alumn -> alumn.getDni().equals(dni)).findFirst();

		return alumno.isPresent() ? alumno.get() : null;

	}
	
	// Funcion para modificar el Alumno.
	public void updateAlumno(Alumno alumnoModificado,String usuarioModificacion) throws Exception {
		String errores="";
		
		if (alumnoModificado == null) {
			errores = "No se ha podido actualizar el alumno porque no han llegado los datos modificados.";
		} else {
			Alumno alumnoActual = devuelveAlumno(alumnoModificado.getDni());
			
			if (alumnoActual.sePuedeModificarUtilizando(alumnoModificado)) {
				alumnos.remove(alumnoActual);
				//Debemos asegurarnos de que no se borra la documentacion
				alumnoModificado.setDocAlumno(alumnoActual.getDocAlumno());
				//Actualizamos usuario y fecha de modificacion
				alumnoModificado.setUser(usuarioModificacion);
				alumnoModificado.setTs(new Date());
				alumnos.add(alumnoModificado);
			} else {
				errores = alumnoActual.mensajeNoSePuedeModificar();
			}
		}
		
		if (errores.length()>0)
			throw new Exception(errores);
		
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
	
	public void addDocAlumno(Alumno alumno, DocAlumno docAlumno) {
		alumno.getDocAlumno().add(docAlumno);
	}
	
	public int siguienteDoc(String dni) {
		
		int idFinal=0;
		
		System.out.println(devuelveAlumno(dni).getDocAlumno());
		
		if (devuelveAlumno(dni).getDocAlumno().size() == 0)
			return ++idFinal;
			
		for (DocAlumno docAlumno : devuelveAlumno(dni).getDocAlumno()) {
			idFinal = docAlumno.getId();
		}
		
		
		return ++idFinal;
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
	
	public Map<String,String> devuelvePais(){
		return pais;
	}
	
	public List<Modulo> listaModulos() {
		return servicioModulo.listar("");
	}
	
	public List<String> listaTiposDocAlumno() {
		return listaTiposDoc;
	}
	
	// Para el Filtro avanzado //
	
	public List<String> cicloListaAlumnos(){
		Set<String> listaSinRepeticiones = alumnos.stream().filter(a->a.getCiclo()!=null).map(a->a.getCiclo()).collect(Collectors.toSet());
		return listaSinRepeticiones.stream().sorted().collect(Collectors.toList());
	}
	
	public List<String> dniListaAlumnos() {
		Set<String> listaSinRepeticiones = alumnos.stream().filter(a->a.getDni()!=null).map(a->a.getDni()).collect(Collectors.toSet());
		return listaSinRepeticiones.stream().sorted().collect(Collectors.toList());
	}
	
	public List<String> horarioListaAlumnos() {
		Set<String> listaSinRepeticiones = alumnos.stream().filter(a->a.getHorario()!=null).map(a->a.getHorario()).collect(Collectors.toSet());
		return listaSinRepeticiones.stream().sorted().collect(Collectors.toList());
	}
	
	public List<Alumno> filtroAvanzadoAlumnos(FiltroAvanzadoAlumno filtroAvanzadoAlumno) {
		List<Alumno> lista = alumnos; // Lista inicialmente contiene todos los alumnos
		try { // Ahora si hace falta vamos filtrando y quedandonos con los alumnos resultantes del filtrado
			//Desconocido si hacemos una depuracion se almacena como "-"
			
			if(!"-".equals(filtroAvanzadoAlumno.getDni())) { // Si que debemos filtrar por dni
				lista = lista.stream().filter(a->a.getDni()!=null && a.getDni().equals(filtroAvanzadoAlumno.getDni())).collect(Collectors.toList());
			}
			
			if(!"-".equals(filtroAvanzadoAlumno.getCiclo())) {
				lista = lista.stream().filter(a->a.getCiclo()!=null && a.getCiclo().equals(filtroAvanzadoAlumno.getCiclo())).collect(Collectors.toList());
			}
		
			if(!"-".equals(filtroAvanzadoAlumno.getHorario())) {
				lista = lista.stream().filter(a->a.getHorario()!=null && a.getHorario().equals(filtroAvanzadoAlumno.getHorario())).collect(Collectors.toList());
			}
			
		} catch (Exception e) { //Si hay error la lista debe estar vacia.
			lista = new ArrayList<Alumno>();
		}
		
		if(lista == null) //Si hay cualquier problema y la lista esta null devolvemos lista vacia
			return new ArrayList<Alumno>();
		return lista;//Devolvemos la lista despues de realizar los filtros
		
	}
	
	/*
	 * Metodo para encontrar un documento de un alumno con el DNI y el ID del documento.
	 */
		public Optional<DocAlumno> encontrarDocAlumnoPorDni_IdDoc(String dni, Integer id) {
			ArrayList<DocAlumno> docAlumno = devuelveAlumno(dni).getDocAlumno();
			
			return  docAlumno.stream()
					.filter(documentacion -> id == documentacion.getId())
					.findFirst();
			
		}
	
	
	

}