package org.alumno.kalo.kalo_primera_app_spring_mvc.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.alumno.kalo.kalo_primera_app_spring_mvc.validaciones.ImagenValida;
import org.springframework.web.multipart.MultipartFile;

public class ImagenUsuario implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Size(min=5,message="El usuario debe tener un tamaño de 5 caracteres")
	private String nickname;
	@NotNull
	@ImagenValida
	private MultipartFile imagen;
	public ImagenUsuario(String nickname) {
		this.nickname = nickname;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public MultipartFile getImagen() {
		return imagen;
	}
	public void setImagen(MultipartFile imagen) {
		this.imagen = imagen;
	}
}
