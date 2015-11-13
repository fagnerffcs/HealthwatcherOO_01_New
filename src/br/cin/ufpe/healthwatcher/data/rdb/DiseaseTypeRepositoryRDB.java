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
import br.cin.ufpe.healthwatcher.data.IDiseaseRepository;
import br.cin.ufpe.healthwatcher.model.complaint.DiseaseType;

public class DiseaseTypeRepositoryRDB implements Serializable, IDiseaseRepository {

	private static final long serialVersionUID = 2946254639830068002L;
	private IPersistenceMechanism mp;
	
	public DiseaseTypeRepositoryRDB(PersistenceMechanism mp) {
		this.mp = mp;
	}

	@Override
	public void insert(DiseaseType td) throws ObjectNotValidException,
			ObjectAlreadyInsertedException, ObjectNotValidException,
			RepositoryException {
		EntityManager em;
		try{
			em = (EntityManager) this.mp.getCommunicationChannel();
			em.persist(td);
		} catch(Exception e){
			
		}
	}

	@Override
	public void update(DiseaseType td) throws ObjectNotValidException,
			ObjectNotFoundException, ObjectNotValidException,
			RepositoryException {
		EntityManager em;
		try{
			em = (EntityManager) this.mp.getCommunicationChannel();
			em.merge(td);
		} catch(Exception e){
			
		}
		
	}

	@Override
	public boolean exists(int code) throws RepositoryException {
		try{
			return search(code)!=null;
		} catch(Exception e){
			
		}
		return false;
	}

	@Override
	public DiseaseType search(int code) throws ObjectNotFoundException,
			RepositoryException {
		EntityManager em;
		try{
			em = (EntityManager) this.mp.getCommunicationChannel();
			return em.find(DiseaseType.class, code);
		} catch(Exception e){
			
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public IteratorDsk getDiseaseTypeList()
			throws ObjectNotFoundException, RepositoryException {
		EntityManager em;
		List<DiseaseType> lista = new ArrayList<DiseaseType>();
		try{
			em = (EntityManager) this.mp.getCommunicationChannel();
			lista = em.createNamedQuery("allDiseases").getResultList();
		} catch(Exception e){
			
		}
		return new ConcreteIterator(lista);
	}

	
}
