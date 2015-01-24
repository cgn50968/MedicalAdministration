package de.rho.server.patient.control;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.StringTokenizer;

import de.rho.server.dao.persistence.DaoToFile;
import de.rho.server.patient.entity.Patient;


/**
 * @author Heiko
 * @version 1.4
 * 
 * konkrete Klasse zum Definieren des Dateizugriffs
 * 
 *
 */
 
public class PatientToCSV {

	
	private static final String CSV_SEPARATOR = ";"; //Konstante
	

// ************************************
// **** Read Patient List From CSV ****
// ************************************
	public ArrayList<Patient> readPatientListFromCSV() throws ParseException {
		System.out.println("PatientToCSV.readPatientListFromCSV");	//debug
		
		FileReader myFile = null;
        BufferedReader buff = null;
        ArrayList<Patient> patientList = new ArrayList<Patient>();
 
        try {
        	// **** Create File Object ****
            myFile = new FileReader("testw.csv");
            // **** Create Buffer Object ****
            buff = new BufferedReader(myFile);
            // **** line Object
            String line;
            try {
			    // **********************************************************
			    // **** create SimpleDateFormat to format String to Date ****
			    // **********************************************************
				SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

				// ***********************************
            	// **** For each line in CSV File ****
				// ***********************************
            	while ((line = buff.readLine()) != null) {
				    
					System.out.println(line); 								// debug-Ausgabe
				    
				    Patient patient = new Patient();
				    StringTokenizer st = new StringTokenizer(line, ";");
				
				    patient.setId(Integer.parseInt(st.nextToken()));
					patient.setFirstname(st.nextToken());
					patient.setLastname(st.nextToken());	
					patient.setGender(st.nextToken());
					patient.setDayofbirth(format.parse(st.nextToken()));		// format String to Date
					patient.setLastvisit(format.parse(st.nextToken()));			// format String to Date
					patient.setAddressid(Integer.parseInt(st.nextToken()));
					patient.setStreet(st.nextToken());
					patient.setHousenumber(st.nextToken());
					patient.setPostalcode(st.nextToken());
					patient.setCity(st.nextToken());
				    
					// ********************************************
					// **** add Patient object to Patient List ****
					// ********************************************
				    patientList.add(patient);
				}
				// **************************************
				// **** close Buffer and File object ****
				// **************************************
				buff.close();
		        myFile.close();		
			}
            catch (NumberFormatException e) {
				e.printStackTrace();
			}        
            catch (IOException e) {
				e.printStackTrace();
			}
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        
        System.out.println(patientList.toString());								//debug
        System.out.println("####Ende: PatientToCSV.readPatientListFromCSV");	//debug
        
        return patientList;  
	}

	
// ***************************
// **** Generate CSV File ****
// ***************************	
	public void generateCsvFile(ArrayList<Patient> patientList){
		System.out.println("PatientToCSV.generateCsvFile");	//debug
		
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
                oneLine.append(patient.getDayofbirth());	// Date 
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(patient.getLastvisit());		// Date 
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(patient.getAddressid() <=0 ? "" : patient.getAddressid());	 //Hier muessten wir Sicherstellen, dass die PatientenID nicht 0 ist.
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
