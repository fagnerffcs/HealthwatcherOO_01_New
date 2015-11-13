package br.cin.ufpe.healthwatcher.converter;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import lib.exceptions.ObjectNotFoundException;
import lib.exceptions.PersistenceMechanismException;
import lib.exceptions.RepositoryException;
import br.cin.ufpe.healthwatcher.business.HealthWatcherFacade;
import br.cin.ufpe.healthwatcher.model.healthguide.HealthUnit;

@ManagedBean
@SessionScoped
public class HealthUnitConverter implements Converter, Serializable {

	private static final long serialVersionUID = 391558762793887877L;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,	String value) {
		if(value!=null){
			HealthWatcherFacade facade;
			try {
				facade = HealthWatcherFacade.getInstance();
				HealthUnit hu = facade.getfCid().getHealthUnitRecord().search(Integer.parseInt(value));
				return hu;				
			} catch (NumberFormatException | PersistenceMechanismException | IOException | ObjectNotFoundException | RepositoryException e) {
				e.printStackTrace();
			}
		}
		return null;
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
