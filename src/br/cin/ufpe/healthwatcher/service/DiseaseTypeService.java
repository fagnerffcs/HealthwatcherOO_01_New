package br.cin.ufpe.healthwatcher.service;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.cin.ufpe.healthwatcher.model.complaint.DiseaseType;
import br.cin.ufpe.healthwatcher.util.JPAUtil;

public class DiseaseTypeService implements Serializable {

	private static final long serialVersionUID = 2946254639830068002L;

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

	
}
