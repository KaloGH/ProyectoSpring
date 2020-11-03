package org.alumno.kalo.kalo_primera_app_spring_mvc.model.order;

import java.util.Comparator;

import org.alumno.kalo.kalo_primera_app_spring_mvc.model.LogError;

public class ComparadorLogErrorTipo implements Comparator<LogError> {

	@Override
	public int compare(LogError e1, LogError e2) {
		// TODO Auto-generated method stub
		return e1.getTipo().compareTo(e2.getTipo());
	}

	
	

}
