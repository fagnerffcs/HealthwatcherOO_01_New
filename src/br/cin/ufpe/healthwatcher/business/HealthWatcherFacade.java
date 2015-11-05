package br.cin.ufpe.healthwatcher.business;

import java.io.IOException;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

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
public class HealthWatcherFacade implements IFacade, ServletContextListener {
	
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public IteratorDsk searchSpecialitiesByHealthUnit(int code)
			throws ObjectNotFoundException, RepositoryException,
			TransactionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Complaint searchComplaint(int code) throws RepositoryException,
			ObjectNotFoundException, TransactionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DiseaseType searchDiseaseType(int code) throws RepositoryException,
			ObjectNotFoundException, TransactionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IteratorDsk searchHealthUnitsBySpeciality(int code)
			throws ObjectNotFoundException, RepositoryException,
			TransactionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IteratorDsk getSpecialityList()
			throws RepositoryException, ObjectNotFoundException,
			TransactionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IteratorDsk getDiseaseTypeList() throws RepositoryException,
			ObjectNotFoundException, TransactionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IteratorDsk getHealthUnitList() throws RepositoryException,
			ObjectNotFoundException, TransactionException {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateHealthUnit(HealthUnit unit) throws RepositoryException,
			TransactionException, ObjectNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IteratorDsk getComplaintList() throws ObjectNotFoundException,
			TransactionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Employee e) throws ObjectAlreadyInsertedException,
			ObjectNotValidException, InsertEntryException, TransactionException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateEmployee(Employee e) throws TransactionException,
			RepositoryException, ObjectNotFoundException,
			ObjectNotValidException, UpdateEntryException {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("Instanciando a classe " + HealthWatcherFacade.class);
		singleton = new HealthWatcherFacade();
	}
	
}
