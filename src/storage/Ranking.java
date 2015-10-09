package storage;

public class Ranking {
	CV cv;
	int jobId;
	double matchPercentage;
	
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