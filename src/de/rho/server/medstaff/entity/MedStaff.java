package de.rho.server.medstaff.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Roger
 * @version 1.0 - 27.01.15
 * 
 * konkrete Klasse MedStaff mit den Attributen
 *
 */

public class MedStaff implements Serializable {

	private static final long serialVersionUID = 123456789;
	
	private int id;
	private String firstname;
	private String lastname;
	private int roleid;
	private String role;
	private String gender;
	private Date dayofbirth;
	private int addressid; 
	private String street;
	private String housenumber;
	private String postalcode;
	private String city;
	
	// -----------------
	// -- Konstruktor --
	// -----------------
	public MedStaff()  {
		
	}
		
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
	
	public int getRoleid() {
		return roleid;
	}
	
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
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
