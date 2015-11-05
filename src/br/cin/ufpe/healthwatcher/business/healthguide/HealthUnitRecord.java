package br.cin.ufpe.healthwatcher.business.healthguide;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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
	
	public HealthUnitRecord(IHealthUnitRepository repUnidadeSaude){
		this.healthUnitRep = repUnidadeSaude;
	}

	public HealthUnit getSelectedHealthUnit() {
		return selectedHealthUnit;
	}

	public void setSelectedHealthUnit(HealthUnit selectedHealthUnit) {
		this.selectedHealthUnit = selectedHealthUnit;
	}

	public void update(HealthUnit unit) throws RepositoryException,
			ObjectNotFoundException, ObjectNotValidException {
		healthUnitRep.update(unit);
	}

	public IteratorDsk searchSpecialityByHealthUnit(int code)
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

}
