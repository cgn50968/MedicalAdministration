package de.rho.server.patient.entity;

import java.io.Serializable;


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
	private String gender;
	private String dayofbirth;
	private String lastvisit;
	private int addressid; 
	private String street;
	private String housenumber;
	private String postalcode;
	private String city;
	
	//Der Default-Konstruktor
		public Patient()  {
		
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDayofbirth() {
		return dayofbirth;
	}

	public void setDayofbirth(String dayofbirth) {
		this.dayofbirth = dayofbirth;
	}
	
	public String getLastvisit() {
		return lastvisit;
	}

	public void setLastvisit(String lastvisit) {
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
