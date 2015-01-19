package de.rho.server.dao.persistence;

import de.rho.server.dao.boundary.InDaoToDB;

public class DaoToH2DB implements InDaoToDB{
	
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
