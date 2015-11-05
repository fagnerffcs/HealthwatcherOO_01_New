package br.cin.ufpe.healthwatcher.business.healthguide;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import lib.exceptions.ObjectNotFoundException;
import lib.exceptions.RepositoryException;
import lib.exceptions.TransactionException;
import br.cin.ufpe.healthwatcher.data.ISpecialityRepository;
import br.cin.ufpe.healthwatcher.data.rdb.HealthUnitRepositoryRDB;
import br.cin.ufpe.healthwatcher.data.rdb.SpecialtyRepositoryRDB;
import br.cin.ufpe.healthwatcher.model.healthguide.HealthUnit;
import br.cin.ufpe.healthwatcher.model.healthguide.MedicalSpecialty;

@ManagedBean
@SessionScoped
public class MedicalSpecialtyRecord implements Serializable {
	
	private static final long serialVersionUID = -4955973920455439632L;

	private MedicalSpecialty selectedMedicalSpecialty;
	private List<MedicalSpecialty> specialtiesList;
	private List<HealthUnit> healthUnits;
	
	private SpecialtyRepositoryRDB specialtyRepositoryRDB = new SpecialtyRepositoryRDB();
	
	private HealthUnitRepositoryRDB healthUnitRepositoryRDB = new HealthUnitRepositoryRDB();
	
	private ISpecialityRepository repEspecialidade;
	
	public MedicalSpecialtyRecord(ISpecialityRepository repEspecialidade) {
		this.repEspecialidade = repEspecialidade;
	}	
	
	public MedicalSpecialtyRecord() {
		this.specialtiesList = specialtyRepositoryRDB.listAllMedicalSpecialties();
		this.healthUnits = new ArrayList<HealthUnit>();
	}

	public MedicalSpecialty getSelectedMedicalSpecialty() {
		return selectedMedicalSpecialty;
	}

	public void setSelectedMedicalSpecialty(MedicalSpecialty selectedMedicalSpecialty) {
		this.selectedMedicalSpecialty = selectedMedicalSpecialty;
	}

	public List<MedicalSpecialty> getSpecialtiesList() {
		return specialtiesList;
	}

	public void setSpecialtiesList(List<MedicalSpecialty> specialtiesList) {
		this.specialtiesList = specialtiesList;
	}

	public List<HealthUnit> getHealthUnits() {
		if(selectedMedicalSpecialty!=null){
			this.healthUnits = healthUnitRepositoryRDB.healthUnitsBySpecialty(selectedMedicalSpecialty.getCode());
		}
		return healthUnits;
	}

	public void setHealthUnits(List<HealthUnit> healthUnits) {
		this.healthUnits = healthUnits;
	}
	
	@PostConstruct
	private void init(){
		this.specialtiesList = specialtyRepositoryRDB.listAllMedicalSpecialties();
		this.healthUnits = new ArrayList<HealthUnit>();
	}
	
	public String searchHealthUnits(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		if(selectedMedicalSpecialty!=null){
			return "listHealthUnitsBySpecialty.jsf?faces-redirect=true";
		} else {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Selecione um health unit.", "Erro ao selecionar."));
            return "";			
		}
	}

	public List<MedicalSpecialty> getListaEspecialidade() throws RepositoryException,	ObjectNotFoundException, TransactionException {
		// TODO Auto-generated method stub
		return null;
	}
	

}
