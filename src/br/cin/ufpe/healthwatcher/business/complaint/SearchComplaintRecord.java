package br.cin.ufpe.healthwatcher.business.complaint;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import br.cin.ufpe.healthwatcher.data.rdb.AnimalComplaintRepositoryRDB;
import br.cin.ufpe.healthwatcher.data.rdb.FoodComplaintRepositoryRDB;
import br.cin.ufpe.healthwatcher.data.rdb.SpecialComplaintRepositoryRDB;
import br.cin.ufpe.healthwatcher.model.complaint.AnimalComplaint;
import br.cin.ufpe.healthwatcher.model.complaint.Complaint;
import br.cin.ufpe.healthwatcher.model.complaint.FoodComplaint;
import br.cin.ufpe.healthwatcher.model.complaint.Situacao;
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
	
	private FoodComplaintRepositoryRDB foodComplaintService = new FoodComplaintRepositoryRDB();
	
	private AnimalComplaintRepositoryRDB animalComplaintService = new AnimalComplaintRepositoryRDB();
	
	private SpecialComplaintRepositoryRDB specialComplaintRepositoryRDB = new SpecialComplaintRepositoryRDB();
	
	@PostConstruct
	private void init(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		if(facesContext!=null){
			Flash flash = facesContext.getExternalContext().getFlash();
			String codigo = (String) flash.get("complaint");
			String tipoComplaint = (String) flash.get("complaintKind");
			if(codigo!=null){
				if(tipoComplaint.equals("Animal complaint")){
					this.complaint = animalComplaintService.find(Integer.parseInt(codigo));
				} else if(tipoComplaint.equals("Special complaint")){
					this.complaint = specialComplaintRepositoryRDB.find(Integer.parseInt(codigo));
				} else {
					this.complaint = foodComplaintService.find(Integer.parseInt(codigo));
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
		this.complaints.addAll(animalComplaintService.listAnimalComplaintsBySituation(Situacao.OPEN));
		this.complaints.addAll(foodComplaintService.listFoodComplaintsBySituation(Situacao.OPEN));
		this.complaints.addAll(specialComplaintRepositoryRDB.listSpecialComplaintsBySituation(Situacao.OPEN));
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
		if(this.complaint instanceof FoodComplaint){
			foodComplaintService.update((FoodComplaint) this.complaint);
		} else if(this.complaint instanceof AnimalComplaint){
			animalComplaintService.update((AnimalComplaint) this.complaint);
		} else {
			specialComplaintRepositoryRDB.update((SpecialComplaint) this.complaint);
		}
		init();
		return "updateSearchComplaintData?faces-redirect=true";
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
