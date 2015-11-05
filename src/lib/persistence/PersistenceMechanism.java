package lib.persistence;

import javax.persistence.EntityManager;

import br.cin.ufpe.healthwatcher.util.JPAUtil;
import lib.exceptions.PersistenceMechanismException;
import lib.exceptions.TransactionException;

public class PersistenceMechanism implements IPersistenceMechanism {
	
	private static PersistenceMechanism singleton;
	
	private JPAUtil jpaUtil;
	private EntityManager em;

	public static synchronized PersistenceMechanism getInstance() throws PersistenceMechanismException {
		if (singleton == null){
			singleton = new PersistenceMechanism();
			singleton.setJpaUtil(new JPAUtil());
		}
		return singleton;
	}
	
	@Override
	public void connect() throws PersistenceMechanismException {
		this.em = singleton.getJpaUtil().getEntityManager();
	}

	@Override
	public void disconnect() throws PersistenceMechanismException {
		this.em.close();		
	}

	@Override
	public Object getCommunicationChannel()	throws PersistenceMechanismException {
		return this.em;
	}

	@Override
	public void releaseCommunicationChannel() throws PersistenceMechanismException {
		this.em.clear();
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

	public JPAUtil getJpaUtil() {
		return jpaUtil;
	}

	public void setJpaUtil(JPAUtil jpaUtil) {
		this.jpaUtil = jpaUtil;
	}

}
