package storage;

import java.sql.*;   // Use classes in java.sql package

public class DeleteDatabase {
	public static void main(String[] args){
		try 
		{
			// Step 1: Allocate a database "Connection" object
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "root"); 
			// MySQL

			// Step 2: Allocate a "Statement" object in the Connection
			Statement stmt = conn.createStatement();
			// Step 3: Execute a SQL SELECT query, the query result
			//  is returned in a "ResultSet" object.
			String strCreate = "DROP DATABASE resumeparser";
			System.out.println("The SQL query is: " + strCreate); // Echo For debugging
			System.out.println();

			stmt.executeUpdate(strCreate);


		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		// Step 5: Close the resources - Done automatically by try-with-resources
	}
}
