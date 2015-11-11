package br.cin.ufpe.healthwatcher.business.complaint;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import lib.exceptions.ObjectNotFoundException;
import lib.exceptions.PersistenceMechanismException;
import lib.exceptions.RepositoryException;
import lib.exceptions.TransactionException;
import lib.util.IteratorDsk;
import br.cin.ufpe.healthwatcher.business.HealthWatcherFacade;
import br.cin.ufpe.healthwatcher.model.complaint.Complaint;

@ManagedBean
@RequestScoped
public class SearchComplaintRecord implements Serializable {

	private static final long serialVersionUID = -6887424307646650506L;
	
	private int complaintCode;
	private Complaint complaint;
	
	public int getComplaintCode() {
		return complaintCode;
	}

	public void setComplaintCode(int complaintCode) {
		this.complaintCode = complaintCode;
	}

	private List<Complaint> complaints;
	private boolean foodComplaintFlag = false;
	private boolean animalComplaintFlag = false;
	private boolean specialComplaintFlag = false;
	private boolean noDataFound;
	private HealthWatcherFacade facade;
	
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
	
	public String search(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Complaint complaint;
		try {
			if(facade==null){
				facade = HealthWatcherFacade.getInstance();
			}
			complaint = facade.getfCid().searchComplaint(complaintCode);
			facesContext.getExternalContext().getFlash().put("complaint", complaint.getCodigo());
			return "updateSearchComplaint?faces-redirect=true";			
		} catch (RepositoryException | ObjectNotFoundException | IOException | PersistenceMechanismException
				| TransactionException e) {
			e.printStackTrace();
		}
		return "";
	}

	public Complaint getComplaint() {
		return complaint;
	}

	public void setComplaint(Complaint complaint) {
		this.complaint = complaint;
	}

}
