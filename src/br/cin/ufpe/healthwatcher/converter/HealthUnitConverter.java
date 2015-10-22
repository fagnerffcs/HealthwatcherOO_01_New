package br.cin.ufpe.healthwatcher.converter;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.cin.ufpe.healthwatcher.model.healthguide.HealthUnit;
import br.cin.ufpe.healthwatcher.service.HealthUnitService;

@ManagedBean
@SessionScoped
public class HealthUnitConverter implements Converter, Serializable {

	private static final long serialVersionUID = 391558762793887877L;
	
	private HealthUnitService healthUnitService = new HealthUnitService();
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,	String value) {
		if(value!=null){
			HealthUnit hu = healthUnitService.find(value);
			return hu;
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,	Object value) {
		if(value instanceof HealthUnit){
			return String.valueOf(((HealthUnit) value).getCode());
		} else {
			return null;
		}
	}

}
