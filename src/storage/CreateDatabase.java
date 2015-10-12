package storage;

import java.sql.*;   // Use classes in java.sql package

public class CreateDatabase {
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
			String strCreate = "CREATE DATABASE resumeparser";
			System.out.println("The SQL query is: " + strCreate); // Echo For debugging
			System.out.println();

			stmt.executeUpdate(strCreate);

			// Step 4: Process the ResultSet by scrolling the cursor forward via next().
			//  For each row, retrieve the contents of the cells with getXxx(columnName).
			System.out.println("Database has been created");

			strCreate = "CREATE TABLE cv " +
					"(id INTEGER not NULL, " +					
					" contentMap TEXT, " + 
					" PRIMARY KEY ( id ))"; 
			System.out.println("The SQL query is: " + strCreate); // Echo For debugging
			System.out.println();

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/resumeparser", "root", "root"); 
			// MySQL

			// Step 2: Allocate a "Statement" object in the Connection
			stmt = conn.createStatement();

			stmt.executeUpdate(strCreate);
			System.out.println("Table cv has been created");

			strCreate = "CREATE TABLE job " +
					"(id INTEGER not NULL, " +
					" contentMap TEXT, " +
					" PRIMARY KEY ( id ))"; 
			System.out.println("The SQL query is: " + strCreate); // Echo For debugging
			System.out.println();

			stmt.executeUpdate(strCreate);
			System.out.println("Table job has been created");

			strCreate = "CREATE TABLE cv_job " +
					"(cvid INTEGER not NULL, " +
					" jobid INTEGER not NULL, " +
					" matchpercentage DECIMAL not NULL, " +
					" PRIMARY KEY ( cvid, jobid ), " +
					" FOREIGN KEY (cvid) REFERENCES cv(id), " +
					" FOREIGN KEY (jobid) REFERENCES job(id))"; 
			System.out.println("The SQL query is: " + strCreate); // Echo For debugging
			System.out.println();

			stmt.executeUpdate(strCreate);
			System.out.println("Table cv_job has been created");

		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		// Step 5: Close the resources - Done automatically by try-with-resources
	}
}
