package br.cin.ufpe.healthwatcher.data.rdb;

import javax.persistence.EntityManager;

import lib.exceptions.ObjectAlreadyInsertedException;
import lib.exceptions.ObjectNotFoundException;
import lib.exceptions.ObjectNotValidException;
import lib.exceptions.PersistenceMechanismException;
import lib.exceptions.RepositoryException;
import lib.persistence.IPersistenceMechanism;
import lib.persistence.PersistenceMechanism;
import lib.util.IteratorDsk;
import br.cin.ufpe.healthwatcher.data.IComplaintRepository;
import br.cin.ufpe.healthwatcher.model.complaint.Complaint;
import br.cin.ufpe.healthwatcher.util.JPAUtil;

@SuppressWarnings("unused")
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
		EntityManager em;
		try {
			em = (EntityManager) this.pm.getCommunicationChannel();
			em.persist(complaint);
		} catch (PersistenceMechanismException e) {
			e.printStackTrace();
		}
		return complaint.getCodigo();
	}

	@Override
	public void update(Complaint complaint) throws ObjectNotValidException,
			ObjectNotFoundException, ObjectNotValidException,
			RepositoryException {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		em.merge(complaint);
		em.getTransaction().commit();
		em.close();		
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
	public IteratorDsk getComplaintList() throws ObjectNotFoundException,
			RepositoryException {
		// TODO Auto-generated method stub
		return null;
	}

}
