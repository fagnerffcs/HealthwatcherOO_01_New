package br.cin.ufpe.healthwatcher.converter;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.cin.ufpe.healthwatcher.business.HealthWatcherFacade;
import br.cin.ufpe.healthwatcher.model.complaint.DiseaseType;

@ManagedBean
@RequestScoped
public class DiseaseTypeConverter implements Converter, Serializable {

	private static final long serialVersionUID = 391558762793887877L;
	private HealthWatcherFacade facade;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,	String value) {
		if(value!=null){
			try{
				if(facade==null){
					facade = HealthWatcherFacade.getInstance();
				}
				DiseaseType diseaseType = facade.searchDiseaseType(Integer.parseInt(value));
				return diseaseType;
			} catch(Exception e) {
				
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,	Object value) {
		String code = String.valueOf(((DiseaseType) value).getCode());
		return code;
	}

}
