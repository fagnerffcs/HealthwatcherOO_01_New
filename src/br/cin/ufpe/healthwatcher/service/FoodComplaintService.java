package br.cin.ufpe.healthwatcher.service;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.cin.ufpe.healthwatcher.business.EmployeeLogin;
import br.cin.ufpe.healthwatcher.facade.Facade;
import br.cin.ufpe.healthwatcher.model.complaint.FoodComplaint;
import br.cin.ufpe.healthwatcher.model.complaint.Situacao;
import br.cin.ufpe.healthwatcher.util.JPAUtil;

public class FoodComplaintService implements Serializable {

	private static final long serialVersionUID = 353591488184945392L;

	private static Logger log = LoggerFactory.getLogger(FoodComplaintService.class);
	
	public void update(FoodComplaint foodComplaint){
		EntityManager em = new JPAUtil().getEntityManager();
		log.info("Atualizando complaint " + foodComplaint.getDescricao());
		foodComplaint.setSituacao(Situacao.CLOSED);
		em.getTransaction().begin();
		em.merge(foodComplaint);
		em.getTransaction().commit();
		em.close();
	}
	
	public void inserir(FoodComplaint foodComplaint){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) facesContext.getExternalContext().getRequest();
		Facade fachada = (Facade) req.getSession().getAttribute("facade");
		EmployeeLogin employeeLogin = fachada.getEmployeeLogin();
		if(employeeLogin!=null && employeeLogin.isLogged()){
			foodComplaint.setAtendente(employeeLogin.getEmployee());
		} else {
			foodComplaint.setAtendente(null);
		}
		
		log.info("Registrando foodComplaint sobre " + foodComplaint.getDescricao());
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		em.persist(foodComplaint);
		em.getTransaction().commit();
		em.close();
		
	}

	public FoodComplaint find(Integer complaintCode) {
		EntityManager em = new JPAUtil().getEntityManager();
		FoodComplaint foodComplaint = null;
		try{
			foodComplaint = (FoodComplaint) em.createNamedQuery("foodComplaintByCode").setParameter("code", complaintCode).getSingleResult();
		} catch(NoResultException nre) {
			log.warn("FoodComplaint " + complaintCode + " não existe.");
		}
		em.close();
		return foodComplaint;
	}

	@SuppressWarnings("unchecked")
	public List<FoodComplaint> listFoodComplaints() {
		EntityManager em = new JPAUtil().getEntityManager();
		List<FoodComplaint> lista = em.createNamedQuery("allFoodComplaints").getResultList();
		em.close();
		return lista;
	}

	@SuppressWarnings("unchecked")
	public List<FoodComplaint> listFoodComplaintsBySituation(Situacao s) {
		EntityManager em = new JPAUtil().getEntityManager();
		List<FoodComplaint> lista = em.createNamedQuery("allFoodComplaintsBySituation")
									  .setParameter("situacao", s)
									  .getResultList();
		em.close();
		return lista;
	}	
	
}
