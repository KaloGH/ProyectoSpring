package org.alumno.kalo.kalo_primera_app_spring_mvc.model.order;

import java.util.Comparator;

import org.alumno.kalo.kalo_primera_app_spring_mvc.model.Alumno;


public class ComparadorAlumnoCicloNombre implements Comparator<Alumno> {

	@Override
	public int compare(Alumno a1, Alumno a2) {
		// TODO Auto-generated method stub
		int comparaCiclo = a1.getCiclo().compareTo(a2.getCiclo());
		
		if (comparaCiclo != 0) {
			
			return comparaCiclo;
			
		} else {
			
			return a1.getNombre().compareTo(a2.getNombre());
		}
		
	}
	
}
