package br.cin.ufpe.healthwatcher.converter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.cin.ufpe.healthwatcher.data.rdb.AnimalComplaintRepositoryRDB;
import br.cin.ufpe.healthwatcher.data.rdb.FoodComplaintRepositoryRDB;
import br.cin.ufpe.healthwatcher.data.rdb.SpecialComplaintRepositoryRDB;
import br.cin.ufpe.healthwatcher.model.complaint.Complaint;

@ManagedBean
@RequestScoped
public class ComplaintConverter implements Converter {
	
	private FoodComplaintRepositoryRDB foodComplaintService = new FoodComplaintRepositoryRDB();
	
	private AnimalComplaintRepositoryRDB animalComplaintService = new AnimalComplaintRepositoryRDB();
	
	private SpecialComplaintRepositoryRDB specialComplaintRepositoryRDB = new SpecialComplaintRepositoryRDB();
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,	String value) {
		if(value!=null){
			Complaint complaint = foodComplaintService.find(Integer.parseInt(value));
			if(complaint==null){
				complaint = animalComplaintService.find(Integer.parseInt(value));
			}
			if(complaint==null){
				complaint = specialComplaintRepositoryRDB.find(Integer.parseInt(value));
			}
			return complaint;
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,	Object value) {
		String code = String.valueOf(((Complaint) value).getCodigo());
		return code;
	}
}
