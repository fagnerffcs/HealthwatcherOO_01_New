package br.cin.ufpe.healthwatcher.business.complaint;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.cin.ufpe.healthwatcher.business.HealthWatcherFacade;
import br.cin.ufpe.healthwatcher.model.address.Address;
import br.cin.ufpe.healthwatcher.model.complaint.Situacao;
import br.cin.ufpe.healthwatcher.model.complaint.SpecialComplaint;
import br.cin.ufpe.healthwatcher.model.employee.Employee;

@ManagedBean
@ViewScoped
public class SpecialComplaintRecord implements Serializable {
	
	private static final long serialVersionUID = -5104908221615000012L;

	private SpecialComplaint specialComplaint;
	private HealthWatcherFacade facade;
	
	public SpecialComplaintRecord() {
		specialComplaint = new SpecialComplaint();
		specialComplaint.setAtendente(new Employee());
		specialComplaint.setEnderecoSolicitante(new Address());
		specialComplaint.setEnderecoOcorrencia(new Address());		
	}
	
	@PostConstruct
	public void init(){
		specialComplaint = new SpecialComplaint();
		specialComplaint.setAtendente(new Employee());
		specialComplaint.setEnderecoSolicitante(new Address());
		specialComplaint.setEnderecoOcorrencia(new Address());
	}

	public SpecialComplaint getSpecialComplaint() {
		return specialComplaint;
	}

	public void setSpecialComplaint(SpecialComplaint specialComplaint) {
		this.specialComplaint = specialComplaint;
	}
	
	public String salvar(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		try{
			this.specialComplaint.setDataParecer(new Date());
			this.specialComplaint.setDataQueixa(new Date());
			this.specialComplaint.setSituacao(Situacao.OPEN);
			if(facade==null){
				this.facade = HealthWatcherFacade.getInstance();				
			}			
			facesContext.getExternalContext().getFlash().put("codigo", facade.insertComplaint(specialComplaint));
			return "specialComplaintInserted?faces-redirect=true";
		} catch(Exception e){
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Não foi possível registrar a reclamação!", "Registration mal sucedido"));
            return "";
		}
	}

}
