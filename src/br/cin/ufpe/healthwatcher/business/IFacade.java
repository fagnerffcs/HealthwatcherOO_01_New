package br.cin.ufpe.healthwatcher.business;

import lib.exceptions.CommunicationException;
import lib.exceptions.InsertEntryException;
import lib.exceptions.ObjectAlreadyInsertedException;
import lib.exceptions.ObjectNotFoundException;
import lib.exceptions.ObjectNotValidException;
import lib.exceptions.RepositoryException;
import lib.exceptions.TransactionException;
import lib.exceptions.UpdateEntryException;
import lib.util.IteratorDsk;
import br.cin.ufpe.healthwatcher.model.complaint.Complaint;
import br.cin.ufpe.healthwatcher.model.complaint.DiseaseType;
import br.cin.ufpe.healthwatcher.model.employee.Employee;
import br.cin.ufpe.healthwatcher.model.healthguide.HealthUnit;

public interface IFacade {

	public void updateComplaint(Complaint q) throws TransactionException,
			RepositoryException, ObjectNotFoundException,
			ObjectNotValidException, CommunicationException,
			java.rmi.RemoteException;

	public IteratorDsk searchSpecialitiesByHealthUnit(int code)
			throws ObjectNotFoundException, RepositoryException,
			CommunicationException, TransactionException,
			java.rmi.RemoteException;

	public Complaint searchComplaint(int code) throws RepositoryException,
			ObjectNotFoundException, CommunicationException,
			TransactionException;

	public DiseaseType searchDiseaseType(int code) throws RepositoryException,
			ObjectNotFoundException, CommunicationException,
			TransactionException;

	public IteratorDsk searchHealthUnitsBySpeciality(int code)
			throws ObjectNotFoundException, RepositoryException,
			TransactionException, CommunicationException,
			java.rmi.RemoteException;

	public IteratorDsk getSpecialityList() throws RepositoryException,
			ObjectNotFoundException, CommunicationException,
			TransactionException;

	public IteratorDsk getDiseaseTypeList() throws RepositoryException,
			ObjectNotFoundException, CommunicationException,
			TransactionException;

	public IteratorDsk getHealthUnitList() throws RepositoryException,
			ObjectNotFoundException, CommunicationException,
			TransactionException;

	public IteratorDsk getPartialHealthUnitList() throws RepositoryException,
			ObjectNotFoundException, CommunicationException,
			TransactionException;

	public int insertComplaint(Complaint complaint) throws RepositoryException,
			ObjectAlreadyInsertedException, CommunicationException,
			TransactionException, ObjectNotValidException,
			java.rmi.RemoteException;

	public void updateHealthUnit(HealthUnit unit) throws RepositoryException,
			TransactionException, ObjectNotFoundException,
			java.rmi.RemoteException;

	public IteratorDsk getComplaintList() throws ObjectNotFoundException,
			TransactionException;

	public void insert(Employee e) throws ObjectAlreadyInsertedException,
			ObjectNotValidException, InsertEntryException, TransactionException;

	public void updateEmployee(Employee e) throws TransactionException,
			RepositoryException, ObjectNotFoundException,
			ObjectNotValidException, UpdateEntryException,
			CommunicationException;

	public Employee searchEmployee(String login) throws TransactionException,
			RepositoryException, ObjectNotFoundException,
			ObjectNotValidException, UpdateEntryException,
			CommunicationException;

	public HealthUnit searchHealthUnit(int healthUnitCode)
			throws ObjectNotFoundException, RepositoryException,
			java.rmi.RemoteException;

}
