package br.cin.ufpe.healthwatcher.data.mem;

import lib.exceptions.ObjectAlreadyInsertedException;
import lib.exceptions.ObjectNotFoundException;
import lib.exceptions.ObjectNotValidException;
import lib.exceptions.RepositoryException;
import lib.util.IteratorDsk;
import br.cin.ufpe.healthwatcher.data.IHealthUnitRepository;
import br.cin.ufpe.healthwatcher.model.healthguide.HealthUnit;

public class HealthUnitRepositoryArray implements IHealthUnitRepository {

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
	public IteratorDsk getHealthUnitList() throws ObjectNotFoundException,
			RepositoryException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IteratorDsk getPartialHealthUnitList()
			throws ObjectNotFoundException, RepositoryException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IteratorDsk getHealthUnitListBySpeciality(int codEspecialidade)
			throws ObjectNotFoundException, RepositoryException {
		// TODO Auto-generated method stub
		return null;
	}


}
