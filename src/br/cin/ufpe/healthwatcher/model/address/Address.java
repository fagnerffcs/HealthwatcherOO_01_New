package br.cin.ufpe.healthwatcher.model.address;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer code;
	
	@Column(length = 100)
	private String street;
	
	@Column(length = 100)
	private String complement;
	
	@Column(length = 20)
	private String zip;
	
	@NotNull
	@Column(length = 100)
	private String state;
	
	@Column(length = 20)
	private String phone;
	
	@NotNull
	@Column(length = 100)
	private String city;
	
	@Column(length = 100)
	private String neighbourhood;

	// Getters and Setters
	public Integer getCode() {
		return this.code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}   
	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}   
	public String getComplement() {
		return this.complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}   
	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}   
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}   
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}   
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}   
	public String getNeighbourhood() {
		return this.neighbourhood;
	}

	public void setNeighbourhood(String neighbourhood) {
		this.neighbourhood = neighbourhood;
	}
   
}
