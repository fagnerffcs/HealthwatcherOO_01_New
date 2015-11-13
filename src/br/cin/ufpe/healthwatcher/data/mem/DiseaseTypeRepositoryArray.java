package br.cin.ufpe.healthwatcher.data.mem;

import lib.exceptions.ObjectAlreadyInsertedException;
import lib.exceptions.ObjectNotFoundException;
import lib.exceptions.ObjectNotValidException;
import lib.exceptions.RepositoryException;
import lib.util.IteratorDsk;
import br.cin.ufpe.healthwatcher.data.IDiseaseRepository;
import br.cin.ufpe.healthwatcher.model.complaint.DiseaseType;

public class DiseaseTypeRepositoryArray implements IDiseaseRepository {

	@Override
	public void insert(DiseaseType td) throws ObjectNotValidException,
			ObjectAlreadyInsertedException, ObjectNotValidException,
			RepositoryException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(DiseaseType td) throws ObjectNotValidException,
			ObjectNotFoundException, ObjectNotValidException,
			RepositoryException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean exists(int code) throws RepositoryException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DiseaseType search(int code) throws ObjectNotFoundException,
			RepositoryException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IteratorDsk getDiseaseTypeList()
			throws ObjectNotFoundException, RepositoryException {
		// TODO Auto-generated method stub
		return null;
	}

}
