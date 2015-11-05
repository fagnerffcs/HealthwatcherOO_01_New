package br.cin.ufpe.healthwatcher.data;

import java.util.List;

import br.cin.ufpe.healthwatcher.model.complaint.DiseaseType;
import lib.exceptions.ObjectAlreadyInsertedException;
import lib.exceptions.ObjectNotFoundException;
import lib.exceptions.ObjectNotValidException;
import lib.exceptions.RepositoryException;

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

	public List<DiseaseType> getDiseaseTypeList() throws ObjectNotFoundException, RepositoryException;

}
