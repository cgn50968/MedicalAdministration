package de.rho.server.patient.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * @author Heiko, Roger
 * @version 1.1 - 19.01.15
 * 
 * Klasse Patient mit den Attributen
 *
 */

public class Patient implements Serializable {
	
	private static final long serialVersionUID = 123456789;
	
	private int id;
	private String firstname;
	private String lastname;
	private String gender;
	private Date dayofbirth;
	private Date lastvisit;
	private int addressid; 
	private String street;
	private String housenumber;
	private String postalcode;
	private String city;
	
	
	// -----------------
	// -- Constructor --
	// -----------------
	public Patient()  {
		
	}
		
	
	// ---------------------
	// -- Getter & Setter --
	// ---------------------	
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDayofbirth() {
		return dayofbirth;
	}

	public void setDayofbirth(Date dayofbirth) {
		this.dayofbirth = dayofbirth;
	}
	
	public Date getLastvisit() {
		return lastvisit;
	}

	public void setLastvisit(Date lastvisit) {
		this.lastvisit = lastvisit;
	}

	public int getAddressid() {
		return addressid;
	}

	public void setAddressid(int addressid) {
		this.addressid = addressid;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHousenumber() {
		return housenumber;
	}

	public void setHousenumber(String housenumber) {
		this.housenumber = housenumber;
	}

	public String getPostalcode() {
		return postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
