package Analyser;
import java.sql.*;   // Use classes in java.sql package
import java.util.ArrayList;

import storage.CV;
import storage.Job;


public class Analyser 
{
	int matchedWords; 
	int totalWords;
	double percentage;
	String [] JobKeyWordsArray;
	
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
	
	
    //compare all CVs with 1 job description
	public void compareCVWithJob(int jobId) throws SQLException
    {
		Statement stmt = sqlStatement();
		String strSelect = "SELECT jobSkill FROM job WHERE job.id='" + jobId + "'";
		String strSelect2 = "SELECT FROM COUNT(*) cv";
		ResultSet rset1 = stmt.executeQuery(strSelect);
		ResultSet rset2 = stmt.executeQuery(strSelect2);
		JobKeyWordsArray = Job.getSkills(jobId);  //JobKeyWordsArray will be conceptualised | spearhead | .... | ....
		for(int i = 0; i < JobKeyWordsArray.length; i++)
		{
			for(int j = 0; j < rset1.getInt(); j++)
			{
				
			}
		}
		
    }
    
    //compare all Jobs with 1 CV
    public void compareJobWithCV(int CvId)
    {
    	
    }
    
    
    public double calculatePercentage(int matchedWords, int totalWords)
    {	
    	return matchedWords/totalWords*100;	
    }
}
