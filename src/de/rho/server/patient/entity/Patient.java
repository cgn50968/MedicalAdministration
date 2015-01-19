package de.rho.server.patient.entity;

import java.io.Serializable;

/**
 * @author Heiko
 * @version 1.0
 * 
 * konkrete Klasse Patient mit den Attributen
 *
 */

public class Patient implements Serializable {
	
	private static final long serialVersionUID = 123456789;
	
	private int id;
	private String vorname;
	private String nachname;
	private String date;
	
	//Konstruktor ist noch fast leer....
	public Patient(int id)  {
		
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	public String getNachname() {
		return nachname;
	}
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
	

}
