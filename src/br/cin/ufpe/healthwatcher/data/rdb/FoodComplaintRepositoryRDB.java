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
import br.cin.ufpe.healthwatcher.model.complaint.FoodComplaint;
import br.cin.ufpe.healthwatcher.model.complaint.Situacao;
import br.cin.ufpe.healthwatcher.util.JPAUtil;

public class FoodComplaintRepositoryRDB implements Serializable, IComplaintRepository {

	private static final long serialVersionUID = 353591488184945392L;

	private static Logger log = LoggerFactory.getLogger(FoodComplaintRepositoryRDB.class);
	private IPersistenceMechanism mp;
	
	public FoodComplaintRepositoryRDB(PersistenceMechanism mp){
		this.mp = mp;
	}
	
	public void update(FoodComplaint foodComplaint){
		EntityManager em = new JPAUtil().getEntityManager();
		log.info("Atualizando complaint " + foodComplaint.getDescricao());
		foodComplaint.setSituacao(Situacao.CLOSED);
		em.getTransaction().begin();
		em.merge(foodComplaint);
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

	@Override
	public int insert(Complaint complaint) throws ObjectNotValidException,
			ObjectAlreadyInsertedException, ObjectNotValidException,
			RepositoryException {
		FoodComplaint foodComplaint = (FoodComplaint) complaint;
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) facesContext.getExternalContext().getRequest();
//		HealthWatcherFacade fachada = (HealthWatcherFacade) req.getSession().getAttribute("facade");
		//TODO: string de login
		EmployeeLogin employeeLogin = (EmployeeLogin) req.getSession().getAttribute("employeeLogin");
		if(employeeLogin!=null && employeeLogin.isLogged()){
			//TODO: buscar no banco o objeto
			//foodComplaint.setAtendente(employeeLogin.getEmployee());
		} else {
			foodComplaint.setAtendente(null);
		}
		
		log.info("Registrando foodComplaint sobre " + foodComplaint.getDescricao());
		EntityManager em;
		try {
			em = (EntityManager) this.mp.getCommunicationChannel();
			em.persist(foodComplaint);
		} catch (PersistenceMechanismException e) {
			e.printStackTrace();
		}

		return foodComplaint.getCodigo();
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
