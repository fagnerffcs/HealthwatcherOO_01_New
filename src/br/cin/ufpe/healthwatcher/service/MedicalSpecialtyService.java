package br.cin.ufpe.healthwatcher.service;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import br.cin.ufpe.healthwatcher.model.MedicalSpecialty;
import br.cin.ufpe.healthwatcher.util.JPAUtil;

public class MedicalSpecialtyService implements Serializable {
	
	private static final long serialVersionUID = -7422698796010710650L;

	public MedicalSpecialty find(Integer code) {
		EntityManager em = new JPAUtil().getEntityManager();
		MedicalSpecialty ms = (MedicalSpecialty) em.createNamedQuery("medicalSpecialtyByCode").setParameter("code", code).getSingleResult();
		em.close();
		return ms;
	}
	
	@SuppressWarnings("unchecked")
	public List<MedicalSpecialty> listAllMedicalSpecialties(){
		EntityManager em = new JPAUtil().getEntityManager();
		List<MedicalSpecialty> lista = em.createNamedQuery("listAllMedicalSpecialties").getResultList();
		em.close();
		return lista;
	}

}
