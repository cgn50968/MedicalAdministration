package de.rho.server.mt.entity;

import java.io.Serializable;
import java.sql.Clob;
import java.util.Date;

public class MedTreatment implements Serializable {

	/**
	 * @author Roger
	 * @version 1.0 - 27.01.15
	 * 
	 * Klasse MedTreatment mit den Attributen
	 *
	 */
	
	private static final long serialVersionUID = 123456789;
	
	private int id;
	private int patientid;
	private int medstaffid;
	private Date date;
	private String treatment;
	
	
	// -----------------
	// -- Constructor --
	// -----------------
	public MedTreatment()  {
		
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

	public int getPatientid() {
		return patientid;
	}

	public void setPatientid(int patientid) {
		this.patientid = patientid;
	}

	public int getMedstaffid() {
		return medstaffid;
	}

	public void setMedstaffid(int medstaffid) {
		this.medstaffid = medstaffid;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTreatment() {
		return treatment;
	}

	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}

}
