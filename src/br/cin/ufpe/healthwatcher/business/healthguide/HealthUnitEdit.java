package br.cin.ufpe.healthwatcher.business.healthguide;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import lib.exceptions.ObjectNotFoundException;
import lib.exceptions.ObjectNotValidException;
import lib.exceptions.RepositoryException;
import lib.util.IteratorDsk;
import br.cin.ufpe.healthwatcher.business.HealthWatcherFacade;
import br.cin.ufpe.healthwatcher.model.healthguide.HealthUnit;

@ManagedBean
@ViewScoped
public class HealthUnitEdit implements Serializable {

	private static final long serialVersionUID = -4914101471627136696L;
	
	private List<HealthUnit> healthUnits;
	private HealthUnit selectedHealthUnit;
	private HealthWatcherFacade facade;
	
	@PostConstruct
	private void init(){
		Integer code = (Integer) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("healthunit");
		try{
			facade = HealthWatcherFacade.getInstance();
			if(code!=null){
				this.selectedHealthUnit = facade.getfCid().getHealthUnitRecord().search(code);
			}
			IteratorDsk it = facade.getHealthUnitList();
			List<HealthUnit> lista = new ArrayList<HealthUnit>();
			while(it.hasNext()){
				lista.add((HealthUnit) it.next());
			}
			this.healthUnits = lista;
		} catch(Exception e) {
				
		}
	}

	public List<HealthUnit> getHealthUnits() {
		return healthUnits;
	}

	public void setHealthUnits(List<HealthUnit> healthUnits) {
		this.healthUnits = healthUnits;
	}

	public HealthUnit getSelectedHealthUnit() {
		return selectedHealthUnit;
	}

	public void setSelectedHealthUnit(HealthUnit selectedHealthUnit) {
		this.selectedHealthUnit = selectedHealthUnit;
	}
	
	public String editHealthUnit(){
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("healthunit", this.selectedHealthUnit.getCode());
		return "updateHealthUnitSearch?faces-redirect=true";
	}
	
	public String atualizar(){
		try{
			facade = HealthWatcherFacade.getInstance();
		} catch (Exception e) {
			try {
				facade.getfCid().getHealthUnitRecord().update(selectedHealthUnit);
			} catch (RepositoryException e1) {
				e1.printStackTrace();
			} catch (ObjectNotFoundException e1) {
				e1.printStackTrace();
			} catch (ObjectNotValidException e1) {
				e1.printStackTrace();
			}
		}
		return "updateHealthUnitData?faces-redirect=true";
	}
	
	public boolean isEmpty(){
		return this.healthUnits.size() == 0;
	}

}
