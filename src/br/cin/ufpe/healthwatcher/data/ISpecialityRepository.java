package br.cin.ufpe.healthwatcher.data;

import lib.exceptions.ObjectAlreadyInsertedException;
import lib.exceptions.ObjectNotFoundException;
import lib.exceptions.ObjectNotValidException;
import lib.exceptions.RepositoryException;
import lib.util.IteratorDsk;
import br.cin.ufpe.healthwatcher.model.healthguide.MedicalSpecialty;

public interface ISpecialityRepository {

	public void insert(MedicalSpecialty esp) throws ObjectNotValidException,
			ObjectAlreadyInsertedException, ObjectNotValidException,
			RepositoryException;

	public void update(MedicalSpecialty esp) throws ObjectNotValidException,
			ObjectNotFoundException, ObjectNotValidException,
			RepositoryException;

	public boolean exists(int num) throws RepositoryException;

	public void remove(int codigo) throws ObjectNotFoundException,
			RepositoryException;

	public IteratorDsk getSpecialityList() throws ObjectNotFoundException,
			RepositoryException;

	public MedicalSpecialty search(int codigo) throws ObjectNotFoundException,
			RepositoryException;

}
