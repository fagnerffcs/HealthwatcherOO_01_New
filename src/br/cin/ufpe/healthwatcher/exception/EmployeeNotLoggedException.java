package br.cin.ufpe.healthwatcher.exception;

public class EmployeeNotLoggedException extends Exception {

	private static final long serialVersionUID = 3192389146180989704L;
	
	@Override
	public String getMessage(){
		return "Não existe nenhum employee logado no sistema.";
	}

}
