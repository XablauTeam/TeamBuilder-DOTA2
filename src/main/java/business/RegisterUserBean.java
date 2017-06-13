package business;

import business.exceptions.BusinessException;
import model.entities.User;
import model.persistence.service.UserService;

public class RegisterUserBean {
	private UserService service = new UserService();
	
	public boolean createUser(String nome, String login, String senha, String email) throws BusinessException{
		User user = new User(nome, login, senha, email);
		try{
			if(user != null){
				service.insert(new User(nome, login, senha, email));
			}
			return true;
		}catch(Exception e){
			throw new BusinessException(e.getMessage());
		}
		
	}

}
