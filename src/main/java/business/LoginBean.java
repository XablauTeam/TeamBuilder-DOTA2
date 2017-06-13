package business;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import business.exceptions.UsuarioSenhaException;
import model.entities.User;
import model.persistence.service.UserService;

@Stateless
public class LoginBean {
	@EJB
	private UserService service;
	
	public User login(String login, String password) throws UsuarioSenhaException {
		User user = service.login(login, password);
		if(user != null){
			return user;
		}else{
			throw new UsuarioSenhaException("Usuario ou Senha inválidos");
		}
		
	}

}
