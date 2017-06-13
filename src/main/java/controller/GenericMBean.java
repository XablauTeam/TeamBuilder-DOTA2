package controller;

import java.util.Iterator;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@SessionScoped
@ManagedBean
public abstract class GenericMBean {

	public String forward(String page){
		return page+"?faces-redirect=true";
	}
	
	public String logout(){
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return forward("index.xhtml");
	}
	
	public void limpaErros(){
		FacesContext context = FacesContext.getCurrentInstance();
		Iterator<FacesMessage> it = context.getMessages();
		while (it.hasNext()) {
			it.next();
			it.remove();
		}
	}
	
	public void incluirErro(String erro){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(erro));
	}
	
	
}
