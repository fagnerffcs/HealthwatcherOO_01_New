package br.cin.ufpe.healthwatcher.converter;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.cin.ufpe.healthwatcher.data.rdb.DiseaseTypeRepositoryRDB;
import br.cin.ufpe.healthwatcher.model.complaint.DiseaseType;

@ManagedBean
@RequestScoped
public class DiseaseTypeConverter implements Converter, Serializable {

	private static final long serialVersionUID = 391558762793887877L;
	
	private DiseaseTypeRepositoryRDB diseaseTypeRepositoryRDB = new DiseaseTypeRepositoryRDB();
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,	String value) {
		if(value!=null){
			DiseaseType diseaseType = diseaseTypeRepositoryRDB.find(value);
			return diseaseType;
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,	Object value) {
		String code = String.valueOf(((DiseaseType) value).getCode());
		return code;
	}

}
