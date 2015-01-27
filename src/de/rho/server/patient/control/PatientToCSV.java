package de.rho.server.patient.control;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.StringTokenizer;










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

    
	private static final String CSV_SEPARATOR = ";"; 				//Festlegen des Separators als Konstante
	
	SimpleDateFormat sdformat = new SimpleDateFormat("dd.MM.yyyy"); // create SimpleDateFormat to format String to Date


// ************************************
// **** Read Patient List From CSV ****
// ************************************
	public ArrayList<Patient> readCSVFile(String filelocation) throws ParseException {
		System.out.println("PatientToCSV.readPatientListFromCSV");	//debug
		
		// -----------------------------------
		// -- Declare Objects and Variables --
		// -----------------------------------
		String fl = filelocation;
		FileReader myFile = null;
        BufferedReader buff = null;
        ArrayList<Patient> patientList = new ArrayList<Patient>();
 
        try {
        	
            myFile = new FileReader(fl);			// -- Create File Object with Path and Name
            buff = new BufferedReader(myFile);		// -- Create Buffer Object
            String line;							// -- Line als String 
            
            try {
            	
				// -------------------------------------------------
            	// -- Read to Buffer - line by line from CSV File --
				// -------------------------------------------------
            	while ((line = buff.readLine()) != null) {
				    
					System.out.println(line); 								// debug-Ausgabe
				    
				    Patient patient = new Patient();
				    StringTokenizer st = new StringTokenizer(line,";");
				
				    patient.setId(Integer.parseInt(st.nextToken()));
					patient.setFirstname(st.nextToken());
					patient.setLastname(st.nextToken());	
					patient.setGender(st.nextToken());
					patient.setDayofbirth(sdformat.parse(st.nextToken()));		// format String to Date - SimpleDateFormat
					patient.setLastvisit(sdformat.parse(st.nextToken()));		// format String to Date - SimpleDateFormat
					patient.setAddressid(Integer.parseInt(st.nextToken()));
					patient.setStreet(st.nextToken());
					patient.setHousenumber(st.nextToken());
					patient.setPostalcode(st.nextToken());
					patient.setCity(st.nextToken());
				    
					// ----------------------------------------
					// -- add Patient object to Patient List --
					// ----------------------------------------
				    patientList.add(patient);			
				}
				
				// ----------------------------------
            	// -- close Buffer and File object --
				// ----------------------------------
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
        
		// ------------------------
		// -- return PatientList --
		// ------------------------	
        
        return patientList;  
	}

	
// ***************************
// **** Generate CSV File ****
// ***************************	
	public void generateCSVFile(ArrayList<Patient> patientList, String filelocation){
		System.out.println("PatientToCSV.generateCsvFile");	//debug

		// ----------------------
		// -- Path to CSV File --
		// ----------------------
		String fl = filelocation;							
				
		try
        {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fl)));
 
        	// -------------------------------------
        	// -- For each Patient in PatientList -- 
        	// -------------------------------------
            for (Patient patient : patientList) {
            	
            	// -----------------------------------
            	// -- Create String Buffer for Text -- 
            	// -----------------------------------
                StringBuffer oneLine = new StringBuffer();
                
            	// ------------------------------------------
                // -- Create CSV String and save in Buffer --
                // ------------------------------------------
                oneLine.append(patient.getId() <=0 ? "" : patient.getId());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(patient.getFirstname().trim().length() == 0? "" : patient.getFirstname());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(patient.getLastname().trim().length() == 0? "" : patient.getLastname());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(patient.getGender().trim().length() == 0? "" : patient.getGender());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(sdformat.format(patient.getDayofbirth()));		// Date (sdformat.format()) SimpleDayFormat
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(sdformat.format(patient.getLastvisit()));		// Date (sdformat.format()) SimpleDayFormat
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
                
                // --------------------------------------
                // -- Write text-line to Buffer object --
                // --------------------------------------
                bw.write(oneLine.toString());	// -- write line
                bw.newLine();					// -- new line
            }
            bw.flush();		// -- delete text in Buffer (flush = ausspuelen)
            bw.close();     // -- close Buffer
        }
		catch (UnsupportedEncodingException e) {
		}
		catch (FileNotFoundException e){
		}
		catch (IOException e){
		}
	}

}
