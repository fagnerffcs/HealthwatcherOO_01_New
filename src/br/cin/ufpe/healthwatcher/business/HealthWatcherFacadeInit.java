package br.cin.ufpe.healthwatcher.business;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import lib.exceptions.ObjectAlreadyInsertedException;
import lib.exceptions.ObjectNotFoundException;
import lib.exceptions.ObjectNotValidException;
import lib.exceptions.RepositoryException;
import lib.exceptions.TransactionException;
import lib.persistence.IPersistenceMechanism;
import lib.persistence.PersistenceMechanism;
import lib.util.IteratorDsk;
import br.cin.ufpe.healthwatcher.Constants;
import br.cin.ufpe.healthwatcher.business.complaint.AnimalComplaintRecord;
import br.cin.ufpe.healthwatcher.business.complaint.ComplaintRecord;
import br.cin.ufpe.healthwatcher.business.complaint.DiseaseRecord;
import br.cin.ufpe.healthwatcher.business.complaint.FoodComplaintRecord;
import br.cin.ufpe.healthwatcher.business.complaint.SearchComplaintRecord;
import br.cin.ufpe.healthwatcher.business.complaint.SpecialComplaintRecord;
import br.cin.ufpe.healthwatcher.business.employee.EmployeeLogin;
import br.cin.ufpe.healthwatcher.business.employee.EmployeeRecord;
import br.cin.ufpe.healthwatcher.business.healthguide.HealthUnitRecord;
import br.cin.ufpe.healthwatcher.business.healthguide.MedicalSpecialtyRecord;
import br.cin.ufpe.healthwatcher.data.mem.ComplaintRepositoryArray;
import br.cin.ufpe.healthwatcher.data.mem.DiseaseTypeRepositoryArray;
import br.cin.ufpe.healthwatcher.data.mem.EmployeeRepositoryArray;
import br.cin.ufpe.healthwatcher.data.mem.HealthUnitRepositoryArray;
import br.cin.ufpe.healthwatcher.data.mem.SpecialityRepositoryArray;
import br.cin.ufpe.healthwatcher.data.rdb.ComplaintRepositoryRDB;
import br.cin.ufpe.healthwatcher.data.rdb.DiseaseTypeRepositoryRDB;
import br.cin.ufpe.healthwatcher.data.rdb.EmployeeRepositoryRDB;
import br.cin.ufpe.healthwatcher.data.rdb.HealthUnitRepositoryRDB;
import br.cin.ufpe.healthwatcher.data.rdb.SpecialityRepositoryRDB;
import br.cin.ufpe.healthwatcher.model.complaint.Complaint;
import br.cin.ufpe.healthwatcher.model.complaint.DiseaseType;
import br.cin.ufpe.healthwatcher.model.employee.Employee;
import br.cin.ufpe.healthwatcher.model.healthguide.HealthUnit;

@ManagedBean(name = "fCid")
@ApplicationScoped
public class HealthWatcherFacadeInit implements Serializable {

	private static final long serialVersionUID = -2205283915107762308L;

	private AnimalComplaintRecord animalComplaintRecord;
	private FoodComplaintRecord foodComplaintRecord;
	private SpecialComplaintRecord specialComplaintRecord;
	private ComplaintRecord complaintRecord;
	private EmployeeLogin employeeLogin;
	private EmployeeRecord employeeRecord;
	private HealthUnitRecord healthUnitRecord;
	private MedicalSpecialtyRecord specialityRecord;
	private SearchComplaintRecord searchComplaintRecord;
	private DiseaseRecord diseaseRecord;

	public AnimalComplaintRecord getAnimalComplaintRecord() {
		return animalComplaintRecord;
	}

	public FoodComplaintRecord getFoodComplaintRecord() {
		return foodComplaintRecord;
	}

	public SpecialComplaintRecord getSpecialComplaintRecord() {
		return specialComplaintRecord;
	}

	public ComplaintRecord getComplaintRecord() {
		return complaintRecord;
	}

	public EmployeeLogin getEmployeeLogin() {
		return employeeLogin;
	}

	public EmployeeRecord getEmployeeRecord() {
		return employeeRecord;
	}

	public HealthUnitRecord getHealthUnitRecord() {
		return healthUnitRecord;
	}

	public MedicalSpecialtyRecord getSpecialityRecord() {
		return specialityRecord;
	}

	public SearchComplaintRecord getSearchComplaintRecord() {
		return searchComplaintRecord;
	}

	public DiseaseRecord getDiseaseRecord() {
		return diseaseRecord;
	}

	public HealthWatcherFacadeInit() {
		this.employeeLogin = new EmployeeLogin();

		this.foodComplaintRecord = new FoodComplaintRecord();
		this.animalComplaintRecord = new AnimalComplaintRecord();
		this.specialComplaintRecord = new SpecialComplaintRecord();

		this.complaintRecord = new ComplaintRecord(
				new ComplaintRepositoryArray());
		this.searchComplaintRecord = new SearchComplaintRecord();
		if (Constants.isPersistent()) {
			this.complaintRecord = new ComplaintRecord(
					new ComplaintRepositoryRDB(
							(PersistenceMechanism) HealthWatcherFacade.getPm()));
		}
		this.healthUnitRecord = new HealthUnitRecord(
				new HealthUnitRepositoryArray());
		if (Constants.isPersistent()) {
			this.healthUnitRecord = new HealthUnitRecord(
					new HealthUnitRepositoryRDB(
							(PersistenceMechanism) HealthWatcherFacade.getPm()));
		}

		this.specialityRecord = new MedicalSpecialtyRecord(
				new SpecialityRepositoryArray());
		if (Constants.isPersistent()) {
			this.specialityRecord = new MedicalSpecialtyRecord(
					new SpecialityRepositoryRDB(
							(PersistenceMechanism) HealthWatcherFacade.getPm()));
		}

		DiseaseTypeRepositoryArray tp = new DiseaseTypeRepositoryArray();
		this.diseaseRecord = new DiseaseRecord(tp);
		if (Constants.isPersistent()) {
			this.diseaseRecord = new DiseaseRecord(
					new DiseaseTypeRepositoryRDB(
							(PersistenceMechanism) HealthWatcherFacade.getPm()));
		}

		EmployeeRepositoryArray er = new EmployeeRepositoryArray();
		this.employeeRecord = new EmployeeRecord(er);
		if (Constants.isPersistent()) {
			this.employeeRecord = new EmployeeRecord(new EmployeeRepositoryRDB(
					(PersistenceMechanism) HealthWatcherFacade.getPm()));
		}
	}

	public void updateHealthUnit(HealthUnit unit) throws RepositoryException,
			ObjectNotFoundException, TransactionException {
		try {
			getPm().beginTransaction();
			healthUnitRecord.update(unit);
			getPm().commitTransaction();
		} catch (RepositoryException e) {
			getPm().rollbackTransaction();
			throw e;
		} catch (ObjectNotFoundException e) {
			getPm().rollbackTransaction();
			throw e;
		} catch (TransactionException e) {
			getPm().rollbackTransaction();
			throw e;
		} catch (Exception e) {
			getPm().rollbackTransaction();
		}
	}

	public IPersistenceMechanism getPm() {
		return HealthWatcherFacade.getPm();
	}

	public void updateComplaint(Complaint complaint)
			throws TransactionException, RepositoryException,
			ObjectNotFoundException, ObjectNotValidException {
		try {
			getPm().beginTransaction();
			complaintRecord.update(complaint);
			getPm().commitTransaction();
		} catch (RepositoryException e) {
			getPm().rollbackTransaction();
			throw e;
		} catch (ObjectNotFoundException e) {
			getPm().rollbackTransaction();
			throw e;
		} catch (TransactionException e) {
			getPm().rollbackTransaction();
			throw e;
		} catch (Exception e) {
			getPm().rollbackTransaction();
		}
	}

	public IteratorDsk searchSpecialitiesByHealthUnit(int code)
			throws ObjectNotFoundException, RepositoryException,
			TransactionException {
		IteratorDsk lista = null;
		try {
			getPm().beginTransaction();
			lista = healthUnitRecord.searchSpecialitiesByHealthUnit(code);
			getPm().commitTransaction();
		} catch (RepositoryException e) {
			getPm().rollbackTransaction();
			throw e;
		} catch (TransactionException e) {
			getPm().rollbackTransaction();
			throw e;
		} catch (ObjectNotFoundException e) {
			getPm().rollbackTransaction();
			throw e;
		}
		return lista;
	}

	public Complaint searchComplaint(int code) throws RepositoryException,
			ObjectNotFoundException, TransactionException {
		Complaint q = null;
		try {
			getPm().beginTransaction();
			q = this.complaintRecord.searchComplaint(code);
			getPm().commitTransaction();
		} catch (RepositoryException e) {
			getPm().rollbackTransaction();
			throw e;
		} catch (TransactionException e) {
			getPm().rollbackTransaction();
			throw e;
		} catch (ObjectNotFoundException e) {
			getPm().rollbackTransaction();
			throw e;
		} catch (Exception e) {
			getPm().rollbackTransaction();
		}
		return q;
	}

	public DiseaseType searchDiseaseType(int code) throws RepositoryException,
			ObjectNotFoundException, TransactionException {
		DiseaseType tp = null;
		try {
			getPm().beginTransaction();
			tp = this.diseaseRecord.searchDiseaseType(code);
			getPm().commitTransaction();
		} catch (RepositoryException e) {
			getPm().rollbackTransaction();
			throw e;
		} catch (TransactionException e) {
			getPm().rollbackTransaction();
			throw e;
		} catch (ObjectNotFoundException e) {
			getPm().rollbackTransaction();
			throw e;
		} catch (Exception e) {
			getPm().rollbackTransaction();
		}
		return tp;
	}

	public IteratorDsk searchHealthUnitsBySpeciality(int code)
			throws ObjectNotFoundException, RepositoryException,
			TransactionException {
		IteratorDsk lista = null;
		try {
			getPm().beginTransaction();
			lista = healthUnitRecord.searchHealthUnitsBySpeciality(code);
			getPm().commitTransaction();
		} catch (RepositoryException e) {
			getPm().rollbackTransaction();
			throw e;
		} catch (TransactionException e) {
			getPm().rollbackTransaction();
			throw e;
		} catch (ObjectNotFoundException e) {
			getPm().rollbackTransaction();
			throw e;
		} catch (Exception e) {
			getPm().rollbackTransaction();
		}
		return lista;
	}

	public IteratorDsk getSpecialityList() throws RepositoryException,
			ObjectNotFoundException, TransactionException {
		IteratorDsk iterator = null;
		try {
			getPm().beginTransaction();
			iterator = specialityRecord.getListaEspecialidade();
			getPm().commitTransaction();
		} catch (RepositoryException e) {
			getPm().rollbackTransaction();
			throw e;
		} catch (TransactionException e) {
			getPm().rollbackTransaction();
			throw e;
		} catch (ObjectNotFoundException e) {
			getPm().rollbackTransaction();
			throw e;
		} catch (Exception e) {
			getPm().rollbackTransaction();
		}
		return iterator;
	}

	public IteratorDsk getDiseaseTypeList() throws RepositoryException,
			ObjectNotFoundException, TransactionException {
		IteratorDsk iterator = null;
		try {
			getPm().beginTransaction();
			iterator = this.diseaseRecord.getDiseaseTypeList();
			getPm().commitTransaction();
		} catch (RepositoryException e) {
			getPm().rollbackTransaction();
			throw e;
		} catch (TransactionException e) {
			getPm().rollbackTransaction();
			throw e;
		} catch (ObjectNotFoundException e) {
			getPm().rollbackTransaction();
			throw e;
		} catch (Exception e) {
			getPm().rollbackTransaction();
		}
		return iterator;
	}

	public HealthUnit searchHealthUnit(int healthUnitCode)
			throws ObjectNotFoundException, RepositoryException {
		return healthUnitRecord.search(healthUnitCode);
	}

	public IteratorDsk getHealthUnitList() throws RepositoryException,
			ObjectNotFoundException, TransactionException {
		IteratorDsk lista = null;
		try {
			getPm().beginTransaction();
			lista = healthUnitRecord.getHealthUnitList();
			getPm().commitTransaction();
		} catch (RepositoryException e) {
			getPm().rollbackTransaction();
			throw e;
		} catch (TransactionException e) {
			getPm().rollbackTransaction();
			throw e;
		} catch (ObjectNotFoundException e) {
			getPm().rollbackTransaction();
			throw e;
		} catch (Exception e) {
			getPm().rollbackTransaction();
		}
		return lista;
	}

	public IteratorDsk getPartialHealthUnitList() throws RepositoryException,
			ObjectNotFoundException, TransactionException {
		IteratorDsk lista = null;
		try {
			getPm().beginTransaction();
			lista = healthUnitRecord.getPartialHealthUnitList();
			getPm().commitTransaction();
		} catch (RepositoryException e) {
			getPm().rollbackTransaction();
			throw e;
		} catch (TransactionException e) {
			getPm().rollbackTransaction();
			throw e;
		} catch (ObjectNotFoundException e) {
			getPm().rollbackTransaction();
			throw e;
		} catch (Exception e) {
			getPm().rollbackTransaction();
		}
		return lista;
	}

	public void insert(Employee employee)
			throws ObjectAlreadyInsertedException, ObjectNotValidException,
			TransactionException {
		try {
			getPm().beginTransaction();
			employeeRecord.insert(employee);
			getPm().commitTransaction();
		} catch (ObjectAlreadyInsertedException e) {
			getPm().rollbackTransaction();
			throw e;
		} catch (ObjectNotValidException e) {
			getPm().rollbackTransaction();
			throw e;
		} catch (TransactionException e) {
			getPm().rollbackTransaction();
			throw e;
		} catch (Exception e) {
			getPm().rollbackTransaction();
		}
	}

	public int insertComplaint(Complaint complaint) throws RepositoryException,
			ObjectAlreadyInsertedException, ObjectNotValidException,
			TransactionException {
		int retorno = 0;
		try {
			getPm().beginTransaction();
			retorno = complaintRecord.insert(complaint);
			getPm().commitTransaction();
		} catch (ObjectAlreadyInsertedException e) {
			getPm().rollbackTransaction();
			throw e;
		} catch (ObjectNotValidException e) {
			getPm().rollbackTransaction();
			throw e;
		} catch (RepositoryException e) {
			getPm().rollbackTransaction();
			throw e;
		} catch (TransactionException e) {
			getPm().rollbackTransaction();
			throw e;
		}
		return retorno;
	}

	public Employee searchEmployee(String login)
			throws ObjectNotFoundException, TransactionException {
		Employee employee = null;
		try {
			getPm().beginTransaction();
			employee = employeeRecord.search(login);
			getPm().commitTransaction();
			return employee;
		} catch (TransactionException e) {
			getPm().rollbackTransaction();
			throw e;
		} catch (ObjectNotFoundException e) {
			getPm().rollbackTransaction();
			throw e;
		} catch (Exception e) {
			getPm().rollbackTransaction();
		}
		return employee;
	}

	public IteratorDsk getComplaintList() throws ObjectNotFoundException,
			TransactionException {
		IteratorDsk list = null;
		try {
			getPm().beginTransaction();
			list = complaintRecord.getComplaintList();
			getPm().commitTransaction();
		} catch (ObjectNotFoundException e) {
			getPm().rollbackTransaction();
			throw e;
		} catch (TransactionException e) {
			getPm().rollbackTransaction();
			throw e;
		} catch (Exception e) {
			getPm().rollbackTransaction();
		}
		return list;
	}

	public void update(Employee employee) throws TransactionException,
			RepositoryException, ObjectNotFoundException,
			ObjectNotValidException {
		try {
			getPm().beginTransaction();
			employeeRecord.update(employee);
			getPm().commitTransaction();
		} catch (TransactionException e) {
			getPm().rollbackTransaction();
			throw e;
		} catch (ObjectNotValidException e) {
			getPm().rollbackTransaction();
			throw e;
		} catch (ObjectNotFoundException e) {
			getPm().rollbackTransaction();
			throw e;
		} catch (Exception e) {
			getPm().rollbackTransaction();
		}
	}

}