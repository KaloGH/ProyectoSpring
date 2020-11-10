package org.alumno.kalo.kalo_primera_app_spring_mvc.model;

import java.io.Serializable;

public class Alumno  implements Serializable,Comparable<Alumno>{  //<= USAR EN CASO DE GASTAR COMPARABLE

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Alumno() {}

	public Alumno(String dni) {
		super();
		this.dni = dni;
	}

	public Alumno(String dni, int edad, String ciclo, int curso, String nombre) {
		super();
		this.dni = dni;
		this.edad = edad;
		this.ciclo = ciclo;
		this.curso = curso;
		this.nombre = nombre;
	}

	private String dni;

	private int edad;

	private String ciclo;

	private int curso;

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getCiclo() {
		return ciclo;
	}

	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}

	public int getCurso() {
		return curso;
	}

	public void setCurso(int curso) {
		this.curso = curso;
	}

	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Alumno [nombre=" + nombre + "]";
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alumno other = (Alumno) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		return true;
	}
	
	// USAR EN CASO DE GASTAR COMPARABLE	@Override
	public int compareTo(Alumno alumno) {
		return this.nombre.compareTo(alumno.getNombre());
		
	}

	
}