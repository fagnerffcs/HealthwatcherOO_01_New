package br.cin.ufpe.healthwatcher.converter;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;

import br.cin.ufpe.healthwatcher.model.Employee;
import br.cin.ufpe.healthwatcher.service.EmployeeService;

@ManagedBean
@SessionScoped
public class EmployeeConverter implements Converter, Serializable {

	private static final long serialVersionUID = 391558762793887877L;
	
	@Inject
	private EmployeeService employeeService;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,	String value) {
		if(value!=null){
			Employee emp = employeeService.find(value);
			return emp;
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,	Object value) {
		String login = String.valueOf(((Employee) value).getLogin());
		return login;
	}

}
