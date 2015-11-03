package br.cin.ufpe.healthwatcher.business.complaint;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import lib.exceptions.ObjectNotFoundException;
import lib.exceptions.RepositoryException;
import lib.exceptions.TransactionException;
import br.cin.ufpe.healthwatcher.data.IDiseaseRepository;
import br.cin.ufpe.healthwatcher.data.rdb.DiseaseTypeRepositoryRDB;
import br.cin.ufpe.healthwatcher.model.complaint.DiseaseType;

@ManagedBean
@SessionScoped
public class DiseaseRecord implements Serializable {
	
	private static final long serialVersionUID = -4955973920455439632L;

	private DiseaseType selectedDiseaseType = new DiseaseType();
	private List<DiseaseType> diseaseTypes;
	private DiseaseTypeRepositoryRDB diseaseTypeRepositoryRDB = new DiseaseTypeRepositoryRDB();
	
	public DiseaseRecord(IDiseaseRepository repTipoDoenca) {
	}

	public List<DiseaseType> getDiseaseTypes() {
		if(this.diseaseTypes==null){
			this.diseaseTypes = diseaseTypeRepositoryRDB.findAll();
		}
		return diseaseTypes;
	}

	public void setDiseaseTypes(List<DiseaseType> diseaseTypes) {
		this.diseaseTypes = diseaseTypes;
	}

	public DiseaseType getSelectedDiseaseType() {
		return selectedDiseaseType;
	}

	public void setSelectedDiseaseType(DiseaseType selectedDiseaseType) {
		this.selectedDiseaseType = selectedDiseaseType;
	}
	
	public String searchDisease(){
		return "searchDiseaseData?faces-redirect=true";
	}

	public DiseaseType search(int code) throws RepositoryException, ObjectNotFoundException, TransactionException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<DiseaseType> getDiseaseTypeList() throws RepositoryException, ObjectNotFoundException, TransactionException {
		// TODO Auto-generated method stub
		return null;
	}

}
