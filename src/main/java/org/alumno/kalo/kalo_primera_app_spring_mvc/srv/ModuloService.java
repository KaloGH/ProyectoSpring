package org.alumno.kalo.kalo_primera_app_spring_mvc.srv;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.alumno.kalo.kalo_primera_app_spring_mvc.excepciones.ModuloDuplicadoException;
import org.alumno.kalo.kalo_primera_app_spring_mvc.model.Modulo;
import org.springframework.stereotype.Service;

@Service
public class ModuloService {
	private static List<Modulo> modulos = new ArrayList<Modulo>();

	static {
		modulos.add(new Modulo(1, "Programación", 8, "PRO"));
		modulos.add(new Modulo(2, "Desarrollo web en entorno de servidor", 8, "DWES"));
	}

	public List<Modulo> listar(String criterio) {
		switch (criterio) {
		case "nombre":
			modulos = modulos.stream()
					.sorted((m1, m2) -> m1.getNombre().compareTo(m2.getNombre()))
					.collect(Collectors.toList());
			break;
		case "horas":
			modulos = modulos.stream()
					.sorted((m1, m2) -> m1.getHoras() - m2.getHoras())
					.collect(Collectors.toList());
			break;
		case "abv":
			modulos = modulos.stream()
					.sorted((m1, m2) -> m1.getAbreviatura().compareTo(m2.getAbreviatura()))
					.collect(Collectors.toList());
			break;
		default:
			modulos = modulos.stream()
					.sorted((m1, m2) -> m1.getId() - m2.getId())
					.collect(Collectors.toList());
			break;
		}
		return modulos;
	}

	public void add(Modulo nuevo) throws ModuloDuplicadoException {
		Modulo existente = checkIfExists(nuevo);
		if (null != existente)
			throw new ModuloDuplicadoException(nuevo, existente);
		modulos.add(nuevo);
	}

	public static Modulo checkIfExists(Modulo nuevo) {
		Optional<Modulo> modulo = modulos.stream().filter(x -> x.getId() == nuevo.getId()).findFirst();
		if (modulo.isPresent())
			return modulo.get();
		return null;
	}

	public void deleteModulo(int id) {
		modulos.removeIf(x -> x.getId() == id);
	}

	public Modulo getModulo(int id) {
		for (Modulo x : modulos) {
			if (x.getId() == id)
				return x;
		}
		return null;
	}
	
	public int obtenerId() {
		int lastId=0;
		for (Modulo modulo : modulos) {
			lastId = modulo.getId();
		}
		return ++lastId;
	}

}
