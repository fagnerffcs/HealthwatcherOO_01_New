package br.cin.ufpe.healthwatcher.data;

import java.util.List;

import lib.exceptions.ObjectAlreadyInsertedException;
import lib.exceptions.ObjectNotFoundException;
import lib.exceptions.ObjectNotValidException;
import lib.exceptions.RepositoryException;
import br.cin.ufpe.healthwatcher.model.healthguide.HealthUnit;

public interface IHealthUnitRepository {

	public void insert(HealthUnit us) throws ObjectNotValidException,
			ObjectAlreadyInsertedException, ObjectNotValidException,
			RepositoryException;

	public void update(HealthUnit us) throws ObjectNotValidException,
			ObjectNotFoundException, ObjectNotValidException,
			RepositoryException;

	public boolean exists(int num) throws RepositoryException;

	public void remove(int code) throws ObjectNotFoundException,
			RepositoryException;

	public HealthUnit search(int code) throws ObjectNotFoundException,
			RepositoryException;

	public List<HealthUnit> getHealthUnitList() throws ObjectNotFoundException,
			RepositoryException;

	public List<HealthUnit> getPartialHealthUnitList()
			throws ObjectNotFoundException, RepositoryException;

	public List<HealthUnit> getHealthUnitListBySpeciality(int codEspecialidade)
			throws ObjectNotFoundException, RepositoryException;

}
