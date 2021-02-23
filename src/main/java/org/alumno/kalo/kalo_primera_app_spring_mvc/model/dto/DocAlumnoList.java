package org.alumno.kalo.kalo_primera_app_spring_mvc.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.alumno.kalo.kalo_primera_app_spring_mvc.validaciones.DocumentoAlumnoValido;
import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class DocAlumnoList implements Comparable<DocAlumnoList>{
	@Getter
	@Setter
	@Pattern(regexp = "[0-9]{8}[A-Za-z]{1}", message = "El dni debe tener 8 números y una letra")
	private String dni;
	@Getter
	@Setter
	@NotNull(message = "El id no puede estar vacio")
	private Integer id;
	@Getter
	@Setter
	@NotNull(message = "El tipo no puede estar vacio")
	private String tipo;
	@Getter
	@Setter
	@Size(min = 10, message = "Los comentarios debe tener almenos 10 carácteres")
	private String comentario;
	@Getter
	@Setter
	String tipoFichero;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		DocAlumnoList other = (DocAlumnoList) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	public DocAlumnoList() {
		super();
	}
	public DocAlumnoList(Integer id) {
		super();
		this.id = id;
	}
	@Override
	public int compareTo(DocAlumnoList doc) {
		return id-doc.getId();
	}
	
}
