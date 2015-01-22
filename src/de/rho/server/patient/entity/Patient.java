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
	private String gender;
	private String dayofbirth;
	private int addressid;
	private String lastvisit;
	
	//Der Default-Konstruktor
	
	public Patient()  {
		
	}
	
	
	//ein zweiter Konstruktor zum Testen der ArrayList
	//public Patient(int id, String firstname, String lastname, String gender, String dayofbirth, int adressid, String lastvisit)  {
		
	//}
	
	
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
	
	public Integer getAddressid() {
		return addressid;
	}

	public void setAddressid(Integer addressid) {
		this.addressid = addressid;
	}

	public String getLastvisit() {
		return lastvisit;
	}

	public void setLastvisit(String lastvisit) {
		this.lastvisit = lastvisit;
	}


	

	
	
	

}
