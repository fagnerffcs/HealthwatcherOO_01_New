package br.cin.ufpe.healthwatcher.service;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.cin.ufpe.healthwatcher.model.HealthUnit;
import br.cin.ufpe.healthwatcher.model.MedicalSpecialty;
import br.cin.ufpe.healthwatcher.util.JPAUtil;

public class HealthUnitService implements Serializable {
	
	private static final long serialVersionUID = 349734549768639456L;

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

}
