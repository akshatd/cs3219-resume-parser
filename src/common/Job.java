package common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Job {
	
    int id;	
	private Map<String, List<Word>> jobContentMap;
	
	public Job(){
		jobContentMap = new HashMap<String, List<Word>>();
	}
	
	public boolean setId(int i) {
		id = i;
		return true;
	}	
	
	public void setJobContentMap(Map<String, List<Word>> contentMap) {
		this.jobContentMap = contentMap;
	}
	
	public int getId() {
		return id;
	}
	
	public Map<String, List<Word>> getJobContentMap() {
		return this.jobContentMap;
	}
}