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
import br.cin.ufpe.healthwatcher.model.complaint.AnimalComplaint;
import br.cin.ufpe.healthwatcher.model.complaint.Complaint;
import br.cin.ufpe.healthwatcher.model.complaint.Situacao;
import br.cin.ufpe.healthwatcher.util.JPAUtil;

public class AnimalComplaintRepositoryRDB implements Serializable, IComplaintRepository {

	private static final long serialVersionUID = 4576246922015958533L;

	private static Logger log = LoggerFactory.getLogger(AnimalComplaintRepositoryRDB.class);
	private IPersistenceMechanism mp;
	
	public AnimalComplaintRepositoryRDB(PersistenceMechanism mp){
		this.mp = mp;
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

	@Override
	public int insert(Complaint complaint) throws ObjectNotValidException,
			ObjectAlreadyInsertedException, ObjectNotValidException,
			RepositoryException {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		AnimalComplaint animalComplaint = (AnimalComplaint) complaint;
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
		EntityManager em;
		try {
			em = (EntityManager) this.mp.getCommunicationChannel();
			em.persist(animalComplaint);			
		} catch (PersistenceMechanismException e) {
			e.printStackTrace();
		}
		return animalComplaint.getCodigo();
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
