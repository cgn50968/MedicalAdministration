package de.rho.server.patient.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * @author Heiko, Roger
 * @version 1.1 - 19.01.15
 * 
 * konkrete Klasse Patient mit den Attributen
 *
 */

public class Patient implements Serializable {
	
	private static final long serialVersionUID = 123456789;
	
	private int id;
	private String firstname;
	private String lastname;
	private Boolean gender;
	private Integer addressid;
	private Date lastvisit;
	
	//Konstruktor ist noch fast leer....
	public Patient(int id)  {
		
	}
	
	/**** Getter and Setter ****/
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public Integer getAddressid() {
		return addressid;
	}

	public void setAddressid(Integer addressid) {
		this.addressid = addressid;
	}

	public Date getLastvisit() {
		return lastvisit;
	}

	public void setLastvisit(Date lastvisit) {
		this.lastvisit = lastvisit;
	}


	

	
	
	

}
