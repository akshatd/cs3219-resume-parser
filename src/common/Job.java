package common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Job {
	
    int id;	
	private Map<String, List<Word>> contentMap;
	
	public Job(){
		contentMap = new HashMap<String, List<Word>>();
	}
	
	public boolean setId(int i) {
		id = i;
		return true;
	}	
	
	public void setContentMap(Map<String, List<Word>> contentMap) {
		this.contentMap = contentMap;
	}
	
	public int getId() {
		return id;
	}
	
	public Map<String, List<Word>> getContentMap() {
		return this.contentMap;
	}
}