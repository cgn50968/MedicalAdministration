package de.rho.server.patient.control;

import java.io.*;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import de.rho.server.dao.persistence.DaoToFile;
import de.rho.server.patient.entity.Patient;


/**
 * @author Heiko
 * @version 1.2
 * 
 * konkrete Klasse zum Definieren des Dateizugriffs
 * 
 *
 */

public class PatientToCSV {

	
	public ArrayList<Patient> readPatientFromCSV(String list) {
		System.out.println("reading from CSV..."); //CSV als Dateiformat angedacht
		return null;
	}

	
	private static final String CSV_SEPARATOR = ";"; //Konstante
	
	public void generateCsvFile(ArrayList<Patient> patientList){
		
		
		for(int i=0;i<patientList.size();i++){ 			//debug-Ausgabe Array-Liste
            System.out.println(patientList.get(i)); 
        } 
		
		
		try
        {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("testw.csv"), "UTF-8"));
            for (Patient patient : patientList)
            {
                StringBuffer oneLine = new StringBuffer();
                oneLine.append(patient.getId() <=0 ? "" : patient.getId());
                oneLine.append(CSV_SEPARATOR);
                //oneLine.append(patient.getFirstname().trim().length() == 0? "" : patient.getFirstname());
                //oneLine.append(CSV_SEPARATOR);
                //oneLine.append(patient.getLastname().trim().length() == 0? "" : patient.getLastname());
                //oneLine.append(CSV_SEPARATOR);
                //oneLine.append(patient.getGender().trim().length() == 0? "" : patient.getGender());
                bw.write(oneLine.toString());
                bw.newLine();
            }
            bw.flush();
            bw.close();
        }
		
		catch (UnsupportedEncodingException e) {}
        catch (FileNotFoundException e){}
        catch (IOException e){}
				
		
		
		
		
		/*    private static void writeToCSV(ArrayList<Product> productList)
	    {
	        try
	        {
	            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("products.csv"), "UTF-8"));
	            for (Product product : productList)
	            {
	                StringBuffer oneLine = new StringBuffer();
	                oneLine.append(product.getId() <=0 ? "" : product.getId());
	                oneLine.append(CSV_SEPARATOR);
	                oneLine.append(product.getName().trim().length() == 0? "" : product.getName());
	                oneLine.append(CSV_SEPARATOR);
	                oneLine.append(product.getCostPrice() < 0 ? "" : product.getCostPrice());
	                oneLine.append(CSV_SEPARATOR);
	                oneLine.append(product.isVatApplicable() ? "Yes" : "No");
	                bw.write(oneLine.toString());
	                bw.newLine();
	            }
	            bw.flush();
	            bw.close();
	        }
		
		*/
		
			
		//eine Moeglichkeit:
		/*
		try	{
		    FileWriter writer = new FileWriter("c:/Users/heiko/Documents/GitHub/MedicalAdministration/testw.csv");
	 
		    String strid = Integer.toString(patient.getId());
		    String straddrid = Integer.toString(patient.getAddressid());
		    
		    
		    
		    writer.append(strid);
		    writer.append(';');
		    writer.append(patient.getFirstname());
		    writer.append(';');
		    writer.append(patient.getLastname());
		    writer.append(';');
		    writer.append(patient.getGender());
		    writer.append(';');
		    writer.append(straddrid);
		    		    
		    writer.flush();
		    writer.close();
		}
		
		catch(IOException e) {
		     e.printStackTrace();
		} 
		 */
		
	}

	
}
