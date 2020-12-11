package org.alumno.kalo.kalo_primera_app_spring_mvc.srv;

import java.util.ArrayList;
import java.util.List;

import org.alumno.kalo.kalo_primera_app_spring_mvc.model.Usuario;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
	
	private static List<Usuario> usersList = new ArrayList<Usuario>();

	static {
		
		usersList.add(new Usuario("joseramon","Jose Ramon","miPassword@1"));
		usersList.add(new Usuario("kalon","Kaloyan Emilov","miPassword@1"));
		
	}
	
	
	public boolean usuarioValido(Usuario user) {
		
		for (Usuario usuario2 : usersList) {
			if (usuario2.equals(user)) {
				return usuario2.getPassword().equals(user.getPassword());	
			}
		}
		
		return false;
		
	}
	
	public Usuario encontrarUsuarioPorNickname(String nickname) {
		
		for (Usuario user : usersList) {
			if (user.getNickname().equals(nickname)) {
				return user;
			}
		}
		return null;
	}

}
