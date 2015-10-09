
public class Analyser 
{
	
	
	/*
	 * get CVId from storage
	 * CV compare against all jobs
	 * calculate match percentage 
	 * and store in database 
	 */
	/*void analyseCV(int CVId) 
	{
	
		
	}*/
	/*
	 * get JobId from storage
	 * Job compare against all CVs
	 * calculate match percentage 
	 * and store in database 
	 */
    /*void analyseJob(int jobId)
    {
    	
	
    }*/
    //compare all CVs with 1 job description
    void compareCVWithJob(int CVId,int JobId)
    {
    	
    	/*int matchedWords = getNoOfMatchedWords(CVId,JobId);  /* getNoOfMatchedWords func which belongs 
    	to some other class, tells me the num of matched words for that particular CVID and
    	the particular JobID*/
    	        
    	/*int totalWords = getNoOfTotalWords(JobId);/* getNoOfTotalWords func which belongs 
    	to some other class, tells me the total number of words in the particular Job 
    	description*/
    	
    	/*float percentage = calculatePercentage(matchedWords, totalWords); /*calculates the
    	percentage for matched words (both CV and Job descr) and total words in Job*/
    }
    
  //compare all Jobs with 1 CV
    void compareJobWithCV(int CVId,int JobId)
    {
    	
    	/*int matchedWords = getNoOfMatchedWords(CVId,JobId);  /* getNoOfMatchedWords func which belongs 
    	to some other class, tells me the num of matched words for that particular CVID and
    	the particular JobID*/
    	        
    	/*int totalWords = getNoOfTotalWords(CVId);/* getNoOfTotalWords func which belongs 
    	to some other class, tells me the total number of words in the particular Job 
    	description*/
    	
    	/*float percentage = calculatePercentage(matchedWords, totalWords); /*calculates the
    	percentage for matched words (both CV and Job descr) and total words in Job*/
    }
    
    
    float calculatePercentage(int matchedWords, int totalWords)
    {	
    	return matchedWords/totalWords*100;	
    }
}
