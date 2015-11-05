package br.cin.ufpe.healthwatcher.business.complaint;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import lib.exceptions.ObjectAlreadyInsertedException;
import lib.exceptions.ObjectNotFoundException;
import lib.exceptions.ObjectNotValidException;
import lib.exceptions.RepositoryException;
import lib.exceptions.TransactionException;
import lib.util.IteratorDsk;
import br.cin.ufpe.healthwatcher.data.IComplaintRepository;
import br.cin.ufpe.healthwatcher.model.complaint.Complaint;

@ManagedBean
@ViewScoped
public class ComplaintRecord implements Serializable {

	private static final long serialVersionUID = -6887424307646650506L;

	private IComplaintRepository complaintRep;

	public ComplaintRecord(IComplaintRepository rep) {
		this.complaintRep = rep;
	}

	public Complaint search(int code) throws RepositoryException,
			ObjectNotFoundException {
		return complaintRep.search(code);
	}

	public int insert(Complaint complaint) throws RepositoryException,
			ObjectAlreadyInsertedException, ObjectNotValidException,
			TransactionException {
		if (complaintRep.exists(complaint.getCodigo())) {
			throw new ObjectAlreadyInsertedException(
					"Complaint code already registered");
		}
		return complaintRep.insert(complaint);
	}

	public void update(Complaint q) throws RepositoryException,
			ObjectNotFoundException, ObjectNotValidException {
		complaintRep.update(q);
	}

	public IteratorDsk getComplaintList() throws RepositoryException,
			ObjectNotFoundException {
		return complaintRep.getComplaintList();

	}

}
