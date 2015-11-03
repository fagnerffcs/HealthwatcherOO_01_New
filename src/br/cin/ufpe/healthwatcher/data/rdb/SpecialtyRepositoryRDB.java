package br.cin.ufpe.healthwatcher.data.rdb;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import br.cin.ufpe.healthwatcher.model.healthguide.MedicalSpecialty;
import br.cin.ufpe.healthwatcher.util.JPAUtil;

public class SpecialtyRepositoryRDB implements Serializable {
	
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
