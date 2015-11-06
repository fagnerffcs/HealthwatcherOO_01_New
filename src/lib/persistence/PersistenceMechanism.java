package lib.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import lib.exceptions.PersistenceMechanismException;
import lib.exceptions.TransactionException;

public class PersistenceMechanism implements IPersistenceMechanism {
	
	private static PersistenceMechanism singleton;
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public static PersistenceMechanism getInstance() throws PersistenceMechanismException {
		if (singleton == null){
			singleton = new PersistenceMechanism();
		}
		return singleton;
	}
	
	@Override
	public void connect() throws PersistenceMechanismException {
		this.emf = Persistence.createEntityManagerFactory("healthwatcher");
		this.em = this.emf.createEntityManager();
	}

	@Override
	public void disconnect() throws PersistenceMechanismException {
		this.emf.close();
	}

	@Override
	public Object getCommunicationChannel()	throws PersistenceMechanismException {
		if(!this.em.isOpen()){
			this.em = this.emf.createEntityManager();			
		}
		return this.em;
	}

	@Override
	public void releaseCommunicationChannel() throws PersistenceMechanismException {
		this.em.close();
	}

	@Override
	public void beginTransaction() throws TransactionException {
		this.em.getTransaction().begin();
	}

	@Override
	public void commitTransaction() throws TransactionException {
		this.em.getTransaction().commit();
	}

	@Override
	public void rollbackTransaction() throws TransactionException {
		this.em.getTransaction().rollback();
	}

}
