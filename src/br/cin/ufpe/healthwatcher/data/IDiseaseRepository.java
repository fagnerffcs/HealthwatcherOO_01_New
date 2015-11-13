package br.cin.ufpe.healthwatcher.data;

import lib.exceptions.ObjectAlreadyInsertedException;
import lib.exceptions.ObjectNotFoundException;
import lib.exceptions.ObjectNotValidException;
import lib.exceptions.RepositoryException;
import lib.util.IteratorDsk;
import br.cin.ufpe.healthwatcher.model.complaint.DiseaseType;

public interface IDiseaseRepository {

	public void insert(DiseaseType td) throws ObjectNotValidException,
			ObjectAlreadyInsertedException, ObjectNotValidException,
			RepositoryException;

	public void update(DiseaseType td) throws ObjectNotValidException,
			ObjectNotFoundException, ObjectNotValidException,
			RepositoryException;

	public boolean exists(int code) throws RepositoryException;

	public DiseaseType search(int code) throws ObjectNotFoundException,
			RepositoryException;

	public IteratorDsk getDiseaseTypeList() throws ObjectNotFoundException, RepositoryException;

}
