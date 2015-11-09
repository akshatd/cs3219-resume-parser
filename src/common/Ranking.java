package common;

public class Ranking {
	private CV cv;
	private int jobId;
	private double matchPercentage;
	
	public boolean setCV(CV c) {
		cv = c;
		return true;
	}
	public boolean setJobId(int ji) {
		jobId = ji;
		return true;
	}
	public boolean setMatchPercentage(double mp) {
		matchPercentage = mp;
		return true;
	}
	
	public CV getCV(){
		return cv;		
	}
	public int getJobId() {
		return jobId;
	}
	public double getMatchPercentage() {
		return matchPercentage;
	}
}