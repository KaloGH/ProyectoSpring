package org.alumno.kalo.kalo_primera_app_spring_mvc.srv;

import org.springframework.stereotype.Service;

@Service
public class LoginService {
	
	public boolean usuarioValido(String usuario, String password) {
		
		return usuario.equals("kalo") && password.equals("contra");
	}

}
