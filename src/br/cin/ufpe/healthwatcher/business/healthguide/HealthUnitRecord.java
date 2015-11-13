package br.cin.ufpe.healthwatcher.business.healthguide;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import lib.exceptions.CommunicationException;
import lib.exceptions.ObjectNotFoundException;
import lib.exceptions.ObjectNotValidException;
import lib.exceptions.RepositoryException;
import lib.util.ConcreteIterator;
import lib.util.IteratorDsk;
import br.cin.ufpe.healthwatcher.data.IHealthUnitRepository;
import br.cin.ufpe.healthwatcher.model.healthguide.HealthUnit;

@ManagedBean
@SessionScoped
public class HealthUnitRecord implements Serializable {

	private static final long serialVersionUID = -4914101471627136696L;

	private HealthUnit selectedHealthUnit;
	private IHealthUnitRepository healthUnitRep;
	private List<HealthUnit> healthUnits;
	
	public List<HealthUnit> getHealthUnits() {
		return healthUnits;
	}

	public HealthUnitRecord(IHealthUnitRepository repUnidadeSaude){
		this.healthUnitRep = repUnidadeSaude;
	}

	public HealthUnit getSelectedHealthUnit() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Integer code = (Integer) facesContext.getExternalContext().getFlash().get("healthUnitCode");
		if(code!=null){
			try {
				selectedHealthUnit = this.healthUnitRep.search(code);
			} catch (ObjectNotFoundException | RepositoryException e) {
				e.printStackTrace();
			}
		}
		return selectedHealthUnit;
	}

	public void setSelectedHealthUnit(HealthUnit selectedHealthUnit) {
		this.selectedHealthUnit = selectedHealthUnit;
	}

	public void update(HealthUnit unit) throws RepositoryException,
			ObjectNotFoundException, ObjectNotValidException {
		healthUnitRep.update(unit);
	}

	public IteratorDsk searchSpecialitiesByHealthUnit(int code)
			throws ObjectNotFoundException, RepositoryException {
		HealthUnit us = healthUnitRep.search(code);
		return new ConcreteIterator(us.getSpecialities());
	}

	public IteratorDsk searchHealthUnitsBySpeciality(int code)
			throws ObjectNotFoundException, RepositoryException {
		return healthUnitRep.getHealthUnitListBySpeciality(code);
	}

	public IteratorDsk getHealthUnitList() throws RepositoryException,
			ObjectNotFoundException {
		return healthUnitRep.getHealthUnitList();
	}

	public IteratorDsk getPartialHealthUnitList() throws RepositoryException,
			ObjectNotFoundException {
		return healthUnitRep.getPartialHealthUnitList();
	}

	public HealthUnit search(int healthUnitCode)
			throws ObjectNotFoundException, RepositoryException {
		return healthUnitRep.search(healthUnitCode);
	}
	
	public List<HealthUnit> getSearchHealthUnitList(){
		List<HealthUnit> lista = new ArrayList<HealthUnit>();
		try {
			IteratorDsk it = healthUnitRep.getHealthUnitList();
			while(it.hasNext()){
				lista.add((HealthUnit) it.next());
			}
		} catch (ObjectNotFoundException | RepositoryException | CommunicationException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public String searchSpecialties(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		if(selectedHealthUnit!=null){
			facesContext.getExternalContext().getFlash().put("healthUnitCode", this.selectedHealthUnit.getCode());
			return "listSpecialtiesByHealthUnit?faces-redirect=true";
		} else {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Selecione um health unit.", "Erro ao selecionar."));
            return "";
		}
	}

}
