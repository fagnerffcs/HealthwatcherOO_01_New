package br.cin.ufpe.healthwatcher.data.rdb;

import java.util.List;

import lib.exceptions.ObjectAlreadyInsertedException;
import lib.exceptions.ObjectNotFoundException;
import lib.exceptions.ObjectNotValidException;
import lib.exceptions.RepositoryException;
import lib.persistence.IPersistenceMechanism;
import lib.persistence.PersistenceMechanism;
import br.cin.ufpe.healthwatcher.data.IComplaintRepository;
import br.cin.ufpe.healthwatcher.model.complaint.Complaint;

public class ComplaintRepositoryRDB implements IComplaintRepository {
	
	private IPersistenceMechanism pm;
	
	private EmployeeRepositoryRDB employeeRep;

	public ComplaintRepositoryRDB(PersistenceMechanism pm) {
		this.pm = pm;
		employeeRep = new EmployeeRepositoryRDB(pm);
	}

	@Override
	public int insert(Complaint complaint) throws ObjectNotValidException,
			ObjectAlreadyInsertedException, ObjectNotValidException,
			RepositoryException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update(Complaint complaint) throws ObjectNotValidException,
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
	public void remove(int code) throws ObjectNotFoundException,
			RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Complaint search(int complaint) throws ObjectNotFoundException,
			RepositoryException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Complaint> getComplaintList() throws ObjectNotFoundException,
			RepositoryException {
		// TODO Auto-generated method stub
		return null;
	}

}
