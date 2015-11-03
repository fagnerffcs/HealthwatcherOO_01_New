package br.cin.ufpe.healthwatcher.business.complaint;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import lib.exceptions.ObjectAlreadyInsertedException;
import lib.exceptions.ObjectNotFoundException;
import lib.exceptions.ObjectNotValidException;
import lib.exceptions.RepositoryException;
import lib.exceptions.TransactionException;
import br.cin.ufpe.healthwatcher.data.IComplaintRepository;
import br.cin.ufpe.healthwatcher.data.rdb.AnimalComplaintRepositoryRDB;
import br.cin.ufpe.healthwatcher.data.rdb.FoodComplaintRepositoryRDB;
import br.cin.ufpe.healthwatcher.data.rdb.SpecialComplaintRepositoryRDB;
import br.cin.ufpe.healthwatcher.model.complaint.AnimalComplaint;
import br.cin.ufpe.healthwatcher.model.complaint.Complaint;
import br.cin.ufpe.healthwatcher.model.complaint.FoodComplaint;
import br.cin.ufpe.healthwatcher.model.complaint.SpecialComplaint;

@ManagedBean
@ViewScoped
public class ComplaintRecord implements Serializable {

	private static final long serialVersionUID = -6887424307646650506L;
	
	private Integer complaintCode;
	private FoodComplaint foodComplaint;
	private AnimalComplaint animalComplaint;
	private SpecialComplaint specialComplaint;
	private boolean foodComplaintFlag = false;
	private boolean animalComplaintFlag = false;
	private boolean specialComplaintFlag = false;
	private boolean noDataFound = true;
	
	private FoodComplaintRepositoryRDB foodComplaintService = new FoodComplaintRepositoryRDB();
	private AnimalComplaintRepositoryRDB animalComplaintService = new AnimalComplaintRepositoryRDB();
	private SpecialComplaintRepositoryRDB specialComplaintService = new SpecialComplaintRepositoryRDB();
	
	public ComplaintRecord() {
		this.foodComplaint 		= new FoodComplaint();
		this.animalComplaint 	= new AnimalComplaint();
		this.specialComplaint 	= new SpecialComplaint();
	}
	
	public ComplaintRecord(IComplaintRepository rep) {
		// TODO Auto-generated constructor stub
	}

	public Integer getComplaintCode() {
		return complaintCode;
	}

	public void setComplaintCode(Integer complaintCode) {
		this.complaintCode = complaintCode;
	}
	
	public FoodComplaint getFoodComplaint() {
		return foodComplaint;
	}

	public void setFoodComplaint(FoodComplaint foodComplaint) {
		this.foodComplaint = foodComplaint;
	}

	public AnimalComplaint getAnimalComplaint() {
		return animalComplaint;
	}

	public void setAnimalComplaint(AnimalComplaint animalComplaint) {
		this.animalComplaint = animalComplaint;
	}

	public SpecialComplaint getSpecialComplaint() {
		return specialComplaint;
	}

	public void setSpecialComplaint(SpecialComplaint specialComplaint) {
		this.specialComplaint = specialComplaint;
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

	public String searchComplaint(){
		this.foodComplaint = foodComplaintService.find(this.complaintCode);
		if(this.foodComplaint==null){
			this.animalComplaint = animalComplaintService.find(this.complaintCode);
			if(this.animalComplaint==null){
				this.specialComplaint = specialComplaintService.find(this.complaintCode);
				if(this.specialComplaint!=null){
					this.specialComplaintFlag = true;
				}
			} else {
				this.animalComplaintFlag = true;
			}
		} else {
			this.foodComplaintFlag = true;
		}
		this.noDataFound = this.animalComplaint==null && this.foodComplaint==null && this.specialComplaint==null;
		return "searchComplaintData?faces-redirect=true";
	}

	public boolean isNoDataFound() {
		return noDataFound;
	}

	public void setNoDataFound(boolean noDataFound) {
		this.noDataFound = noDataFound;
	}

	public Complaint search(int code) throws RepositoryException, ObjectNotFoundException, TransactionException {
		// TODO Auto-generated method stub
		return null;
	}

	public int insert(Complaint complaint) throws RepositoryException,	ObjectAlreadyInsertedException, ObjectNotValidException, TransactionException {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Complaint> getComplaintList() throws ObjectNotFoundException, TransactionException {
		// TODO Auto-generated method stub
		return null;
	}
	

}
