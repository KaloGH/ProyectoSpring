package org.alumno.kalo.kalo_primera_app_spring_mvc.model;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

public class DocAlumno implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Size(min=9 , message="El DNI debe de tener un tamaño minimo de 9")
	private String dni;
	
	@NotNull(message = "Porfavor ingresa ID")
	private Integer id;
	
	@NotNull(message = "Porfavor ingresa el tipo")
	private String tipo;
	
	@Length(min= 10, message="Los comentarios deben tener al menos 10 caracteres.")
	private String comentario;

	public DocAlumno(Integer id) {
		super();
		this.id = id;
	}
	public DocAlumno() {
		super();
	}

	public DocAlumno(@Size(min = 9, message = "El DNI debe de tener un tamaño minimo de 9") String dni,
			@NotEmpty Integer id, @NotEmpty String tipo,
			@Length(min = 10, message = "Los comentarios deben tener al menos 10 caracteres.") String comentario) {
		super();
		this.dni = dni;
		this.id = id;
		this.tipo = tipo;
		this.comentario = comentario;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

}
