package common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CV {
	private int id;
	private String firstName;
	private String lastName;
	
	private Map<String, List<Word>> cvContentMap;
	
	public boolean setId(int i) {
		id = i;
		cvContentMap = new HashMap<String, List<Word>>();
		return true;
	}
	public boolean setFirstName(String nm) {
		firstName = nm;
		return true;
	}
	public boolean setLastName(String nm) {
		lastName = nm;
		return true;
	}
	
	public void setCvContentMap(Map<String, List<Word>> cvContentMap) {
		this.cvContentMap = cvContentMap;
	}
	
	public int getId() {
		return id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public Map<String, List<Word>> getCvContentMap() {
		return cvContentMap;
	}
}