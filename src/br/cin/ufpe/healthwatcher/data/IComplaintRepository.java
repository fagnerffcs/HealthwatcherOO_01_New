package br.cin.ufpe.healthwatcher.data;

import java.util.List;

import lib.exceptions.ObjectAlreadyInsertedException;
import lib.exceptions.ObjectNotFoundException;
import lib.exceptions.ObjectNotValidException;
import lib.exceptions.RepositoryException;
import br.cin.ufpe.healthwatcher.model.complaint.Complaint;

public interface IComplaintRepository {

	public int insert(Complaint complaint) throws ObjectNotValidException,
			ObjectAlreadyInsertedException, ObjectNotValidException,
			RepositoryException;

	public void update(Complaint complaint) throws ObjectNotValidException,
			ObjectNotFoundException, ObjectNotValidException,
			RepositoryException;

	public boolean exists(int code) throws RepositoryException;

	public void remove(int code) throws ObjectNotFoundException,
			RepositoryException;

	public Complaint search(int complaint) throws ObjectNotFoundException,
			RepositoryException;

	public List<Complaint> getComplaintList() throws ObjectNotFoundException,
			RepositoryException;

}
