package org.alumno.kalo.kalo_primera_app_spring_mvc.model.order;

import java.util.Comparator;

import org.alumno.kalo.kalo_primera_app_spring_mvc.model.ram.Alumno;


public class ComparadorAlumnoDni implements Comparator<Alumno> {

	@Override
	public int compare(Alumno a1, Alumno a2) {
		// TODO Auto-generated method stub
		return a1.getDni().compareTo(a2.getDni());
	}
	
}