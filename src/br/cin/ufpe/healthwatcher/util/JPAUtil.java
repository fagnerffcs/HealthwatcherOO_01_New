package br.cin.ufpe.healthwatcher.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("healthwatcher");
	
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
	
	public void close(EntityManager em){
		em.close();
	}
}