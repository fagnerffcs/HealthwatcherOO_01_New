package br.cin.ufpe.healthwatcher.converter;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.cin.ufpe.healthwatcher.data.rdb.SpecialtyRepositoryRDB;
import br.cin.ufpe.healthwatcher.model.healthguide.MedicalSpecialty;

@ManagedBean
@SessionScoped
public class MedicalSpecialtyConverter implements Converter, Serializable {

	private static final long serialVersionUID = 391558762793887877L;
	
	private SpecialtyRepositoryRDB specialtyRepositoryRDB = new SpecialtyRepositoryRDB();
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,	String value) {
		if(value!=null){
			MedicalSpecialty ms = specialtyRepositoryRDB.find(Integer.parseInt(value));
			return ms;
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,	Object value) {
		String code = String.valueOf(((MedicalSpecialty) value).getCode());
		return code;
	}

}
