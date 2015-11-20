package br.cin.ufpe.healthwatcher.converter;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import lib.util.IteratorDsk;
import br.cin.ufpe.healthwatcher.business.HealthWatcherFacade;
import br.cin.ufpe.healthwatcher.model.healthguide.MedicalSpecialty;

@ManagedBean
@SessionScoped
public class MedicalSpecialtyConverter implements Converter, Serializable {

	private static final long serialVersionUID = 391558762793887877L;
	
	private HealthWatcherFacade facade;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,	String value) {
		if(value!=null){
			try{
				facade = HealthWatcherFacade.getInstance();
				IteratorDsk it = facade.getSpecialityList();
				while(it.hasNext()){
					MedicalSpecialty ms = (MedicalSpecialty) it.next();
					if(ms.getCode()==Integer.parseInt(value)){
						return ms;
					}
				}
			}catch(Exception e){
				
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,	Object value) {
		String code = String.valueOf(((MedicalSpecialty) value).getCode());
		return code;
	}

}
