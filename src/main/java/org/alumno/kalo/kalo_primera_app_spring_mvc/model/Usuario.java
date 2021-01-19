package org.alumno.kalo.kalo_primera_app_spring_mvc.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.alumno.kalo.kalo_primera_app_spring_mvc.model.interfaces.Modificable;

public class Usuario implements Serializable,Comparable<Usuario>,Modificable<Usuario> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	@Size(min=5 , message="Tu nombre de ususario debe contener como minimo 5 caracteres.")
	private String nickname;
	
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", message="Debe tener entre 8 y 16 caracteres. Una Mayuscula. Una Minuscula. Un numero. Un caracter especial.")
	private String password;
	
	private String nombre;
	
	private String nombreFicheroConImagen; // Contendra el nickname.ext , donde ext será JPG , GIF o PNG.
	
	private Date ts; // Almacena la fecha de la ultima modificacion
	private String user; //Almacena la persona que ha realizado la ultima modificacion.
	
	
	public Usuario(String nickname,
					String nombre,
					String password) {
		super();
		this.nickname = nickname;
		this.password = password;
		this.nombre = nombre;
		this.nombreFicheroConImagen = "desconocido.jpg";
	}
	
	public Usuario(String nickname,
			String password) {
			super();
			this.nickname = nickname;
			this.password = password;
	}
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Usuario() {}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nickname == null) ? 0 : nickname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (nickname == null) {
			if (other.nickname != null)
				return false;
		} else if (!nickname.equals(other.nickname))
			return false;
		return true;
	}

	@Override
	public int compareTo(Usuario user) {
		return this.getNickname().compareTo(user.getNickname());
	}

	@Override
	public Date getTs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTs(Date ts) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setUser(String user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean sePuedeModificarUtilizando(Usuario itemModificado) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String mensajeNoSePuedeModificar() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
