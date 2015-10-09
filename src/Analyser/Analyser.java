public class Analyser 
{
	int matchedWords; 
	int totalWords;
	double percentage;
	
    //compare all CVs with 1 job description
    public void compareCVWithJob(CV cv)
    {
    	
    }
    
  //compare all Jobs with 1 CV
    public void compareJobWithCV()
    {
    	
    }
    
    
    public double calculatePercentage(int matchedWords, int totalWords)
    {	
    	return matchedWords/totalWords*100;	
    }
}
