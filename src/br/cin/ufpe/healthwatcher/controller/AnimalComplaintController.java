package br.cin.ufpe.healthwatcher.controller;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.cin.ufpe.healthwatcher.model.Address;
import br.cin.ufpe.healthwatcher.model.AnimalComplaint;
import br.cin.ufpe.healthwatcher.model.Employee;
import br.cin.ufpe.healthwatcher.model.Situacao;
import br.cin.ufpe.healthwatcher.service.AnimalComplaintService;

@ManagedBean
@ViewScoped
public class AnimalComplaintController implements Serializable {
	
	private static final long serialVersionUID = 7502327389893929089L;

	private AnimalComplaintService animalComplaintService = new AnimalComplaintService();
	
	private AnimalComplaint animalComplaint;
	
	public AnimalComplaintController() {
		animalComplaint = new AnimalComplaint();
		animalComplaint.setAtendente(new Employee());
		animalComplaint.setOccurenceLocalAddress(new Address());
		animalComplaint.setEnderecoSolicitante(new Address());
	}
	
	@PostConstruct
	public void init(){
		animalComplaint = new AnimalComplaint();
		animalComplaint.setAtendente(new Employee());
		animalComplaint.setOccurenceLocalAddress(new Address());
		animalComplaint.setEnderecoSolicitante(new Address());
	}

	public AnimalComplaint getAnimalComplaint() {
		return animalComplaint;
	}

	public void setAnimalComplaint(AnimalComplaint animalComplaint) {
		this.animalComplaint = animalComplaint;
	}
	
	public String salvar(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		try{
			this.animalComplaint.setDataParecer(new Date());
			this.animalComplaint.setDataQueixa(new Date());
			this.animalComplaint.setSituacao(Situacao.OPEN);
			animalComplaintService.inserir(animalComplaint);
			facesContext.getExternalContext().getFlash().put("codigo", animalComplaint.getCodigo());
			init();
			return "animalComplaintInserted?faces-redirect=true";
		} catch(Exception e){
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Não foi possível registrar a reclamação!", "Registration mal sucedido"));
            return "";
		}
	}

}
