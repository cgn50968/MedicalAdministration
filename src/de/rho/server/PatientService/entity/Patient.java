package de.rho.server.PatientService.entity;

/**
 * @author Heiko
 * @version 1.0
 * 
 * konkrete Klasse Patient mit den Attributen
 *
 */

public class Patient {
	
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
