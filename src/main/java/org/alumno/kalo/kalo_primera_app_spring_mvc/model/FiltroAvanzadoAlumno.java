package org.alumno.kalo.kalo_primera_app_spring_mvc.model;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class FiltroAvanzadoAlumno implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public FiltroAvanzadoAlumno() {
		super();
	}
	
	private String dni;
	private String ciclo;
	private String horario;


	public FiltroAvanzadoAlumno(String dni, String ciclo, String horario) {
		super();
		this.dni = dni;
		this.ciclo = ciclo;
		this.horario = horario;
	}
	
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getCiclo() {
		return ciclo;
	}
	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
}
