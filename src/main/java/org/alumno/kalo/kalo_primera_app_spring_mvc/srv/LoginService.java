package org.alumno.kalo.kalo_primera_app_spring_mvc.srv;

import java.util.ArrayList;
import java.util.List;

import org.alumno.kalo.kalo_primera_app_spring_mvc.model.ImagenUsuario;
import org.alumno.kalo.kalo_primera_app_spring_mvc.model.Ts;
import org.alumno.kalo.kalo_primera_app_spring_mvc.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class LoginService {
	
	@Autowired
	FileService fileService;
	
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

	public void modificaUsuario(Usuario modificado, String usuarioModificacion) throws Exception { // throws Exception
        Usuario usuario = encontrarUsuarioPorNickname(usuarioModificacion);

        if(null != usuario) {
 

                System.out.println("ha entrado al if 2");
                for (int i = 0; i < usersList.size(); i++) { //Recorregem tots els alumnos
                    //Si alumno i es igual que al que li hem pasat el sobreescribim
                    if (usersList.get(i).getNickname().contentEquals(modificado.getNickname())) {
                        System.out.println("ha entrado al if 3");
                        modificado.setTs(Ts.today());
                        modificado.setUser(usuarioModificacion);
                        usersList.set(i, modificado);
                        break;
                    }
                }
            } else {
                System.out.println("Ha sido modificado recientemente");
                throw new Exception(usuario.mensajeNoSePuedeModificar());
            } 
        }
   	}
	
