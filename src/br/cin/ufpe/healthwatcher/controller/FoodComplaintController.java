package br.cin.ufpe.healthwatcher.controller;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.cin.ufpe.healthwatcher.model.Address;
import br.cin.ufpe.healthwatcher.model.Employee;
import br.cin.ufpe.healthwatcher.model.FoodComplaint;
import br.cin.ufpe.healthwatcher.model.Situacao;
import br.cin.ufpe.healthwatcher.service.FoodComplaintService;

@ManagedBean
@ViewScoped
public class FoodComplaintController implements Serializable {
	
	private static final long serialVersionUID = -396582813699440065L;

	private FoodComplaintService foodComplaintService = new FoodComplaintService();
	
	private FoodComplaint foodComplaint;
	
	public FoodComplaintController() {
		this.foodComplaint = new FoodComplaint();
		this.foodComplaint.setEnderecoDoente(new Address());
		this.foodComplaint.setEnderecoSolicitante(new Address());
	}
	
	@PostConstruct
	public void init(){
		foodComplaint = new FoodComplaint();
		foodComplaint.setAtendente(new Employee());
		foodComplaint.setEnderecoDoente(new Address());
		foodComplaint.setEnderecoSolicitante(new Address());
	}

	public FoodComplaint getFoodComplaint() {
		return foodComplaint;
	}

	public void setFoodComplaint(FoodComplaint foodComplaint) {
		this.foodComplaint = foodComplaint;
	}
	
	public String salvar(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		try{
			foodComplaint.setDataQueixa(new Date());
			foodComplaint.setSituacao(Situacao.OPEN);
			foodComplaintService.inserir(foodComplaint);
			facesContext.getExternalContext().getFlash().put("codigo", foodComplaint.getCodigo());
			init();
			return "foodComplaintInserted?faces-redirect=true";
		} catch(Exception e){
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Não foi possível registrar a reclamação!", "Registration mal sucedido"));
            return "";
		}
	}
	
}
