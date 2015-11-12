package br.cin.ufpe.healthwatcher.data.rdb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import lib.exceptions.ObjectAlreadyInsertedException;
import lib.exceptions.ObjectNotFoundException;
import lib.exceptions.ObjectNotValidException;
import lib.exceptions.RepositoryException;
import lib.persistence.IPersistenceMechanism;
import lib.persistence.PersistenceMechanism;
import lib.util.ConcreteIterator;
import lib.util.IteratorDsk;
import br.cin.ufpe.healthwatcher.data.IHealthUnitRepository;
import br.cin.ufpe.healthwatcher.model.healthguide.HealthUnit;
import br.cin.ufpe.healthwatcher.model.healthguide.MedicalSpecialty;
import br.cin.ufpe.healthwatcher.util.JPAUtil;

public class HealthUnitRepositoryRDB implements Serializable, IHealthUnitRepository {
	
	private static final long serialVersionUID = 349734549768639456L;
	private IPersistenceMechanism mp;

	public HealthUnitRepositoryRDB(PersistenceMechanism mp) {
		this.mp = mp;
	}

	@SuppressWarnings("unchecked")
	public List<HealthUnit> listAllHealthUnits(){
		EntityManager em = new JPAUtil().getEntityManager();
		List<HealthUnit> lista = em.createNamedQuery("allHealthUnits").getResultList();
		em.close();
		return lista;
	}
	
	public List<MedicalSpecialty> listSpecialties(Integer code){
		EntityManager em = new JPAUtil().getEntityManager();
		try{
			HealthUnit healthUnit = (HealthUnit) em.createNamedQuery("healthUnitByName").setParameter("code", code).getSingleResult();
			return healthUnit.getSpecialities();
		} catch	(NoResultException nre){
			return null;
		} finally {
			em.close();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<HealthUnit> healthUnitsBySpecialty(Integer code){
		EntityManager em = new JPAUtil().getEntityManager();
		try{
			List<HealthUnit> lista = em.createNamedQuery("healthUnitsBySpecialty").setParameter("code", code).getResultList();
			return lista;
		} catch	(NoResultException nre){
			return null;
		} finally {
			em.close();
		}
		
	}

	public HealthUnit find(String value) {
		EntityManager em = new JPAUtil().getEntityManager();
		HealthUnit hu = em.find(HealthUnit.class, Integer.parseInt(value));
		em.close();
		return hu;
	}
	
	public void update(HealthUnit healthUnit){
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		em.merge(healthUnit);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void insert(HealthUnit us) throws ObjectNotValidException,
			ObjectAlreadyInsertedException, ObjectNotValidException,
			RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean exists(int num) throws RepositoryException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void remove(int code) throws ObjectNotFoundException,
			RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public HealthUnit search(int code) throws ObjectNotFoundException,
			RepositoryException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IteratorDsk getHealthUnitList() throws ObjectNotFoundException,
			RepositoryException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IteratorDsk getPartialHealthUnitList()
			throws ObjectNotFoundException, RepositoryException {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public IteratorDsk getHealthUnitListBySpeciality(int codEspecialidade)
			throws ObjectNotFoundException, RepositoryException {
		EntityManager em;
		List<HealthUnit> lista = new ArrayList<HealthUnit>(); 
		try{
			em = (EntityManager) this.mp.getCommunicationChannel();
			lista = em.createNamedQuery("healthUnitsBySpecialty").setParameter("code", codEspecialidade).getResultList();
		} catch (Exception e){
			
		}
		return new ConcreteIterator(lista);
	}


}
