package br.cin.ufpe.healthwatcher.business.healthguide;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import lib.exceptions.ObjectAlreadyInsertedException;
import lib.exceptions.ObjectNotFoundException;
import lib.exceptions.ObjectNotValidException;
import lib.exceptions.RepositoryException;
import lib.exceptions.TransactionException;
import br.cin.ufpe.healthwatcher.data.IHealthUnitRepository;
import br.cin.ufpe.healthwatcher.data.rdb.HealthUnitRepositoryRDB;
import br.cin.ufpe.healthwatcher.model.healthguide.HealthUnit;
import br.cin.ufpe.healthwatcher.model.healthguide.MedicalSpecialty;

@ManagedBean
@SessionScoped
public class HealthUnitRecord implements Serializable, IHealthUnitRepository {

	private static final long serialVersionUID = -4914101471627136696L;
	
	private List<HealthUnit> healthUnits;
	
	private HealthUnit selectedHealthUnit;
	
	private List<MedicalSpecialty> specialties;
	
	private HealthUnitRepositoryRDB healthUnitRepositoryRDB = new HealthUnitRepositoryRDB();
	
	private IHealthUnitRepository healthUnitRep;
	
	public HealthUnitRecord(IHealthUnitRepository repUnidadeSaude) {
		this.healthUnitRep = repUnidadeSaude;
	}
	
	public HealthUnitRecord() {
		this.healthUnits = healthUnitRepositoryRDB.listAllHealthUnits();
		this.specialties = new ArrayList<MedicalSpecialty>();
	}
	
	@PostConstruct
	private void init(){
		this.healthUnits = healthUnitRepositoryRDB.listAllHealthUnits();
		this.specialties = new ArrayList<MedicalSpecialty>();
		this.selectedHealthUnit = new HealthUnit();
	}

	public List<HealthUnit> getHealthUnits() {
		return healthUnits;
	}

	public void setHealthUnits(List<HealthUnit> healthUnits) {
		this.healthUnits = healthUnits;
	}

	public HealthUnit getSelectedHealthUnit() {
		if(selectedHealthUnit!=null){
			this.specialties = healthUnitRepositoryRDB.listSpecialties(selectedHealthUnit.getCode());
		}
		return selectedHealthUnit;
	}

	public void setSelectedHealthUnit(HealthUnit selectedHealthUnit) {
		this.selectedHealthUnit = selectedHealthUnit;
	}

	public List<MedicalSpecialty> getSpecialities() {
		return specialties;
	}

	public void setSpecialities(List<MedicalSpecialty> specialities) {
		this.specialties = specialities;
	}
	
	public String searchSpecialties(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		if(selectedHealthUnit!=null){
			return "listSpecialtiesByHealthUnit.jsf?faces-redirect=true";
		} else {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Selecione um health unit.", "Erro ao selecionar."));
            return "";
		}
	}

	@Override
	public void insert(HealthUnit us) throws ObjectNotValidException,
			ObjectAlreadyInsertedException, ObjectNotValidException,
			RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(HealthUnit us) throws ObjectNotValidException,
			ObjectNotFoundException, ObjectNotValidException,
			RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean exists(int num) throws RepositoryException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void remove(int code) throws ObjectNotFoundException,
			RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public HealthUnit search(int code) throws ObjectNotFoundException,
			RepositoryException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HealthUnit> getHealthUnitList() throws ObjectNotFoundException,
			RepositoryException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HealthUnit> getPartialHealthUnitList()
			throws ObjectNotFoundException, RepositoryException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HealthUnit> getHealthUnitListBySpeciality(int codEspecialidade)
			throws ObjectNotFoundException, RepositoryException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<MedicalSpecialty> searchSpecialtyByHealthUnit(int code) throws RepositoryException, TransactionException, ObjectNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<HealthUnit> searchHealthUnitsBySpeciality(int code) throws ObjectNotFoundException, RepositoryException, TransactionException {
		// TODO Auto-generated method stub
		return null;
	}

}
