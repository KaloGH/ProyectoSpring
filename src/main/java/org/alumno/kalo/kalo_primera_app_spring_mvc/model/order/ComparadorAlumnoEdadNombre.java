package org.alumno.kalo.kalo_primera_app_spring_mvc.model.order;

import java.util.Comparator;

import org.alumno.kalo.kalo_primera_app_spring_mvc.model.ram.Alumno;


public class ComparadorAlumnoEdadNombre implements Comparator<Alumno> {

	@Override
	public int compare(Alumno a1, Alumno a2) {
		// TODO Auto-generated method stub
		int comparaEdad = a1.getEdad()-a2.getEdad(); // Sacamos si la edad es diferente.
		if (comparaEdad != 0) { // Si es diferente la ordena por la edad.
			return comparaEdad;
		} else {
			return a1.getNombre().compareTo(a2.getNombre()); // De lo contrario ordena por nombre.
		}
	}

}
