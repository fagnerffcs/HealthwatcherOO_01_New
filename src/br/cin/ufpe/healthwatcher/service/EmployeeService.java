package br.cin.ufpe.healthwatcher.service;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.cin.ufpe.healthwatcher.exception.EmployeeAlreadyExistsException;
import br.cin.ufpe.healthwatcher.model.Employee;
import br.cin.ufpe.healthwatcher.util.JPAUtil;

public class EmployeeService implements Serializable {

	private static final long serialVersionUID = -1140679191313821439L;

	private static Logger log = LoggerFactory.getLogger(EmployeeService.class);
	
	public Employee find(String login) {
		EntityManager em = new JPAUtil().getEntityManager();
		Employee e = em.find(Employee.class, login);
		em.close();
		return e;
	}
	
	public void insert(Employee employee) throws EmployeeAlreadyExistsException {
		EntityManager em = new JPAUtil().getEntityManager();
		try{
			log.info("Registrando employee " + employee.getName());
			em.getTransaction().begin();
			employee.setEnable(true);
			em.persist(employee);
			em.getTransaction().commit();
		} catch (ConstraintViolationException cve) {
			log.error("Erro ao inserir employee " + employee.getLogin());
			throw new EmployeeAlreadyExistsException(employee.getLogin());
		} finally {
			em.close();
		}
	}
	
	public void update(Employee employee) {
		log.info("Atualizando " + employee.getName());
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		em.merge(employee);
		em.getTransaction().commit();
		em.close();
	}

}	
