package br.cin.ufpe.healthwatcher.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.cin.ufpe.healthwatcher.model.Employee;
import br.cin.ufpe.healthwatcher.service.EmployeeService;

@ManagedBean
@SessionScoped
public class EmployeeEdit implements Serializable {

	private static final long serialVersionUID = -3894035124921216300L;

	private Employee employee;
	private String newPassword;
	private String newPasswordConfirm;
	private String currentPassword;
	
	@Inject
	private FacesContext facesContext;
	
	@Inject
	private EmployeeService employeeService;
	
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
		employeeService.update(employee);
		return "menuEmployee.jsf?faces-redirect=true";
	}
	
}
