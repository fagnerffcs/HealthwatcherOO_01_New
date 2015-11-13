package br.cin.ufpe.healthwatcher.business.complaint;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import lib.exceptions.CommunicationException;
import lib.exceptions.ObjectNotFoundException;
import lib.exceptions.RepositoryException;
import lib.util.IteratorDsk;
import br.cin.ufpe.healthwatcher.data.IDiseaseRepository;
import br.cin.ufpe.healthwatcher.model.complaint.DiseaseType;

@ManagedBean
@SessionScoped
public class DiseaseRecord implements Serializable {
	
	private static final long serialVersionUID = -4955973920455439632L;

	private DiseaseType selectedDiseaseType;
	private IDiseaseRepository diseaseRep;
	
	public DiseaseRecord(IDiseaseRepository repTipoDoenca) {
		this.diseaseRep = repTipoDoenca;
	}

	public DiseaseType searchDiseaseType(int codigo) throws RepositoryException, ObjectNotFoundException {
		return diseaseRep.search(codigo);
	}

	public IteratorDsk getDiseaseTypeList() throws RepositoryException, ObjectNotFoundException {
		return diseaseRep.getDiseaseTypeList();
	}

	public DiseaseType getSelectedDiseaseType() {
		FacesContext facesContext = FacesContext.getCurrentInstance();  
		Integer diseaseCode = (Integer) facesContext.getExternalContext().getFlash().get("diseaseCode");
		if(diseaseCode!=null){
			try {
				this.selectedDiseaseType = this.diseaseRep.search(diseaseCode);
			} catch (ObjectNotFoundException | RepositoryException e) {
				e.printStackTrace();
			}
		}
		return selectedDiseaseType;
	}

	public void setSelectedDiseaseType(DiseaseType selectedDiseaseType) {
		this.selectedDiseaseType = selectedDiseaseType;
	}
	
	public List<DiseaseType> getDiseaseTypeAsList(){
		IteratorDsk it;
		List<DiseaseType> lista = new ArrayList<DiseaseType>();
		try {
			it = getDiseaseTypeList();
			while(it.hasNext()){
				lista.add((DiseaseType) it.next());
			}
		} catch (RepositoryException | ObjectNotFoundException | CommunicationException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public String searchDisease(){
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("diseaseCode", this.selectedDiseaseType.getCode());
		return "searchDiseaseData?faces-redirect=true";
	}

}
