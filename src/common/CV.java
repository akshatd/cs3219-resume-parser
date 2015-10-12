package common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CV {
	
	private int id;	
	private Map<String, List<Word>> cvContentMap;
	
	public boolean setId(int i) {
		id = i;
		cvContentMap = new HashMap<String, List<Word>>();
		return true;
	}
	
	public void setCvContentMap(Map<String, List<Word>> cvContentMap) {
		this.cvContentMap = cvContentMap;
	}
	
	public int getId() {
		return id;
	}
	public Map<String, List<Word>> getCvContentMap() {
		return cvContentMap;
	}
}