package org.alumno.kalo.kalo_primera_app_spring_mvc.model;

import java.util.List;
import java.util.stream.Collectors;

public class FiltroAlumno {
	
	public FiltroAlumno() {
		super();
	}
	public FiltroAlumno(String campo, String valor) {
		super();
		this.campo = campo;
		this.valor = valor;
	}

	private String campo;
	private String valor;
	
	
	public String getCampo() {
		return campo;
	}
	public void setCampo(String campo) {
		this.campo = campo;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	public List<Alumno> PorDni(String dni, List<Alumno> alumnos){
		return alumnos.stream().filter(alumno -> alumno.getDni().equals(dni)).collect(Collectors.toList());
	}
	
	public List<Alumno> PorEdad(int edad, List<Alumno> alumnos){
		return alumnos.stream().filter(alumno -> alumno.getEdad() == edad).collect(Collectors.toList());
	}
	
	public List<Alumno> PorCiclo(String ciclo, List<Alumno> alumnos){
		return alumnos.stream().filter(alumno -> alumno.getCiclo().equals(ciclo)).collect(Collectors.toList());
	}
	
	public List<Alumno> PorHorario(String horario, List<Alumno> alumnos){
		return alumnos.stream().filter(alumno -> alumno.getHorario().equals(horario)).collect(Collectors.toList());
	}
	
	public List<Alumno> PorPais(String pais, List<Alumno> alumnos){
		return alumnos.stream().filter(alumno -> alumno.getPais().equals(pais)).collect(Collectors.toList());
	}
	
	public List<Alumno> PorCurso(int curso, List<Alumno> alumnos){
		return alumnos.stream().filter(alumno -> alumno.getCurso() == curso).collect(Collectors.toList());
	}
	
}
