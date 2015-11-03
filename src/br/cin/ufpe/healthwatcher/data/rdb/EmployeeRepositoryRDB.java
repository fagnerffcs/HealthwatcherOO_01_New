package br.cin.ufpe.healthwatcher.data.rdb;

import java.io.Serializable;

import javax.persistence.EntityManager;

import lib.exceptions.ObjectAlreadyInsertedException;
import lib.exceptions.ObjectNotFoundException;
import lib.exceptions.ObjectNotValidException;
import lib.exceptions.RepositoryException;
import lib.persistence.IPersistenceMechanism;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.cin.ufpe.healthwatcher.data.IEmployeeRepository;
import br.cin.ufpe.healthwatcher.model.employee.Employee;
import br.cin.ufpe.healthwatcher.util.JPAUtil;

public class EmployeeRepositoryRDB implements Serializable, IEmployeeRepository {

	private static final long serialVersionUID = -1140679191313821439L;

	private static Logger log = LoggerFactory.getLogger(EmployeeRepositoryRDB.class);
	
	private IPersistenceMechanism pm;
	
	public EmployeeRepositoryRDB(){
		
	}

	public EmployeeRepositoryRDB(IPersistenceMechanism pm) {
		this.pm = pm;
	}	
	
	public Employee find(String login) {
		EntityManager em = new JPAUtil().getEntityManager();
		Employee e = em.find(Employee.class, login);
		em.close();
		return e;
	}
	
	public void update(Employee employee) {
		log.info("Atualizando " + employee.getName());
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		em.merge(employee);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public boolean exists(String login) throws RepositoryException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void remove(String login) throws ObjectNotFoundException, RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Employee search(String login) throws ObjectNotFoundException, RepositoryException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Employee employee) throws ObjectNotValidException,
			ObjectAlreadyInsertedException, ObjectNotValidException,
			RepositoryException {
		EntityManager em = new JPAUtil().getEntityManager();
		try{
			log.info("Registrando employee " + employee.getName());
			em.getTransaction().begin();
			employee.setEnable(true);
			em.persist(employee);
			em.getTransaction().commit();
		} catch (ConstraintViolationException cve) {
			log.error("Erro ao inserir employee " + employee.getLogin());
			throw new ObjectNotValidException();
		} finally {
			em.close();
		}
		
	}

}	
