package br.cin.ufpe.healthwatcher.model;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Employee
 * @version 1.0
 * @date 2015-04-05 11:58:00
 * @author m_rocha
 */
@Entity
@NamedQueries({
	@NamedQuery(name="todosEmployees", query="SELECT e FROM Employee e"),
	@NamedQuery(name="employeePorLoginSenha", query="SELECT e FROM Employee e WHERE e.login = :login AND e.password = :password"),
})
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(length = 100)
	private String name;   

	@Id
	@Column(length = 100)
	private String login;

	@Column(length = 60)
	private String password;
	
	@Column(columnDefinition="boolean default true", nullable=false)
	private boolean enable;

	// Getters and Setters
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}   
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

}
