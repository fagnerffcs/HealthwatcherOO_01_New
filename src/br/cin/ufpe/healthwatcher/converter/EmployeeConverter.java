package br.cin.ufpe.healthwatcher.converter;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import lib.exceptions.ObjectNotFoundException;
import lib.exceptions.RepositoryException;
import br.cin.ufpe.healthwatcher.data.rdb.EmployeeRepositoryRDB;
import br.cin.ufpe.healthwatcher.model.employee.Employee;

@ManagedBean
@SessionScoped
public class EmployeeConverter implements Converter, Serializable {

	private static final long serialVersionUID = 391558762793887877L;
	
	private EmployeeRepositoryRDB employeeService = new EmployeeRepositoryRDB();
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,	String value) {
		if(value!=null){
			Employee emp;
			try {
				emp = employeeService.search(value);
				return emp;
			} catch (ObjectNotFoundException e) {
				e.printStackTrace();
			} catch (RepositoryException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,	Object value) {
		String login = String.valueOf(((Employee) value).getLogin());
		return login;
	}

}
