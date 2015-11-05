package br.cin.ufpe.healthwatcher.data.rdb;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.cin.ufpe.healthwatcher.business.employee.EmployeeLogin;
import br.cin.ufpe.healthwatcher.model.complaint.AnimalComplaint;
import br.cin.ufpe.healthwatcher.model.complaint.Situacao;
import br.cin.ufpe.healthwatcher.util.JPAUtil;

public class AnimalComplaintRepositoryRDB implements Serializable {

	private static final long serialVersionUID = 4576246922015958533L;

	private static Logger log = LoggerFactory.getLogger(AnimalComplaintRepositoryRDB.class);
	
	public void inserir(AnimalComplaint animalComplaint) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) facesContext.getExternalContext().getRequest();
		//TODO: alterar atributo para string
		EmployeeLogin employeeLogin = (EmployeeLogin) req.getSession().getAttribute("employeeLogin");
		if(employeeLogin!=null && employeeLogin.isLogged()){
			//TODO: recuperar o employee do database a partir do login
//			animalComplaint.setAtendente(employeeLogin.getEmployee());
		} else {
			animalComplaint.setAtendente(null);
		}
		log.info("Registrando animalComplaint sobre " + animalComplaint.getDescricao());
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		em.persist(animalComplaint);
		em.getTransaction().commit();
		em.close();
	}
	
	public void update(AnimalComplaint animalComplaint){
		EntityManager em = new JPAUtil().getEntityManager();
		log.info("Atualizando complaint " + animalComplaint.getDescricao());
		animalComplaint.setSituacao(Situacao.CLOSED);
		em.getTransaction().begin();
		em.merge(animalComplaint);
		em.getTransaction().commit();
		em.close();
	}	

	public AnimalComplaint find(Integer complaintCode) {
		EntityManager em = new JPAUtil().getEntityManager();
		AnimalComplaint animalComplaint = null;
		try{
			animalComplaint = (AnimalComplaint) em.createNamedQuery("animalComplaintByCode").setParameter("code", complaintCode).getSingleResult();
		} catch(NoResultException nre) {
			log.warn("AnimalComplaint " + complaintCode + " não existe.");
		}
		em.close();
		return animalComplaint;
	}
	
	@SuppressWarnings("unchecked")
	public List<AnimalComplaint> listAnimalComplaints(){
		EntityManager em = new JPAUtil().getEntityManager();
		List<AnimalComplaint> lista = em.createNamedQuery("allAnimalComplaints").getResultList();
		em.close();
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List<AnimalComplaint> listAnimalComplaintsBySituation(Situacao situacao){
		EntityManager em = new JPAUtil().getEntityManager();
		List<AnimalComplaint> lista = em.createNamedQuery("animalComplaintsBySituation")
										.setParameter("situacao", situacao)
										.getResultList();
		em.close();
		return lista;
		
	}

}
