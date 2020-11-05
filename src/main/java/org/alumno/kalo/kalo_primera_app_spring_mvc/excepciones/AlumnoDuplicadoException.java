package org.alumno.kalo.kalo_primera_app_spring_mvc.excepciones;

import org.alumno.kalo.kalo_primera_app_spring_mvc.model.Alumno;

public class AlumnoDuplicadoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Alumno oldAlumno;
	private Alumno newAlumno;
	
	public AlumnoDuplicadoException(Alumno oldAlumno, Alumno newAlumno) {
		
		this.oldAlumno = oldAlumno;
		this.newAlumno = newAlumno;
	}
	
	@Override
	public String toString() {
		return "El DNI del alumno "+newAlumno.getNombre()+ 
				" ya esta registrado para el Alumno "+oldAlumno.getNombre();
	}
	
	
	
}