package br.cin.ufpe.healthwatcher.data.rdb;

import lib.exceptions.ObjectAlreadyInsertedException;
import lib.exceptions.ObjectNotFoundException;
import lib.exceptions.ObjectNotValidException;
import lib.exceptions.RepositoryException;
import lib.persistence.IPersistenceMechanism;
import lib.persistence.PersistenceMechanism;
import lib.util.IteratorDsk;
import br.cin.ufpe.healthwatcher.data.IComplaintRepository;
import br.cin.ufpe.healthwatcher.model.complaint.AnimalComplaint;
import br.cin.ufpe.healthwatcher.model.complaint.Complaint;
import br.cin.ufpe.healthwatcher.model.complaint.FoodComplaint;
import br.cin.ufpe.healthwatcher.model.complaint.SpecialComplaint;

public class ComplaintRepositoryRDB implements IComplaintRepository {
	
	private IPersistenceMechanism pm;

	public ComplaintRepositoryRDB(PersistenceMechanism pm) {
		this.pm = pm;
	}

	@Override
	public int insert(Complaint complaint) throws ObjectNotValidException,
			ObjectAlreadyInsertedException, ObjectNotValidException,
			RepositoryException {
		if(complaint instanceof FoodComplaint){
			FoodComplaintRepositoryRDB foodRepositoryRDB = new FoodComplaintRepositoryRDB((PersistenceMechanism) this.pm);
			return foodRepositoryRDB.insert((FoodComplaint) complaint);
		} else if(complaint instanceof AnimalComplaint){
			AnimalComplaintRepositoryRDB animalRepositoryRDB = new AnimalComplaintRepositoryRDB((PersistenceMechanism) this.pm);
			return animalRepositoryRDB.insert((AnimalComplaint) complaint);
		} else if(complaint instanceof SpecialComplaint){
			SpecialComplaintRepositoryRDB specialRepository = new SpecialComplaintRepositoryRDB((PersistenceMechanism) this.pm);
			return specialRepository.insert((SpecialComplaint) complaint);
		}
		return 0;
	}

	@Override
	public void update(Complaint complaint) throws ObjectNotValidException,
			ObjectNotFoundException, ObjectNotValidException,
			RepositoryException {
		if(complaint instanceof FoodComplaint){
			FoodComplaintRepositoryRDB foodRepositoryRDB = new FoodComplaintRepositoryRDB((PersistenceMechanism) this.pm);
			foodRepositoryRDB.update((FoodComplaint) complaint);
		} else if(complaint instanceof AnimalComplaint){
			AnimalComplaintRepositoryRDB animalRepositoryRDB = new AnimalComplaintRepositoryRDB((PersistenceMechanism) this.pm);
			animalRepositoryRDB.update((AnimalComplaint) complaint);
		} else if(complaint instanceof SpecialComplaint){
			SpecialComplaintRepositoryRDB specialRepository = new SpecialComplaintRepositoryRDB((PersistenceMechanism) this.pm);
			specialRepository.update((SpecialComplaint) complaint);
		}	
	}

	@Override
	public boolean exists(int code) throws RepositoryException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void remove(int code) throws ObjectNotFoundException,
			RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Complaint search(int code) throws ObjectNotFoundException,
			RepositoryException {
		Complaint complaint = null;
		FoodComplaintRepositoryRDB foodRepositoryRDB = new FoodComplaintRepositoryRDB((PersistenceMechanism) this.pm);
		complaint = foodRepositoryRDB.search(code);
		if(complaint==null){
			AnimalComplaintRepositoryRDB animalRepositoryRDB = new AnimalComplaintRepositoryRDB((PersistenceMechanism) this.pm);
			complaint = animalRepositoryRDB.search(code);
			if(complaint==null){
				SpecialComplaintRepositoryRDB specialRepository = new SpecialComplaintRepositoryRDB((PersistenceMechanism) this.pm);
				complaint = specialRepository.search(code);
			}
		}
		return complaint;
	}

	@Override
	public IteratorDsk getComplaintList() throws ObjectNotFoundException,
			RepositoryException {
		// TODO Auto-generated method stub
		return null;
	}

}
