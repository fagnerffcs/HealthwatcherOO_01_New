package br.cin.ufpe.healthwatcher.data.rdb;

import java.util.List;

import lib.exceptions.ObjectAlreadyInsertedException;
import lib.exceptions.ObjectNotFoundException;
import lib.exceptions.ObjectNotValidException;
import lib.exceptions.RepositoryException;
import lib.persistence.IPersistenceMechanism;
import lib.persistence.PersistenceMechanism;
import br.cin.ufpe.healthwatcher.data.ISpecialityRepository;
import br.cin.ufpe.healthwatcher.model.healthguide.MedicalSpecialty;

public class SpecialityRepositoryRDB implements ISpecialityRepository {

	private IPersistenceMechanism pm;
	
	public SpecialityRepositoryRDB(PersistenceMechanism pm) {
		this.pm = pm;
	}

	@Override
	public void insert(MedicalSpecialty esp) throws ObjectNotValidException,
			ObjectAlreadyInsertedException, ObjectNotValidException,
			RepositoryException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(MedicalSpecialty esp) throws ObjectNotValidException,
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
	public void remove(int codigo) throws ObjectNotFoundException,
			RepositoryException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<MedicalSpecialty> getSpecialityList()
			throws ObjectNotFoundException, RepositoryException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MedicalSpecialty search(int codigo) throws ObjectNotFoundException,
			RepositoryException {
		// TODO Auto-generated method stub
		return null;
	}

}
