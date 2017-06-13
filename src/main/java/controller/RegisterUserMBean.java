package controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import business.RegisterUserBean;
import business.exceptions.BusinessException;

@ManagedBean(name = "registerUserMBean")
@SessionScoped
public class RegisterUserMBean extends GenericMBean {

	private RegisterUserBean registerBean = new RegisterUserBean();

	private String email;
	private String login;
	private String senha;
	private String nome;

	public String criarUser() {
		try {
			if (validaCampos()) {
				registerBean.createUser(nome, login, senha, email);
				return forward("index.xhtml");
			}
		} catch (BusinessException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
		}

		return "";
	}
	
	public String cancelar(){
		this.email = "";
		this.login = "";
		this.senha = "";
		this.nome = "";
		return forward("index.xhtml");
	}

	public boolean validaCampos() {

		limpaErros();

		if (email.isEmpty() || login.isEmpty() || senha.isEmpty() || nome.isEmpty()) {
			return false;
		}
		return true;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
