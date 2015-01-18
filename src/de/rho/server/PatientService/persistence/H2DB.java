package de.rho.server.PatientService.persistence;

import de.rho.server.PatientService.boundary.InDBConnect;

public class H2DB implements InDBConnect{
	
	public void connect() {
        System.out.println("H2: Connecting to H2 database ....");
        System.out.println("H2: Connected");
    }

    public void executeQuery(String sql) {
        System.out.println("H2 Executing Query: ");
        System.out.println(sql);
        System.out.println("on H2 database");
    }

}
