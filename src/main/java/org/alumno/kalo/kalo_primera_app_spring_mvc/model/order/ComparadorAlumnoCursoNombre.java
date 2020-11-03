package org.alumno.kalo.kalo_primera_app_spring_mvc.model.order;

import java.util.Comparator;

import org.alumno.kalo.kalo_primera_app_spring_mvc.model.Alumno;


public class ComparadorAlumnoCursoNombre implements Comparator<Alumno>{

	@Override
	public int compare(Alumno a1, Alumno a2) {
		// TODO Auto-generated method stub
		int comparaCurso = a1.getCurso()-a2.getCurso();
		
		if (comparaCurso != 0) {
			
			return comparaCurso;
			
		} else {
			
			return a1.getNombre().compareTo(a2.getNombre());
		}
	}

}
