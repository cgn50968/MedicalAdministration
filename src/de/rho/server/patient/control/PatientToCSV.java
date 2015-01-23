package de.rho.server.patient.control;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

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

	
	public ArrayList<Patient> readPatientListFromCSV() {
		
		System.out.println("reading from CSV...");
		
		String fileName = "testw.csv";		 		 // Deklaration Name und ggf. Pfad
        File file = new File(fileName);				 // Uebergabe der Deklaration an File-Objekt
        ArrayList<Patient> patientList = new ArrayList<Patient>();
        
        try{
            Scanner inputStream = new Scanner(file); // File-Objekt mit Scanner-Klasse lesen
            
            while(inputStream.hasNext()){			 // Bedingung: hasNext()-Methode durchlauft die Schleife line-by-line
                //String data = inputStream.next();	 // jede einzelne Zeile der Datei in einen String packen
            	Patient patient = new Patient();
				patient.setId(Integer.parseInt(inputStream.next()));
				patient.setFirstname(inputStream.next());
				patient.setLastname(inputStream.next());	
				patient.setGender(inputStream.next());
				patient.setDayofbirth(inputStream.next());
				patient.setLastvisit(inputStream.next());	
				patient.setAddressid(Integer.parseInt(inputStream.next()));
				patient.setStreet(inputStream.next());	
				patient.setHousenumber(inputStream.next());	
				patient.setPostalcode(inputStream.next());	
				patient.setCity(inputStream.next());	
				
				// **** Uebergabe des Patienten Objekts an die PatientenListe
				patientList.add(patient);
                
                
                //System.out.println(data);	//TODO: Problem: den Inhalt von data in eine ArrayList
               
                
                      
            }
            inputStream.close();					// nach der Schleife: Scanner schliessen 
        }

        catch (FileNotFoundException e){
            e.printStackTrace();
        }
			
        return patientList;		//TODO: ArrayList zurueckgeben
	}

	
	private static final String CSV_SEPARATOR = ";"; //Konstante
	
	public void generateCsvFile(ArrayList<Patient> patientList){
		
		
		for(int i=0;i<patientList.size();i++){ 			//debug-Ausgabe Array-Liste
            System.out.println(patientList.get(i)); 
        } 
		
		try
        {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("testw.csv"), "UTF-8"));
            for (Patient patient : patientList) {
            	
            	// **** Neuer String Buffer für Textausgabe pro Zeile **** 
                StringBuffer oneLine = new StringBuffer();
                
                // **** Uebergabe der Attribute an String Buffer ****
                oneLine.append(patient.getId() <=0 ? "" : patient.getId());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(patient.getFirstname().trim().length() == 0? "" : patient.getFirstname());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(patient.getLastname().trim().length() == 0? "" : patient.getLastname());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(patient.getGender().trim().length() == 0? "" : patient.getGender());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(patient.getDayofbirth().trim().length() == 0? "" : patient.getDayofbirth());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(patient.getLastvisit().trim().length() == 0? "" : patient.getLastvisit());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(patient.getAddressid() <=0 ? "" : patient.getAddressid());	 //Hier müssten wir Sicherstellen, dass die PatientenID nicht 0 ist.
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(patient.getStreet().trim().length() == 0? "" : patient.getStreet());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(patient.getHousenumber().trim().length() == 0? "" : patient.getHousenumber());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(patient.getPostalcode().trim().length() == 0? "" : patient.getPostalcode());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(patient.getCity().trim().length() == 0? "" : patient.getCity());
                
                
                bw.write(oneLine.toString());	// **** Schreiben der Zeile
                bw.newLine();					// **** Neue Zeile
            }
            bw.flush();		// Loeschen des Streams (flush = ausspuelen)
            bw.close();
        }
		
		catch (UnsupportedEncodingException e) {
		}
        
		catch (FileNotFoundException e){
		}
        
		catch (IOException e){
		}
				
		
		
		
		
		
		
	}

	
}
