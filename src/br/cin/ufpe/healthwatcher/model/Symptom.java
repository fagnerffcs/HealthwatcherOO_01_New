package br.cin.ufpe.healthwatcher.model;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Symptom
 * @version 1.0
 * @date 2015-04-05 14:48:00
 * @author m_rocha
 */
@Entity

public class Symptom implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue
	private Integer code;
	
	@Column(length = 100)
	private String description;


	// Getters and Setters
	public Integer getCode() {
		return this.code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}   
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
   
}
