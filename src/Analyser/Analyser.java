package Analyser;
import java.sql.*;   // Use classes in java.sql package
import java.util.ArrayList;

import common.CV;
import common.Job;


public class Analyser 
{
	int matchedWords = 0; 
	int totalWords;
	double percentage;
	String jobKeyWords;
	String cvKeyWords;
	String [] jobKeyWordsArray;
	String [] cvKeyWordsArray;
	Job job;
	CV cv;
	
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
	
	public int totalWordsInJob(int jobId, String [] jobKeyWordsArray)
	{
		int count = 0;
		for(int i = 0; i < jobKeyWordsArray.length; i++)
		{
			count++;
		}
		return count;
	}
	
    //compare all CVs with 1 job description
	public void compareCVWithJob(int jobId) throws SQLException
    {
		Statement stmt = sqlStatement();
		String strSelect = "SELECT jobSkill FROM job WHERE job.id='" + jobId + "'";
		String strSelect2 = "SELECT FROM COUNT(*) cv";
		ResultSet rset1 = stmt.executeQuery(strSelect);
		ResultSet rset2 = stmt.executeQuery(strSelect2);
		jobKeyWords = job.getSkills();  //JobKeyWordsArray will be conceptualised | spearhead | .... | ....
		jobKeyWordsArray = jobKeyWords.split(",");
		totalWords = totalWordsInJob(jobId, jobKeyWordsArray);
		for(int i = 0; i < jobKeyWordsArray.length; i++)
		{
			for(int j = 0; j < NumOfCol; j++)
			{
				cvKeyWords = cv.getSkills();   // get
				cvKeyWordsArray = cvKeyWords.split(",");
				for(int k = 0; k < cvKeyWordsArray.length; k++)
				{
					if(jobKeyWordsArray[i] == cvKeyWordsArray[k])
					{
						matchedWords++;
					}
					else
					{
						continue;
					}
				}
				percentage = calculatePercentage(matchedWords, totalWords);
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
