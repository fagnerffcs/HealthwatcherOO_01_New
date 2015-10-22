package br.cin.ufpe.healthwatcher.model.complaint;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Entity implementation class for Entity: DiseaseType
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name="allDiseases", query="SELECT d FROM DiseaseType d")
})
public class DiseaseType implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue
	private Integer code;
	
	@Column(length = 100)
	private String name;
	
	@Column(length = 200)
	private String description;
	
	@Column(length = 100)
	private String manifestation;
	
	@Column(length = 100)
	private String duration;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable
	(name = "DiseaseType_symptom", joinColumns = @JoinColumn(name = "DiseaseType_code"), inverseJoinColumns = @JoinColumn(name = "symptom_code"))
	private List<Symptom> symptoms;

	// Getters and Setters
	public Integer getCode() {
		return this.code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}   
	public String getManifestation() {
		return this.manifestation;
	}

	public void setManifestation(String manifestation) {
		this.manifestation = manifestation;
	}   
	public String getDuration() {
		return this.duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}   
	public List<Symptom> getSymptoms() {
		return this.symptoms;
	}

	public void setSymptoms(List<Symptom> symptoms) {
		this.symptoms = symptoms;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DiseaseType other = (DiseaseType) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DiseaseType [code=" + code + ", name=" + name + "]";
	}
   
}
