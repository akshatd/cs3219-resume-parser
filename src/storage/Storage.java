package storage;

import java.sql.*;   // Use classes in java.sql package
import java.util.ArrayList;

import common.CV;
import common.Job;
import common.Ranking;

// JDK 7 and above
public class Storage {
	public static void main(String[] args) {
		try 
		{
			// Step 1: Allocate a database "Connection" object
			 Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/resumeparser", "root", "root"); // MySQL

			// Step 2: Allocate a "Statement" object in the Connection
			 Statement stmt = conn.createStatement();
			// Step 3: Execute a SQL SELECT query, the query result
			//  is returned in a "ResultSet" object.
			
			CV cv = new CV();
			cv.setId(1);
			
			Job job = new Job();
			job.setId(1);
			
			Storage st = new Storage();
			st.saveCV(cv);
			st.saveJob(job);
			
			System.out.println("reached here");
//			String strSelect = "select * from stories";
//			System.out.println("The SQL query is: " + strSelect); // Echo For debugging
//			System.out.println();
//
//			ResultSet rset = stmt.executeQuery(strSelect);
//
//			// Step 4: Process the ResultSet by scrolling the cursor forward via next().
//			//  For each row, retrieve the contents of the cells with getXxx(columnName).
//			System.out.println("The records selected are:");
//			int rowCount = 0;
//			while(rset.next()) {   // Move the cursor to the next row
//				String title = rset.getString("title");
//				double maxPost = rset.getDouble("maxPost");
//				int    id   = rset.getInt("id");
//				System.out.println(title + ", " + maxPost + ", " + id);
//				++rowCount;
//			}
//			System.out.println("Total number of records = " + rowCount);

		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		// Step 5: Close the resources - Done automatically by try-with-resources
	}
	
	private static Statement sqlStatement() {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/resumeparser", 
					"root", "root");
			Statement stmt = conn.createStatement();
			return stmt;
		}
		catch(SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public static CV getCV(int CVId) {
		try {
			Statement stmt = sqlStatement();
			String strSelect = "SELECT * FROM cv WHERE cv.id='" + CVId + "'";
			ResultSet rset = stmt.executeQuery(strSelect);
			if(rset.next()){
				CV cv = CV.fromString(rset.getString("contentMap"));
				return cv;
			}
			return null;
		}
		catch(SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public static Job getJob(int jobId) {
		try {
			Statement stmt = sqlStatement();
			String strSelect = "SELECT * FROM job WHERE job.id='" + jobId + "'";
			ResultSet rset = stmt.executeQuery(strSelect);
			if(rset.next()){
				Job job = Job.fromString(rset.getString("contentMap"));
				return job;
			}
			return null;
		}
		catch(SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public static ArrayList<Job> getAllJobs() {
		try {
			ArrayList<Job> jobsArray = new ArrayList<Job>(); 
			Statement stmt = sqlStatement();
			String strSelect = "SELECT * FROM job";
			ResultSet rset = stmt.executeQuery(strSelect);
			while(rset.next()){
				jobsArray.add(Job.fromString(rset.getString("contentMap")));
			}
			return jobsArray;
		}
		catch(SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public static ArrayList<CV> getAllCVs() {
		try {
			ArrayList<CV> CVsArray = new ArrayList<CV>(); 
			Statement stmt = sqlStatement();
			String strSelect = "SELECT * FROM cv";
			ResultSet rset = stmt.executeQuery(strSelect);
			while(rset.next()){
				CVsArray.add(CV.fromString(rset.getString("contentMap")));
			}
			return CVsArray;
		}
		catch(SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public static ArrayList<Ranking> getRanking(int jobId) {
		try {
			ArrayList<Ranking> rankList = new ArrayList<Ranking>();
			Statement stmt = sqlStatement();
			String strSelect = "SELECT * FROM cv_job WHERE cv_job.jobid='" + jobId + "'";
			ResultSet rset = stmt.executeQuery(strSelect);
			while(rset.next()) {   // Move the cursor to the next row
				CV cv = getCV(rset.getInt("cvid"));
				Ranking ranking = new Ranking();
				ranking.setCV(cv);
				ranking.setJobId(jobId);
				ranking.setMatchPercentage(rset.getDouble("matchpercentage"));
				rankList.add(ranking);
			}
			
			return rankList;
		}
		catch(SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public static int saveCV(CV cv) {
		try {
			Statement stmt = sqlStatement();
			int id = getLastId("cv") + 1;
			cv.setId(id);
			String contentMap = cv.toString();
			String sqlInsert = "INSERT INTO cv VALUES('" + id + "','" + contentMap + "')";
			stmt.executeUpdate(sqlInsert);
			return id;
		}
		catch (SQLException ex){
			ex.printStackTrace();
			return 0;
		}
	}
	
	public static int saveJob(Job job) {
		try {
			Statement stmt = sqlStatement();
			int id = getLastId("job") + 1;
			job.setId(id);
			String contentMap = job.toString();
			String sqlInsert = "INSERT INTO job VALUES('" + id + "','" + contentMap + "')";
			stmt.executeUpdate(sqlInsert);
			return id;
		}
		catch (SQLException ex){
			ex.printStackTrace();
			return 0;
		}
	}
	
	public static boolean saveRanking(ArrayList<Ranking> rankList) {
		try {
			Statement stmt = sqlStatement();
			String sqlInsert = "";
			for(int x = 0; x < rankList.size(); x++) {
				Ranking ranking = rankList.get(x);
				sqlInsert = "INSERT INTO cv_job VALUES('" + ranking.getCV().getId() + "', '" + ranking.getJobId()
						+ "', '" + ranking.getMatchPercentage() + "')";
				stmt.executeUpdate(sqlInsert);
			}
			return true;
		}
		catch (SQLException ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	private static int getLastId(String tableName) {
		try {
			Statement stmt = sqlStatement();
			String strSelect = "SELECT max(id) FROM " + tableName;
			ResultSet rset = stmt.executeQuery(strSelect);
			if (rset.next()) {
				return rset.getInt("max(id)");
			}
			else {
				return 0;
			}
		}
		catch (SQLException ex){
			ex.printStackTrace();
			return 0;
		}
	}
}
