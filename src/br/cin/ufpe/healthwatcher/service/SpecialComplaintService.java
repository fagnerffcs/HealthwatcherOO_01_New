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
import br.cin.ufpe.healthwatcher.model.complaint.Situacao;
import br.cin.ufpe.healthwatcher.model.complaint.SpecialComplaint;
import br.cin.ufpe.healthwatcher.util.JPAUtil;

public class SpecialComplaintService implements Serializable {
	
	private static final long serialVersionUID = -1558531836712513980L;

	private static Logger log = LoggerFactory.getLogger(SpecialComplaintService.class);
	
	public void inserir(SpecialComplaint specialComplaint){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) facesContext.getExternalContext().getRequest();
		Facade fachada = (Facade) req.getSession().getAttribute("facade");
		EmployeeLogin employeeLogin = fachada.getEmployeeLogin();
		if(employeeLogin!=null && employeeLogin.isLogged()){
			specialComplaint.setAtendente(employeeLogin.getEmployee());
		} else {
			specialComplaint.setAtendente(null);
		}
		log.info("Registrando animalComplaint sobre " + specialComplaint.getDescricao());
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		em.persist(specialComplaint);
		em.getTransaction().commit();
		em.close();
	}
	
	public void update(SpecialComplaint specialComplaint){
		log.info("Atualizando complaint " + specialComplaint.getDescricao());
		specialComplaint.setSituacao(Situacao.CLOSED);
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		em.merge(specialComplaint);
		em.getTransaction().commit();
		em.close();
	}	

	public SpecialComplaint find(Integer complaintCode) {
		EntityManager em = new JPAUtil().getEntityManager();
		SpecialComplaint specialComplaint = null;
		try{
			specialComplaint = (SpecialComplaint) em.createNamedQuery("specialComplaintByCode").setParameter("code", complaintCode).getSingleResult();
		} catch(NoResultException nre) {
			log.warn("SpecialComplaint " + complaintCode + " não existe.");
		}
		em.close();
		return specialComplaint;
	}

	@SuppressWarnings("unchecked")
	public List<SpecialComplaint> listSpecialComplaints() {
		EntityManager em = new JPAUtil().getEntityManager();
		List<SpecialComplaint> lista = em.createNamedQuery("allSpecialComplaints").getResultList();
		em.close();
		return lista;
	}

	@SuppressWarnings("unchecked")
	public List<SpecialComplaint> listSpecialComplaintsBySituation(Situacao s) {
		EntityManager em = new JPAUtil().getEntityManager();
		List<SpecialComplaint> lista = em.createNamedQuery("specialComplaintsBySituation")
										 .setParameter("situacao", s)
										 .getResultList();
		em.close();
		return lista;
	}

}
