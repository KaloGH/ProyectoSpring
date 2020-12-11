package org.alumno.kalo.kalo_primera_app_spring_mvc.excepciones;

import org.alumno.kalo.kalo_primera_app_spring_mvc.model.Modulo;

public class ModuloDuplicadoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Modulo oldModulo;
	private Modulo newModulo;
	
	public ModuloDuplicadoException(Modulo newModulo, Modulo oldModulo) {
		
		this.oldModulo = oldModulo;
		this.newModulo = newModulo;
	}
	
	@Override
	public String toString() {
		return "El ID del modulo "+newModulo.getId()+ 
				" ya esta registrado para el Modulo "+oldModulo.getNombre();
	}
	
	
	
}