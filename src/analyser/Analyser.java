package analyser;
import java.sql.*;   // Use classes in java.sql package
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.CV;
import common.Job;
import common.Ranking;
import common.Word;
import storage.Storage;

public class Analyser 
{
	private static int matchedWords = 0; 
	private static int totalWords;
	private static double percentage;
	private static String jobKeyWords;
	private static String cvKeyWords;
	private static String [] jobKeyWordsArray;
	private static String [] cvKeyWordsArray;
	private static Job job;
	private static CV cv;
	
    
    public static void analyseCV(int CVId) {
    	CV cv = Storage.getCV(CVId);
    	ArrayList<Job> jobsArray = Storage.getAllJobs();
    	
    	ArrayList<Ranking> rankArray = new ArrayList<Ranking>();
    	
    	for (int x = 0; x < jobsArray.size(); x++) {
    		Job job = jobsArray.get(x);
    		double matchPercentage = calculateMatchPercentage(cv, job);
    		Ranking rank = new Ranking();
    		rank.setCV(cv);
    		rank.setJobId(job.getId());
    		rank.setMatchPercentage(matchPercentage);
    		rankArray.add(rank);
    	}
    	Storage.saveRanking(rankArray);
    }
    
    public static void analyseJob(int JobId) {
    	Job job = Storage.getJob(JobId);
    	ArrayList<CV> CVsArray = Storage.getAllCVs();
    	
    	ArrayList<Ranking> rankArray = new ArrayList<Ranking>();
    	
    	for (int x = 0; x < CVsArray.size(); x++) {
    		CV cv = CVsArray.get(x);
    		double matchPercentage = calculateMatchPercentage(cv, job);
    		Ranking rank = new Ranking();
    		rank.setCV(cv);
    		rank.setJobId(job.getId());
    		rank.setMatchPercentage(matchPercentage);
    		rankArray.add(rank);
    	}
    	Storage.saveRanking(rankArray);
    }
    
    private static double calculateMatchPercentage(CV cv, Job job) {
    	Map<String, List<Word>> cvContent = cv.getCvContentMap();
    	Map<String, List<Word>> jobContent = job.getJobContentMap();
    	
    	double matchPercentage = 0;
    	double matches = 0;
    	double numberOfKeywords = 0;
    	
//    	for(Map.Entry<String, List<Word>> cvEntry : cvContent.entrySet()) {
//			List<Word> cvWordList = cvEntry.getValue();
//			for(int z = 0; z < cvWordList.size(); z++) {
//				outer: for(Map.Entry<String, List<Word>> entry : jobContent.entrySet()) {
//					numberOfKeywords = 0;
//		    		String key = entry.getKey();
//		    		List<Word> wordList = entry.getValue();
//		    		System.out.println(wordList);
//		    		for (int y = 0; y < wordList.size(); y++) {
//		    			if(cvWordList.get(z).toString().equalsIgnoreCase(wordList.get(y).toString())){
//    						matches += 1.0; 
//    						break outer;
//    					}
//		    		}
//		    		numberOfKeywords += wordList.size();
//				}
//			}
//    	}
    	
    	for(Map.Entry<String, List<Word>> entry : jobContent.entrySet()) {
    		String key = entry.getKey();
    		List<Word> wordList = entry.getValue();
    		System.out.println(wordList);
    		for (int y = 0; y < wordList.size(); y++) {
    			outer: for(Map.Entry<String, List<Word>> cvEntry : cvContent.entrySet()) {
    				List<Word> cvWordList = cvEntry.getValue();
    				for(int z = 0; z < cvWordList.size(); z++) {
    					if(cvWordList.get(z).toString().equalsIgnoreCase(wordList.get(y).toString())){
    						matches += 1.0; 
    						break outer;
    					}
    				}
    			}
//    			if(cvContent.get(key)!= null && cvContent.get(key).contains(wordList.get(y))) {
//    				List<Word> cvWordList = cvContent.get(key);
//    				for(int z = 0; z < cvWordList.size(); z++) {
//    					if(cvWordList.get(z).toString().equalsIgnoreCase(wordList.get(y).toString())){
//    						matches += 1.0; 
//    					}
//    				}
//    				
//    			}
    		}
			numberOfKeywords += wordList.size();
    	}
    	
    	matchPercentage = matches/numberOfKeywords;
    	
    	System.out.println("Matching keywords: " + matches);
    	System.out.println("Total Keywords: " + numberOfKeywords);
    	System.out.println("Match Percentage for CV" + cv.getId() + " and Job" + job.getId() 
    			+ " is " + matchPercentage);
    	
    	return matchPercentage;
    }
}
