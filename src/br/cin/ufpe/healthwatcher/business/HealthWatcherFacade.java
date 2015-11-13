package br.cin.ufpe.healthwatcher.business;

import java.io.IOException;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import lib.exceptions.InsertEntryException;
import lib.exceptions.ObjectAlreadyInsertedException;
import lib.exceptions.ObjectNotFoundException;
import lib.exceptions.ObjectNotValidException;
import lib.exceptions.PersistenceMechanismException;
import lib.exceptions.RepositoryException;
import lib.exceptions.TransactionException;
import lib.exceptions.UpdateEntryException;
import lib.persistence.IPersistenceMechanism;
import lib.persistence.PersistenceMechanism;
import lib.util.IteratorDsk;
import br.cin.ufpe.healthwatcher.Constants;
import br.cin.ufpe.healthwatcher.model.complaint.Complaint;
import br.cin.ufpe.healthwatcher.model.complaint.DiseaseType;
import br.cin.ufpe.healthwatcher.model.employee.Employee;
import br.cin.ufpe.healthwatcher.model.healthguide.HealthUnit;

@ManagedBean(name="facade")
@ApplicationScoped
public class HealthWatcherFacade implements IFacade {
	
	private static HealthWatcherFacade singleton; //padrao singleton
	
	private HealthWatcherFacadeInit fCid;

	public HealthWatcherFacadeInit getfCid() {
		return fCid;
	}

	private static IPersistenceMechanism pm = null;

	private static boolean pmCreated = false;
	
	public HealthWatcherFacade() {
		if(fCid==null){
			this.fCid = new HealthWatcherFacadeInit();
		}
	}
	
	public static IPersistenceMechanism getPm() {
		if (!pmCreated) {
			pm = pmInit();
			if (pm != null) {
				pmCreated = true;
			}
		}
		return pm;
	}
	
	static boolean isPersistent() {
		return Constants.isPersistent();
	}	
	
	static IPersistenceMechanism pmInit() {
		IPersistenceMechanism returnValue = null;
		if (isPersistent()) {
			try {
				returnValue = PersistenceMechanism.getInstance();
				// Persistence mechanism connection
				returnValue.connect();
			} catch (PersistenceMechanismException e) {
				e.printStackTrace();
			} catch (Exception e) {
				// Persistence mechanism desconnection for resource release
				try {
					if (getPm() != null) {
						getPm().disconnect();
					}
				} catch (PersistenceMechanismException mpe) {
					mpe.printStackTrace();
				}
			}
		}
		return returnValue;
	}
	
	public static HealthWatcherFacade getInstance() throws PersistenceMechanismException, IOException {
		if (singleton == null) {
			// Obtain valid persistence mechanism instance
			getPm();
			singleton = new HealthWatcherFacade();
		}
		return singleton;
	}	

	@Override
	public void updateComplaint(Complaint q) throws TransactionException,
			RepositoryException, ObjectNotFoundException,
			ObjectNotValidException {
		fCid.getComplaintRecord().update(q);
	}

	@Override
	public IteratorDsk searchSpecialitiesByHealthUnit(int code)
			throws ObjectNotFoundException, RepositoryException,
			TransactionException {
		return fCid.getHealthUnitRecord().searchSpecialitiesByHealthUnit(code);
	}

	@Override
	public Complaint searchComplaint(int code) throws RepositoryException,
			ObjectNotFoundException, TransactionException {
		return fCid.getComplaintRecord().searchComplaint(code);
	}

	@Override
	public DiseaseType searchDiseaseType(int code) throws RepositoryException,
			ObjectNotFoundException, TransactionException {
		return fCid.getDiseaseRecord().searchDiseaseType(code);
	}

	@Override
	public IteratorDsk searchHealthUnitsBySpeciality(int code)
			throws ObjectNotFoundException, RepositoryException,
			TransactionException {
		return fCid.getHealthUnitRecord().searchHealthUnitsBySpeciality(code);
	}

	@Override
	public IteratorDsk getSpecialityList()
			throws RepositoryException, ObjectNotFoundException,
			TransactionException {
		return fCid.getSpecialityList();
	}

	@Override
	public IteratorDsk getDiseaseTypeList() throws RepositoryException,
			ObjectNotFoundException, TransactionException {
		return (IteratorDsk) fCid.getDiseaseTypeList();
	}

	@Override
	public IteratorDsk getHealthUnitList() throws RepositoryException,
			ObjectNotFoundException, TransactionException {
		return fCid.getHealthUnitList();
	}

	@Override
	public IteratorDsk getPartialHealthUnitList()
			throws RepositoryException, ObjectNotFoundException,
			TransactionException {
		return fCid.getPartialHealthUnitList();
	}

	@Override
	public int insertComplaint(Complaint complaint) throws RepositoryException,
			ObjectAlreadyInsertedException, TransactionException,
			ObjectNotValidException {
		return fCid.insertComplaint(complaint);
	}

	@Override
	public void updateHealthUnit(HealthUnit unit) throws RepositoryException,
			TransactionException, ObjectNotFoundException {
		fCid.updateHealthUnit(unit);
	}

	@Override
	public IteratorDsk getComplaintList() throws ObjectNotFoundException,
			TransactionException {
		return fCid.getComplaintList();
	}

	@Override
	public void insert(Employee e) throws ObjectAlreadyInsertedException,
			ObjectNotValidException, InsertEntryException, TransactionException {
		fCid.insert(e);
	}

	@Override
	public void updateEmployee(Employee e) throws TransactionException,
			RepositoryException, ObjectNotFoundException,
			ObjectNotValidException, UpdateEntryException {
		fCid.update(e);
	}

	@Override
	public Employee searchEmployee(String login) throws TransactionException,
			RepositoryException, ObjectNotFoundException,
			ObjectNotValidException, UpdateEntryException {
		return fCid.searchEmployee(login);
	}

	@Override
	public HealthUnit searchHealthUnit(int healthUnitCode)
			throws ObjectNotFoundException, RepositoryException {
		return fCid.searchHealthUnit(healthUnitCode);
	}
	
}
