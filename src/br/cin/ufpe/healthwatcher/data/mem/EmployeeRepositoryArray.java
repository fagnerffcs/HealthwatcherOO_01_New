package br.cin.ufpe.healthwatcher.data.mem;

import lib.exceptions.ObjectAlreadyInsertedException;
import lib.exceptions.ObjectNotFoundException;
import lib.exceptions.ObjectNotValidException;
import lib.exceptions.RepositoryException;
import br.cin.ufpe.healthwatcher.data.IEmployeeRepository;
import br.cin.ufpe.healthwatcher.model.employee.Employee;

public class EmployeeRepositoryArray implements IEmployeeRepository {

	@Override
	public void insert(Employee employee) throws ObjectNotValidException,
			ObjectAlreadyInsertedException, ObjectNotValidException,
			RepositoryException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Employee employee) throws ObjectNotValidException,
			ObjectNotFoundException, ObjectNotValidException,
			RepositoryException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean exists(String login) throws RepositoryException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void remove(String login) throws ObjectNotFoundException,
			RepositoryException {
		// TODO Auto-generated method stub

	}

	@Override
	public Employee search(String login) throws ObjectNotFoundException,
			RepositoryException {
		// TODO Auto-generated method stub
		return null;
	}

}
