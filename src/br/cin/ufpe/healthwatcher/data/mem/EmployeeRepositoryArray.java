package br.cin.ufpe.healthwatcher.data.mem;

import lib.exceptions.ObjectNotFoundException;
import br.cin.ufpe.healthwatcher.data.IEmployeeRepository;
import br.cin.ufpe.healthwatcher.model.employee.Employee;

public class EmployeeRepositoryArray implements IEmployeeRepository {
	
	private Employee[] vector;

	private int index;

	private int iteratorIndex;

	public EmployeeRepositoryArray() {
		vector = new Employee[5000];
		Employee admin = new Employee();
		admin.setLogin("admin");
		admin.setPassword("$2a$10$trT3.R/Nfey62eczbKEnueTcIbJXW.u1ffAo/XfyLpofwNDbEB86O");
		admin.setEnable(true);
		admin.setName("Administrator");
		vector[0] = admin;
		index = 1;
	}	

	public void insert(Employee employee) {
		synchronized (this) {
			if (employee == null) {
				throw new IllegalArgumentException();
			}
			this.vector[index] = employee;
			index++;
		}
	}

	public void update(Employee employee) throws ObjectNotFoundException {
		synchronized (this) {
			int i = getIndex(employee.getLogin());
			if (i == index) {
				throw new ObjectNotFoundException("Employee not found");
			} else {
				vector[i] = employee;
			}
		}
	}

	public boolean exists(String login) {
		synchronized (this) {
			int i = getIndex(login);
			return (i != index);
		}
	}

	public Employee search(String login) throws ObjectNotFoundException {
		synchronized (this) {
			Employee response = null;
			int i = getIndex(login);
			if (i == index) {
				throw new ObjectNotFoundException("Employee not found");
			} else {
				response = vector[i];
			}
			return response;
		}
	}

	public void remove(String login) throws ObjectNotFoundException {
		synchronized (this) {
			int i = getIndex(login);
			if (i == index) {
				throw new ObjectNotFoundException("Employee not found");
			} else {
				vector[i] = vector[index - 1];
				index = index - 1;
			}
		}
	}

	private int getIndex(String login) {
		synchronized (this) {
			String temp;
			boolean flag = false;
			int i = 0;
			while ((!flag) && (i < index)) {
				temp = vector[i].getLogin();
				if (temp.equals(login)) {
					flag = true;
				} else {
					i = i + 1;
				}
			}
			return i;
		}
	}

	public void reset() {
		synchronized (this) {
			this.iteratorIndex = 0;
		}
	}

	public Object next() {
		synchronized (this) {
			if (iteratorIndex >= index) {
				return null;
			} else {
				return vector[iteratorIndex++];
			}
		}
	}

	public boolean hasNext() {
		synchronized (this) {
			return (iteratorIndex < index);
		}
	}

}
