package br.cin.ufpe.healthwatcher.data.rdb;

import java.io.Serializable;

import javax.persistence.EntityManager;

import lib.exceptions.ObjectAlreadyInsertedException;
import lib.exceptions.ObjectNotFoundException;
import lib.exceptions.ObjectNotValidException;
import lib.exceptions.PersistenceMechanismException;
import lib.exceptions.RepositoryException;
import lib.persistence.IPersistenceMechanism;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.cin.ufpe.healthwatcher.data.IEmployeeRepository;
import br.cin.ufpe.healthwatcher.model.employee.Employee;

public class EmployeeRepositoryRDB implements Serializable, IEmployeeRepository {

	private static final long serialVersionUID = -1140679191313821439L;

	private static Logger log = LoggerFactory.getLogger(EmployeeRepositoryRDB.class);
	
	private IPersistenceMechanism mp;
	
	public EmployeeRepositoryRDB(){
		
	}

	public EmployeeRepositoryRDB(IPersistenceMechanism mp) {
		this.mp = mp;
	}	
	
	@Override
	public boolean exists(String login) throws RepositoryException {
		try{
			return search(login)!=null;
		} catch(Exception e) {
			
		}
		return false;
	}

	@Override
	public void remove(String login) throws ObjectNotFoundException, RepositoryException {
		EntityManager em;
		Employee employee = null; 
		try{
			employee = search(login);
			log.info("Removendo employee " + employee.getName());
			em = (EntityManager) this.mp.getCommunicationChannel();
			em.remove(employee);
		} catch (ConstraintViolationException | PersistenceMechanismException cve) {
			log.error("Erro ao excluir employee " + login);
		}		
	}

	@Override
	public Employee search(String login) throws ObjectNotFoundException, RepositoryException {
		EntityManager em;
		try{
			em = (EntityManager) this.mp.getCommunicationChannel();
			Employee e = em.find(Employee.class, login);
			return e;
		}catch(Exception e) {
			
		}
		return null;
	}

	@Override
	public void insert(Employee employee) throws ObjectNotValidException,
			ObjectAlreadyInsertedException, ObjectNotValidException,
			RepositoryException {
		EntityManager em;
		try{
			log.info("Registrando employee " + employee.getName());
			em = (EntityManager) this.mp.getCommunicationChannel();
			employee.setEnable(true);
			em.persist(employee);
		} catch (ConstraintViolationException | PersistenceMechanismException cve) {
			log.error("Erro ao inserir employee " + employee.getLogin());
			throw new ObjectNotValidException();
		}
		
	}

	@Override
	public void update(Employee employee) throws ObjectNotValidException,
			ObjectNotFoundException, ObjectNotValidException,
			RepositoryException {
		EntityManager em;
		try{
			log.info("Atualizando employee " + employee.getName());
			em = (EntityManager) this.mp.getCommunicationChannel();
			employee.setEnable(true);
			em.merge(employee);
		} catch (ConstraintViolationException | PersistenceMechanismException cve) {
			log.error("Erro ao inserir employee " + employee.getLogin());
			throw new ObjectNotValidException();
		}
	}

}	
