package org.alumno.kalo.kalo_primera_app_spring_mvc.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.alumno.kalo.kalo_primera_app_spring_mvc.model.interfaces.Modificable;
import org.hibernate.validator.constraints.Length;


public class Alumno  implements Modificable<Alumno>,Serializable,Comparable<Alumno>{  //<= USAR EN CASO DE GASTAR COMPARABLE

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Size(min=9 , message="El DNI debe de tener un tama�o minimo de 9")
	private String dni;

	@Size(min=5, message="El nombre debe de tener una longitud minima de 5 caracteres.")
	private String nombre;
	
	@Min(value=18 , message="Debes tener minimo 18 a�os.")@Max(value=100 , message="No puedes tener mas de 100 a�os.")
	private int edad;

	@Length(min=3 , max= 5, message="Ingresa la abreviaci�n de la asignatura. No es necesario todo el nombre.")@NotEmpty()
	private String ciclo;
	
	@Min(value=1, message="El curso inicial es 1")@Max(value=4, message="El curso mas alto es 4")
	private int curso;
	
	private Date ts;
	private String user;
	
	
	
	
	
	
	
	public Date getTs() {
		return ts;
	}

	public void setTs(Date ts) {
		this.ts = ts;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

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

	public boolean sePuedeModificarUtilizando(Alumno itemModificado) {
		if ( this.getUser() != null && this.getTs() != null ) {
			// Existe un usuario y una fecha Inicial y tenemos que comprobar
			String usuarioActual = this.getUser();
			String usuarioModificado = itemModificado.getUser();
			
			Date fechaActual = Ts.parseIso(Ts.formatIso(this.getTs()));
			Date fechaModificada = Ts.parseIso(Ts.formatIso(itemModificado.getTs()));
			
			if (!usuarioActual.equals(usuarioModificado) || !fechaActual.equals(fechaModificada)) 
				// El usuario no es el mismo o la fecha cambia
				return false;
			
		}
		// No tenemos fecha o usuario -> 1� modificacion por lo que se puede modificar.
		return true;
	}

	public String mensajeNoSePuedeModificar() {
		// Mensaje generico para poder reutilizarlo
		String msg = "\r\n\t[ERROR]\r\n<br/>"+
		"\t '$item' ha sido modificado por otro usuario.\r\n<br/>"+
		"\t Para evitar la p�rdida de informacion se impide guardar '$item'.\r\n<br/>"+
		"\t Ultima modificacion realizada por ["+this.getUser() + "] el ["+
		Ts.ts(this.getTs()) + "]\r\n<br/>";
		
		// Para concretar el tipo de registro modificado sustituimos $item por Alumno.
		return msg.replace("$item", "Alumno");
	}

	
}