package org.alumno.kalo.kalo_primera_app_spring_mvc.excepciones;

import org.alumno.kalo.kalo_primera_app_spring_mvc.model.dto.AlumnoEdit;
import org.alumno.kalo.kalo_primera_app_spring_mvc.model.ram.Alumno;

public class AlumnoDuplicadoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Alumno oldAlumno;
	private AlumnoEdit newAlumnoEdit;
	
	public AlumnoDuplicadoException(Alumno oldAlumno, AlumnoEdit newAlumnoEdit) {
		
		this.oldAlumno = oldAlumno;
		this.newAlumnoEdit = newAlumnoEdit;
	}
	
	@Override
	public String toString() {
		return "El DNI del alumno "+newAlumnoEdit.getNombre()+ 
				" ya esta registrado para el Alumno "+oldAlumno.getNombre();
	}
	
	
	
}