package br.cin.ufpe.healthwatcher.data.rdb;

import java.util.ArrayList;
import java.util.List;

import lib.exceptions.CommunicationException;
import lib.exceptions.ObjectAlreadyInsertedException;
import lib.exceptions.ObjectNotFoundException;
import lib.exceptions.ObjectNotValidException;
import lib.exceptions.RepositoryException;
import lib.persistence.IPersistenceMechanism;
import lib.persistence.PersistenceMechanism;
import lib.util.ConcreteIterator;
import lib.util.IteratorDsk;
import br.cin.ufpe.healthwatcher.data.IComplaintRepository;
import br.cin.ufpe.healthwatcher.model.complaint.AnimalComplaint;
import br.cin.ufpe.healthwatcher.model.complaint.Complaint;
import br.cin.ufpe.healthwatcher.model.complaint.FoodComplaint;
import br.cin.ufpe.healthwatcher.model.complaint.SpecialComplaint;

public class ComplaintRepositoryRDB implements IComplaintRepository {
	
	private IPersistenceMechanism mp;
	private FoodComplaintRepositoryRDB foodRepositoryRDB;
	private AnimalComplaintRepositoryRDB animalRepositoryRDB;
	private SpecialComplaintRepositoryRDB specialRepositoryRDB;

	public ComplaintRepositoryRDB(PersistenceMechanism mp) {
		this.mp = mp;
		this.foodRepositoryRDB 		= new FoodComplaintRepositoryRDB((PersistenceMechanism) this.mp);
		this.animalRepositoryRDB 	= new AnimalComplaintRepositoryRDB((PersistenceMechanism) this.mp);
		this.specialRepositoryRDB 	= new SpecialComplaintRepositoryRDB((PersistenceMechanism) this.mp);
	}

	@Override
	public int insert(Complaint complaint) throws ObjectNotValidException,
			ObjectAlreadyInsertedException, ObjectNotValidException,
			RepositoryException {
		if(complaint instanceof FoodComplaint){
			return foodRepositoryRDB.insert((FoodComplaint) complaint);
		} else if(complaint instanceof AnimalComplaint){
			return animalRepositoryRDB.insert((AnimalComplaint) complaint);
		} else if(complaint instanceof SpecialComplaint){
			return specialRepositoryRDB.insert((SpecialComplaint) complaint);
		}
		return 0;
	}

	@Override
	public void update(Complaint complaint) throws ObjectNotValidException,
			ObjectNotFoundException, ObjectNotValidException,
			RepositoryException {
		if(complaint instanceof FoodComplaint){
			foodRepositoryRDB.update((FoodComplaint) complaint);
		} else if(complaint instanceof AnimalComplaint){
			animalRepositoryRDB.update((AnimalComplaint) complaint);
		} else if(complaint instanceof SpecialComplaint){
			specialRepositoryRDB.update((SpecialComplaint) complaint);
		}	
	}

	@Override
	public boolean exists(int code) throws RepositoryException {
		Complaint c = null;
		try {
			c = search(code);
		} catch (ObjectNotFoundException e) {
			e.printStackTrace();
		}
		return c != null;
	}

	@Override
	public void remove(int code) throws ObjectNotFoundException,
			RepositoryException {
		Complaint c = search(code);
		if(c instanceof FoodComplaint){
			foodRepositoryRDB.remove(code);
		} else if(c instanceof AnimalComplaint){
			animalRepositoryRDB.remove(code);
		} else if(c instanceof SpecialComplaint){
			specialRepositoryRDB.remove(code);
		}
	}

	@Override
	public Complaint search(int code) throws ObjectNotFoundException,
			RepositoryException {
		Complaint complaint = null;
		complaint = foodRepositoryRDB.search(code);
		if(complaint==null){
			complaint = animalRepositoryRDB.search(code);
			if(complaint==null){
				complaint = specialRepositoryRDB.search(code);
			}
		}
		return complaint;
	}

	@Override
	public IteratorDsk getComplaintList() throws ObjectNotFoundException,
			RepositoryException {
		IteratorDsk itFood = foodRepositoryRDB.getComplaintList();
		IteratorDsk itAnimal = animalRepositoryRDB.getComplaintList();
		IteratorDsk itSpecial = specialRepositoryRDB.getComplaintList();
		
		List<Complaint> allComplaints = new ArrayList<Complaint>();
		try {
			while(itFood.hasNext()){
				allComplaints.add((Complaint) itFood.next());
			}
			while(itAnimal.hasNext()){
				allComplaints.add((Complaint) itAnimal.next());
			}
			while(itSpecial.hasNext()){
				allComplaints.add((Complaint) itSpecial.next());
			}

		} catch (CommunicationException e) {
			e.printStackTrace();
		}
		return new ConcreteIterator(allComplaints);
	}

}
