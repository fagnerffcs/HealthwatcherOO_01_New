package br.cin.ufpe.healthwatcher.business;

import java.util.List;

import lib.exceptions.InsertEntryException;
import lib.exceptions.ObjectAlreadyInsertedException;
import lib.exceptions.ObjectNotFoundException;
import lib.exceptions.ObjectNotValidException;
import lib.exceptions.RepositoryException;
import lib.exceptions.TransactionException;
import lib.exceptions.UpdateEntryException;
import br.cin.ufpe.healthwatcher.model.complaint.Complaint;
import br.cin.ufpe.healthwatcher.model.complaint.DiseaseType;
import br.cin.ufpe.healthwatcher.model.employee.Employee;
import br.cin.ufpe.healthwatcher.model.healthguide.HealthUnit;
import br.cin.ufpe.healthwatcher.model.healthguide.MedicalSpecialty;

public interface IFacade {

	public void updateComplaint(Complaint q) throws TransactionException, RepositoryException, ObjectNotFoundException, ObjectNotValidException;

	public List<MedicalSpecialty> searchSpecialitiesByHealthUnit(int code) throws ObjectNotFoundException, RepositoryException, TransactionException;

	public Complaint searchComplaint(int code) throws RepositoryException, ObjectNotFoundException, TransactionException;

	public DiseaseType searchDiseaseType(int code) throws RepositoryException, ObjectNotFoundException, TransactionException;

	public List<HealthUnit> searchHealthUnitsBySpeciality(int code)	throws ObjectNotFoundException, RepositoryException, TransactionException;

	public List<MedicalSpecialty> getSpecialityList() throws RepositoryException, ObjectNotFoundException, TransactionException;

	public List<DiseaseType> getDiseaseTypeList() throws RepositoryException, ObjectNotFoundException, TransactionException;

	public List<HealthUnit> getHealthUnitList() throws RepositoryException, ObjectNotFoundException, TransactionException;

	public List<HealthUnit> getPartialHealthUnitList() throws RepositoryException, ObjectNotFoundException, TransactionException;

	public int insertComplaint(Complaint complaint) throws RepositoryException,	ObjectAlreadyInsertedException, TransactionException, ObjectNotValidException;

	public void updateHealthUnit(HealthUnit unit) throws RepositoryException, TransactionException, ObjectNotFoundException;

	public List<Complaint> getComplaintList() throws ObjectNotFoundException, TransactionException;

	public void insert(Employee e) throws ObjectAlreadyInsertedException, ObjectNotValidException, InsertEntryException, TransactionException;

	public void updateEmployee(Employee e) throws TransactionException, RepositoryException, ObjectNotFoundException, ObjectNotValidException, UpdateEntryException;

	public Employee searchEmployee(String login) throws TransactionException, RepositoryException, ObjectNotFoundException, ObjectNotValidException, UpdateEntryException;

	public HealthUnit searchHealthUnit(int healthUnitCode)	throws ObjectNotFoundException, RepositoryException;

}
