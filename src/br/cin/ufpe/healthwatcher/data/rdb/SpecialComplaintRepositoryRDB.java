package br.cin.ufpe.healthwatcher.data.rdb;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;

import lib.exceptions.ObjectAlreadyInsertedException;
import lib.exceptions.ObjectNotFoundException;
import lib.exceptions.ObjectNotValidException;
import lib.exceptions.PersistenceMechanismException;
import lib.exceptions.RepositoryException;
import lib.persistence.IPersistenceMechanism;
import lib.persistence.PersistenceMechanism;
import lib.util.IteratorDsk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.cin.ufpe.healthwatcher.business.employee.EmployeeLogin;
import br.cin.ufpe.healthwatcher.data.IComplaintRepository;
import br.cin.ufpe.healthwatcher.model.complaint.Complaint;
import br.cin.ufpe.healthwatcher.model.complaint.Situacao;
import br.cin.ufpe.healthwatcher.model.complaint.SpecialComplaint;
import br.cin.ufpe.healthwatcher.util.JPAUtil;

public class SpecialComplaintRepositoryRDB implements Serializable, IComplaintRepository {
	
	private static final long serialVersionUID = -1558531836712513980L;

	private static Logger log = LoggerFactory.getLogger(SpecialComplaintRepositoryRDB.class);
	private IPersistenceMechanism mp;
	
	public SpecialComplaintRepositoryRDB(PersistenceMechanism mp){
		this.mp = mp;
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

	@Override
	public int insert(Complaint complaint) throws ObjectNotValidException,
			ObjectAlreadyInsertedException, ObjectNotValidException,
			RepositoryException {
		SpecialComplaint specialComplaint = (SpecialComplaint) complaint;
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) facesContext.getExternalContext().getRequest();
//		HealthWatcherFacade fachada = (HealthWatcherFacade) req.getSession().getAttribute("facade");
		//TODO: string
		EmployeeLogin employeeLogin = (EmployeeLogin) req.getSession().getAttribute("employeeLogin");
		if(employeeLogin!=null && employeeLogin.isLogged()){
			//TODO: buscar o objeto no banco
//			specialComplaint.setAtendente(employeeLogin.getEmployee());
		} else {
			specialComplaint.setAtendente(null);
		}
		
		log.info("Registrando foodComplaint sobre " + specialComplaint.getDescricao());
		EntityManager em;
		try {
			em = (EntityManager) this.mp.getCommunicationChannel();
			em.persist(specialComplaint);
		} catch (PersistenceMechanismException e) {
			e.printStackTrace();
		}
		return specialComplaint.getCodigo();
	}

	@Override
	public void update(Complaint complaint) throws ObjectNotValidException,
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
	public void remove(int code) throws ObjectNotFoundException,
			RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Complaint search(int complaint) throws ObjectNotFoundException,
			RepositoryException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IteratorDsk getComplaintList() throws ObjectNotFoundException,
			RepositoryException {
		// TODO Auto-generated method stub
		return null;
	}

}
