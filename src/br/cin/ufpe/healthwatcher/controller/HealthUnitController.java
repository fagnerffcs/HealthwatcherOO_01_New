package br.cin.ufpe.healthwatcher.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.cin.ufpe.healthwatcher.model.HealthUnit;
import br.cin.ufpe.healthwatcher.model.MedicalSpecialty;
import br.cin.ufpe.healthwatcher.service.HealthUnitService;

@ManagedBean
@SessionScoped
public class HealthUnitController implements Serializable {

	private static final long serialVersionUID = -4914101471627136696L;
	
	private List<HealthUnit> healthUnits;
	
	private HealthUnit selectedHealthUnit;
	
	private List<MedicalSpecialty> specialties;
	
	private HealthUnitService healthUnitService = new HealthUnitService();
	
	public HealthUnitController() {
		this.healthUnits = healthUnitService.listAllHealthUnits();
		this.specialties = new ArrayList<MedicalSpecialty>();
	}
	
	@PostConstruct
	private void init(){
		this.healthUnits = healthUnitService.listAllHealthUnits();
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
			this.specialties = healthUnitService.listSpecialties(selectedHealthUnit.getCode());
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

}
