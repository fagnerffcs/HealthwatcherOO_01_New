package br.cin.ufpe.healthwatcher.exception;

public class EmployeeAlreadyExistsException extends Exception {
	
	private static final long serialVersionUID = -2351919880446236847L;

	private String employee;
	
	public EmployeeAlreadyExistsException() {
	}

	public EmployeeAlreadyExistsException(String login){
		this.employee = login;
	}
	
	@Override
	public String getMessage(){
		return "Login " + this.employee + " já cadastrado no sistema.";
	}
	

}
