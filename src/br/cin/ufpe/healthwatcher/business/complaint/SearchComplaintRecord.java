package br.cin.ufpe.healthwatcher.business.complaint;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import lib.exceptions.ObjectNotFoundException;
import lib.exceptions.PersistenceMechanismException;
import lib.exceptions.RepositoryException;
import lib.exceptions.TransactionException;
import lib.util.IteratorDsk;
import br.cin.ufpe.healthwatcher.business.HealthWatcherFacade;
import br.cin.ufpe.healthwatcher.model.complaint.AnimalComplaint;
import br.cin.ufpe.healthwatcher.model.complaint.Complaint;
import br.cin.ufpe.healthwatcher.model.complaint.SpecialComplaint;

@ManagedBean
@RequestScoped
public class SearchComplaintRecord implements Serializable {

	private static final long serialVersionUID = -6887424307646650506L;
	
	private Complaint complaint;
	private List<Complaint> complaints;
	private boolean foodComplaintFlag = false;
	private boolean animalComplaintFlag = false;
	private boolean specialComplaintFlag = false;
	private boolean noDataFound;
	private String complaintKind;
	HealthWatcherFacade facade;
	
	@PostConstruct
	private void init(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		if(facesContext!=null){
			Flash flash = facesContext.getExternalContext().getFlash();
			String codigo = (String) flash.get("complaint");
			if(codigo!=null){
				try {
					if(facade==null){
						facade = HealthWatcherFacade.getInstance();
					}
					this.complaint = facade.getfCid().searchComplaint(Integer.parseInt(codigo));
				} catch (NumberFormatException | RepositoryException | PersistenceMechanismException | IOException
						| ObjectNotFoundException | TransactionException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public boolean isFoodComplaintFlag() {
		return foodComplaintFlag;
	}

	public void setFoodComplaintFlag(boolean foodComplaintFlag) {
		this.foodComplaintFlag = foodComplaintFlag;
	}

	public boolean isAnimalComplaintFlag() {
		return animalComplaintFlag;
	}

	public void setAnimalComplaintFlag(boolean animalComplaintFlag) {
		this.animalComplaintFlag = animalComplaintFlag;
	}

	public boolean isSpecialComplaintFlag() {
		return specialComplaintFlag;
	}

	public void setSpecialComplaintFlag(boolean specialComplaintFlag) {
		this.specialComplaintFlag = specialComplaintFlag;
	}

	public boolean isNoDataFound() {
		if(this.complaints!=null){
			noDataFound = this.complaints.size() == 0;
		}
		return noDataFound;
	}

	public void setNoDataFound(boolean noDataFound) {
		this.noDataFound = noDataFound;
	}

	public Complaint getComplaint() {
		return complaint;
	}

	public void setComplaint(Complaint complaint) {
		this.complaint = complaint;
	}

	public List<Complaint> getComplaints() {
		this.complaints = new ArrayList<Complaint>();
		try{
			if(facade==null){
				facade = HealthWatcherFacade.getInstance();
			}
			IteratorDsk it = facade.getfCid().getComplaintList();
			while(it.hasNext()){
				Complaint c = (Complaint) it.next();
				this.complaints.add(c);
			}
		} catch (Exception e){
			
		}
		return complaints;
	}

	public void setComplaints(List<Complaint> complaints) {
		this.complaints = complaints;
	}
	
	public String searchComplaint(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.getExternalContext().getFlash().put("complaint", complaint.getCodigo());
		facesContext.getExternalContext().getFlash().put("complaintKind", complaintKind);
		return "updateSearchComplaint?faces-redirect=true";
	}
	
	public String updateComplaint(){
		try{
			if(facade==null){
				facade = HealthWatcherFacade.getInstance();
			}
			facade.getfCid().getComplaintRecord().update(this.complaint);
			init();
			return "updateSearchComplaintData?faces-redirect=true";
		} catch (Exception e) {
			return "";
		}
	}	

	public String getComplaintKind() {
		if(this.complaint!=null){
			if(this.complaint instanceof AnimalComplaint){
				complaintKind = "Animal complaint";
			} else if(this.complaint instanceof SpecialComplaint){
				complaintKind = "Special complaint";
			} else {
				complaintKind = "Food complaint";
			}
		}
		return complaintKind;
	}

}
