package common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CV {
<<<<<<< HEAD
	private int id;
	private String firstName;
	private String lastName;
	private int age;
	
	private Map<String, List<Word>> cvContentMap;
=======
	int id;
	String name;
	String education;
	String skills;
	String experience;
	String leadership;
	int age;
>>>>>>> master
	
	public boolean setId(int i) {
		id = i;
		cvContentMap = new HashMap<String, List<Word>>();
		return true;
	}
	public boolean setName(String nm) {
		name = nm;
		return true;
	}
	public boolean setExperience(String exp) {
		experience = exp;
		return true;
	}
	public boolean setEducation(String edu) {
		education = edu;
		return true;
	}
	public boolean setSkills(String sk) {
		skills = sk;
		return true;
	}
	public boolean setLeadership(String lead) {
		leadership = lead;
		return true;
	}
	public boolean setAge(int a) {
		age = a;
		return true;
	}
	public void setCvContentMap(Map<String, List<Word>> cvContentMap) {
		this.cvContentMap = cvContentMap;
	}
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getSkills() {
		return skills;
	}
	public String getExperience() {
		return experience;
	}
	public String getEducation() {
		return education;
	}
	public String getLeadership() {
		return leadership;
	}
	public int getAge() {
		return age;
	}
	public Map<String, List<Word>> getCvContentMap() {
		return cvContentMap;
	}
}