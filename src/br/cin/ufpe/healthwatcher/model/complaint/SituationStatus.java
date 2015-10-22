package br.cin.ufpe.healthwatcher.model.complaint;

public enum SituationStatus {
	Aberta(1), Suspensa(2), Fechada(3);
	
	private final int code;
	
	int code(){ return this.code;}
	
	SituationStatus(int code){
		this.code = code;
	}
		
}
