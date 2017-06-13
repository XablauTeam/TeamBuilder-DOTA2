package controller;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import business.LoginBean;
import business.exceptions.UsuarioSenhaException;
import model.entities.User;

@ManagedBean(name = "loginMBean")
@SessionScoped
public class LoginMBean extends GenericMBean {
	
	@EJB
	LoginBean loginBean;

	private String login;
	private String senha;
	private User user;

	public String realizarLogin() {
		try {
			this.user = loginBean.login(getLogin(), getSenha()); 
			return forward("principal.xhtml");
		} catch (UsuarioSenhaException e) {
			incluirErro(e.getMessage());
			return "";
		}
	}

	public boolean validaCampos() {

		limpaErros();

		if (login.isEmpty() || senha.isEmpty()) {
			return false;
		}
		return true;
	}

	public String register() {
		return forward("register.xhtml");
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
