package br.cin.ufpe.healthwatcher.business.employee;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import lib.exceptions.ObjectNotFoundException;
import lib.exceptions.ObjectNotValidException;
import lib.exceptions.PersistenceMechanismException;
import lib.exceptions.RepositoryException;
import lib.exceptions.TransactionException;
import lib.exceptions.UpdateEntryException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.cin.ufpe.healthwatcher.business.HealthWatcherFacade;
import br.cin.ufpe.healthwatcher.model.employee.Employee;

@ManagedBean
@SessionScoped
public class EmployeeEdit implements Serializable {

	private static final long serialVersionUID = -3894035124921216300L;

	private Employee employee;
	private String newPassword;
	private String newPasswordConfirm;
	private String currentPassword;
	
	private HealthWatcherFacade facade;
	
	public EmployeeEdit(){
		
	}
	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getNewPasswordConfirm() {
		return newPasswordConfirm;
	}

	public void setNewPasswordConfirm(String newPasswordConfirm) {
		this.newPasswordConfirm = newPasswordConfirm;
	}

	@PostConstruct
	private void init(){
		this.employee = new Employee();
	}
	
	public String atualizar(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		BCryptPasswordEncoder bPasswordEncoder = new BCryptPasswordEncoder();
		if(!bPasswordEncoder.matches(currentPassword, employee.getPassword())){
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
            		"Senha atual informada não confere com a registrada.", "Senha incorreta."));
            return "";
		}
		
		if(!newPassword.equals(newPasswordConfirm)){
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
            		"Nova senha não confere com a confirmação.", "Senha incorreta."));
            return "";
		}
		
		this.employee.setPassword(new BCryptPasswordEncoder().encode(newPassword));
		try {
			if(facade==null){
				facade = HealthWatcherFacade.getInstance();
			}
			facade.updateEmployee(employee);
		} catch (ObjectNotValidException | ObjectNotFoundException
				| RepositoryException | PersistenceMechanismException | IOException | TransactionException | UpdateEntryException e) {
			e.printStackTrace();
		}
		return "menuEmployee.jsf?faces-redirect=true";
	}
	
}
