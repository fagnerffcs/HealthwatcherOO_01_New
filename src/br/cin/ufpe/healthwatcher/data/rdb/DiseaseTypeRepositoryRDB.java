package br.cin.ufpe.healthwatcher.data.rdb;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import lib.exceptions.ObjectAlreadyInsertedException;
import lib.exceptions.ObjectNotFoundException;
import lib.exceptions.ObjectNotValidException;
import lib.exceptions.RepositoryException;
import lib.persistence.PersistenceMechanism;
import br.cin.ufpe.healthwatcher.data.IDiseaseRepository;
import br.cin.ufpe.healthwatcher.model.complaint.DiseaseType;
import br.cin.ufpe.healthwatcher.util.JPAUtil;

public class DiseaseTypeRepositoryRDB implements Serializable, IDiseaseRepository {

	private static final long serialVersionUID = 2946254639830068002L;

	public DiseaseTypeRepositoryRDB(){
		
	}
	
	public DiseaseTypeRepositoryRDB(PersistenceMechanism pm) {
	}

	public DiseaseType find(String value) {
		EntityManager em = new JPAUtil().getEntityManager();
		DiseaseType dt = em.find(DiseaseType.class, Integer.parseInt(value));
		em.close();
		return dt;
	}

	@SuppressWarnings("unchecked")
	public List<DiseaseType> findAll() {
		EntityManager em = new JPAUtil().getEntityManager();
		List<DiseaseType> lista = null;
		try{
			lista = em.createNamedQuery("allDiseases").getResultList();
		} catch(NoResultException nre) {
			
		}
		em.close();
		return lista;
	}

	@Override
	public void insert(DiseaseType td) throws ObjectNotValidException,
			ObjectAlreadyInsertedException, ObjectNotValidException,
			RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(DiseaseType td) throws ObjectNotValidException,
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
	public DiseaseType search(int code) throws ObjectNotFoundException,
			RepositoryException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DiseaseType> getDiseaseTypeList()
			throws ObjectNotFoundException, RepositoryException {
		// TODO Auto-generated method stub
		return null;
	}

	
}
