package br.cin.ufpe.healthwatcher.business.healthguide;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import lib.exceptions.CommunicationException;
import lib.exceptions.ObjectNotFoundException;
import lib.exceptions.PersistenceMechanismException;
import lib.exceptions.RepositoryException;
import lib.exceptions.TransactionException;
import lib.util.IteratorDsk;
import br.cin.ufpe.healthwatcher.business.HealthWatcherFacade;
import br.cin.ufpe.healthwatcher.data.ISpecialityRepository;
import br.cin.ufpe.healthwatcher.model.healthguide.HealthUnit;
import br.cin.ufpe.healthwatcher.model.healthguide.MedicalSpecialty;

@ManagedBean
@SessionScoped
public class MedicalSpecialtyRecord implements Serializable {
	
	private static final long serialVersionUID = -4955973920455439632L;

	private MedicalSpecialty selectedMedicalSpecialty;
	private List<MedicalSpecialty> specialtiesList;
	private List<HealthUnit> healthUnits;
	private ISpecialityRepository repEspecialidade;
	
	private HealthWatcherFacade facade;
	
	public MedicalSpecialtyRecord(ISpecialityRepository repEspecialidade) {
		this.repEspecialidade = repEspecialidade;
	}	
	
	public MedicalSpecialtyRecord() {
		this.healthUnits = new ArrayList<HealthUnit>();
	}

	public MedicalSpecialty getSelectedMedicalSpecialty() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Integer code = (Integer) facesContext.getExternalContext().getFlash().get("specialtyCode");
		if(code!=null){
			try {
				selectedMedicalSpecialty = this.repEspecialidade.search(code);
			} catch (ObjectNotFoundException | RepositoryException e) {
				e.printStackTrace();
			}
		}
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
			this.healthUnits = new ArrayList<HealthUnit>();
			try {
				if(facade==null){
					try {
						facade = HealthWatcherFacade.getInstance();
					} catch (PersistenceMechanismException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}				
				IteratorDsk it = facade.getfCid().getHealthUnitRecord().searchHealthUnitsBySpeciality(this.selectedMedicalSpecialty.getCode());
				while(it.hasNext()){
					this.healthUnits.add((HealthUnit) it.next());
				}
			} catch (CommunicationException e) {
				e.printStackTrace();
			} catch (ObjectNotFoundException e) {
				e.printStackTrace();
			} catch (RepositoryException e) {
				e.printStackTrace();
			}
		}
		return healthUnits;
	}

	public void setHealthUnits(List<HealthUnit> healthUnits) {
		this.healthUnits = healthUnits;
	}
	
	public String searchHealthUnits(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		if(selectedMedicalSpecialty!=null){
			facesContext.getExternalContext().getFlash().put("specialtyCode", this.selectedMedicalSpecialty.getCode());
			return "listHealthUnitsBySpecialty.jsf?faces-redirect=true";
		} else {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Selecione um health unit.", "Erro ao selecionar."));
            return "";			
		}
	}

	public List<MedicalSpecialty> getListaEspecialidade() throws RepositoryException,	ObjectNotFoundException, TransactionException {
		IteratorDsk medSpecialtyIterator = this.repEspecialidade.getSpecialityList();
		List<MedicalSpecialty> lista = new ArrayList<MedicalSpecialty>();
		try {
			while(medSpecialtyIterator.hasNext()){
				lista.add((MedicalSpecialty) medSpecialtyIterator.next());
			}
		} catch (CommunicationException e) {
			e.printStackTrace();
		}
		return lista;
	}
	

}
