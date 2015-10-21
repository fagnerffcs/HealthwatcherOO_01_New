package br.cin.ufpe.healthwatcher.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Authorization {
	
	@Id
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
