package br.cin.ufpe.healthwatcher.business.complaint;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import lib.exceptions.ObjectNotFoundException;
import lib.exceptions.ObjectNotValidException;
import lib.exceptions.PersistenceMechanismException;
import lib.exceptions.RepositoryException;
import lib.exceptions.TransactionException;
import br.cin.ufpe.healthwatcher.business.HealthWatcherFacade;
import br.cin.ufpe.healthwatcher.model.complaint.AnimalComplaint;
import br.cin.ufpe.healthwatcher.model.complaint.Complaint;
import br.cin.ufpe.healthwatcher.model.complaint.FoodComplaint;
import br.cin.ufpe.healthwatcher.model.complaint.Situacao;
import br.cin.ufpe.healthwatcher.model.complaint.SpecialComplaint;

@ManagedBean
@ViewScoped
public class UpdateComplaintRecord implements Serializable {

	private static final long serialVersionUID = -6887424307646650506L;
	private Complaint complaint;
	private String complaintKind;
	private HealthWatcherFacade facade;
	
	public Complaint getComplaint() {
		return complaint;
	}
	
	public String update(Complaint q) throws RepositoryException,
			ObjectNotFoundException, ObjectNotValidException {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.getExternalContext().getFlash().put("complaintCode", complaint.getCodigo());
		try {
			if(this.facade==null){
				this.facade = HealthWatcherFacade.getInstance();
			}
			q.setSituacao(Situacao.CLOSED);
			q.setDataParecer(new Date());
			facade.updateComplaint(q);
			return "updateSearchComplaintData?faces-redirect=true";
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return "";
	}

	public String getComplaintKind() {
		if(this.complaint instanceof FoodComplaint){
			complaintKind = "Food";
		} else if (this.complaint instanceof AnimalComplaint){
			complaintKind = "Animal";
		} else if (complaint instanceof SpecialComplaint) {
			complaintKind = "Special";
		}
		return complaintKind;
	}
	
	public void onLoad(){
		try {
			if(this.facade==null){
				this.facade = HealthWatcherFacade.getInstance();
			}
			FacesContext facesContext = FacesContext.getCurrentInstance();
			Integer code = (Integer) facesContext.getExternalContext().getFlash().get("complaintCode");
			try {
				try {
					this.complaint = facade.searchComplaint(code);
				} catch (TransactionException e) {
					e.printStackTrace();
				}
			} catch (ObjectNotFoundException | RepositoryException e) {
				e.printStackTrace();
			}					
		} catch (PersistenceMechanismException | IOException e1) {
			e1.printStackTrace();
		}
	}

}
