package br.cin.ufpe.healthwatcher.data.rdb;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import lib.persistence.IPersistenceMechanism;
import lib.persistence.PersistenceMechanism;
import br.cin.ufpe.healthwatcher.model.healthguide.MedicalSpecialty;

public class SpecialtyRepositoryRDB implements Serializable {
	
	private static final long serialVersionUID = -7422698796010710650L;
	
	private IPersistenceMechanism mp;
	
	public SpecialtyRepositoryRDB(PersistenceMechanism mp){
		this.mp = mp;
	}

	public MedicalSpecialty find(Integer code) {
		EntityManager em = null;
		try{
			em = (EntityManager) this.mp.getCommunicationChannel();
			MedicalSpecialty ms = (MedicalSpecialty) em.createNamedQuery("medicalSpecialtyByCode").setParameter("code", code).getSingleResult();
			return ms;
		}catch(Exception e){
			
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<MedicalSpecialty> listAllMedicalSpecialties(){
		EntityManager em = null;
		List<MedicalSpecialty> lista = null;
		try{
			em = (EntityManager) this.mp.getCommunicationChannel();
			lista = em.createNamedQuery("listAllMedicalSpecialties").getResultList();
		} catch(Exception e){
			
		}
		return lista;
	}

}
