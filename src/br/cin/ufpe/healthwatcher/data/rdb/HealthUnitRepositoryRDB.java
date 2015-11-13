package br.cin.ufpe.healthwatcher.data.rdb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

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

public class HealthUnitRepositoryRDB implements Serializable, IHealthUnitRepository {
	
	private static final long serialVersionUID = 349734549768639456L;
	private IPersistenceMechanism mp;

	public HealthUnitRepositoryRDB(PersistenceMechanism mp) {
		this.mp = mp;
	}

	@Override
	public void insert(HealthUnit us) throws ObjectNotValidException,
			ObjectAlreadyInsertedException, ObjectNotValidException,
			RepositoryException {
		EntityManager em;
		try{
			em = (EntityManager) this.mp.getCommunicationChannel();
			em.persist(us);
		} catch (Exception e){
			
		}		
	}

	@Override
	public boolean exists(int num) throws RepositoryException {
		HealthUnit us = null;
		try{
			us = search(num);
		}catch(Exception e){
			
		}
		return us != null;
	}

	@Override
	public void remove(int code) throws ObjectNotFoundException,
			RepositoryException {
		EntityManager em;
		HealthUnit us = search(code);
		try{
			em = (EntityManager) this.mp.getCommunicationChannel();
			em.remove(us);
		} catch (Exception e){
			
		}
		
	}

	@Override
	public HealthUnit search(int code) throws ObjectNotFoundException,
			RepositoryException {
		EntityManager em;
		try{
			em = (EntityManager) this.mp.getCommunicationChannel();
			return em.find(HealthUnit.class, code);
		} catch (Exception e){
			
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public IteratorDsk getHealthUnitList() throws ObjectNotFoundException,
			RepositoryException {
		EntityManager em;
		List<HealthUnit> lista = new ArrayList<HealthUnit>(); 
		try{
			em = (EntityManager) this.mp.getCommunicationChannel();
			lista = em.createNamedQuery("allHealthUnits").getResultList();
		} catch (Exception e){
			
		}
		return new ConcreteIterator(lista);
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

	@Override
	public void update(HealthUnit us) throws ObjectNotValidException,
			ObjectNotFoundException, ObjectNotValidException,
			RepositoryException {
		EntityManager em;
		try{
			em = (EntityManager) this.mp.getCommunicationChannel();
			em.merge(us);
		} catch (Exception e){
			
		}	
	}


}
