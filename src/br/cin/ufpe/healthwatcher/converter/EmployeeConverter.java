package br.cin.ufpe.healthwatcher.converter;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.persistence.PersistenceException;

import lib.exceptions.ObjectNotFoundException;
import lib.exceptions.ObjectNotValidException;
import lib.exceptions.PersistenceMechanismException;
import lib.exceptions.RepositoryException;
import lib.exceptions.TransactionException;
import lib.exceptions.UpdateEntryException;
import br.cin.ufpe.healthwatcher.business.HealthWatcherFacade;
import br.cin.ufpe.healthwatcher.model.employee.Employee;

@ManagedBean
@SessionScoped
public class EmployeeConverter implements Converter, Serializable {

	private static final long serialVersionUID = 391558762793887877L;
	
	private HealthWatcherFacade facade;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,	String value) {
		if(value!=null){
			Employee emp;
			try {
				facade = HealthWatcherFacade.getInstance();
				emp = facade.searchEmployee(value);
				return emp;
			} catch (ObjectNotFoundException | RepositoryException | PersistenceException | PersistenceMechanismException | 
					 IOException | TransactionException | ObjectNotValidException | UpdateEntryException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,	Object value) {
		String login = null;
		if(value instanceof Employee){
			login = String.valueOf(((Employee) value).getLogin());
		}
		return login;
	}

}
