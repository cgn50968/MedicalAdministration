package de.rho.server.dao.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import de.rho.server.dao.boundary.InDaoToDB;

public class DaoToH2DB implements InDaoToDB {
	
	// Datenbank Verbindung

		
		/**
		 * 
		 * @return java.sql.Connection
		 */
		public Connection connect() {
			
			System.out.println("DaoToH2DB.connect");
			
			Connection con = null;				//Connection zurücksetzen
			
			/*
			String driver = "org.h2.Driver";
			String db_url = "jdbc:h2:tcp://localhost/~/test";
			String user = "sa";
			String password = "";
			*/
			
			try {
				// Datenbankverbindung
				Class.forName("org.h2.Driver");
				con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");

			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			return con;
		}
		
		
	    public void executeQuery(Connection con, String sql) {
	    	System.out.println("Hallo");
			
			try {
				PreparedStatement pStat = con.prepareStatement(sql);
				pStat.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	
	

	}
	    
	    
	    
	
/*
	
	public void connect() {
        System.out.println("H2: Connecting to H2 database ....");
        System.out.println("H2: Connected");
    }

    public void executeQuery(String sql) {
        System.out.println("H2 Executing Query: ");
        System.out.println(sql);
        System.out.println("on H2 database");
    }
*/

