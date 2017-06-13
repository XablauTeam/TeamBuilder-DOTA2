package model.persistence.service;

import java.util.List;

import javax.ejb.Stateless;

import model.entities.User;
import model.persistence.dao.UserDAO;

@Stateless
public class UserService {
	
	private static UserDAO userDAO;
	
	
	public UserService() {
		userDAO = new UserDAO();
	}

	public void insert(User usuario) {
		userDAO.openCurrentSessionwithTransaction();
		userDAO.insert(usuario);
		userDAO.closeCurrentSessionwithTransaction();
	}

	public void update(User usuario) {
		userDAO.openCurrentSessionwithTransaction();
		userDAO.update(usuario);
		userDAO.closeCurrentSessionwithTransaction();
	}

	public User findById(Integer id) {
		userDAO.openCurrentSession();
		User Usuario = userDAO.findById(id);
		userDAO.closeCurrentSession();
		return Usuario;
	}

	public void delete(Integer id) {
		userDAO.openCurrentSessionwithTransaction();
		User Usuario = userDAO.findById(id);
		userDAO.delete(Usuario);
		userDAO.closeCurrentSessionwithTransaction();
	}

	public List<User> findAll() {
		userDAO.openCurrentSession();
		List<User> Usuarios = userDAO.findAll();
		userDAO.closeCurrentSession();
		return Usuarios;
	}

	public void deleteAll() {
		userDAO.openCurrentSessionwithTransaction();
		userDAO.deleteAll();
		userDAO.closeCurrentSessionwithTransaction();
	}

	public User login(String login, String password) {
		userDAO.openCurrentSession();
		User user = userDAO.login(login, password);
		userDAO.closeCurrentSession();
		return user;
	}

	public UserDAO usuarioDAO() {
		return userDAO;
	}
}