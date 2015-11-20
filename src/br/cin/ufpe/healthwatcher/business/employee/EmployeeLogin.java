package br.cin.ufpe.healthwatcher.business.employee;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import lib.exceptions.ObjectNotFoundException;
import lib.exceptions.ObjectNotValidException;
import lib.exceptions.PersistenceMechanismException;
import lib.exceptions.RepositoryException;
import lib.exceptions.TransactionException;
import lib.exceptions.UpdateEntryException;
import br.cin.ufpe.healthwatcher.business.HealthWatcherFacade;
import br.cin.ufpe.healthwatcher.model.employee.Employee;

@ManagedBean(name="employeeLogin")
@SessionScoped
public class EmployeeLogin implements Serializable {

	private static final long serialVersionUID = -8729930869176381346L;

	private String login;
	private String password;
	private boolean logged = false;
	private HealthWatcherFacade facade;
	
	public String getLogin() {
		if(login==null){
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			this.login = (String) session.getAttribute("login");
		}
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLogged() {
		return logged;
	}

	public void setLogged(boolean isLogged) {
		this.logged = isLogged;
	}
	
	public String login() throws RepositoryException, ObjectNotValidException, UpdateEntryException{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		try{
			Employee employee = null;
			if(facade==null){
				this.facade = HealthWatcherFacade.getInstance();				
			}
			employee = facade.searchEmployee(this.login);
			if(employee.validatePassword(password)){
				this.logged = true;
				HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
				session.setAttribute("login", login);				
				return "/employee/menuEmployee?faces-redirect=true";
			} else {
	            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
										"Invalid login. Try again.", "Invalid Credentials!"));
				return "";
			}
		}catch(ObjectNotFoundException onfe){
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"Invalid password. Try again.", "Invalid Credentials!"));
			return "";			
		} catch(TransactionException te){
			
		} catch(PersistenceMechanismException pme){
			
		} catch(IOException ioe){
			
		}
		return "";
	}
	
	public String logout() {
		this.logged = false;
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.removeAttribute("login");
		return "/home?faces-redirect=true";
	}

	public String changeLoggedUser() {
		this.logged = false;
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.removeAttribute("login");		
		return "/login?faces-redirect=true";
	}	
	
}
