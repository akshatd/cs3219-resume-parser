package storage;

public class Job {
	
    int id;
	String title;
	String skills;
	String education;
	String location;
	String experience;
	
	public boolean setId(int i) {
		id = i;
		return true;
	}
	public boolean setTitle(String t) {
		title = t;
		return true;
	}
	public boolean setSkills(String s) {
		skills = s;
		return true;
	}
	public boolean setEducation(String ed) {
		education = ed;
		return true;
	}
	public boolean setLocation(String l) {
		location = l;
		return true;
	}
	public boolean setExperience(String ex) {
		experience = ex;
		return true;
	}
	
	public int getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getSkills() {
		return skills;
	}
	public String getEducation() {
		return education;
	}
	public String getLocation() {
		return location;
	}
	public String getExperience() {
		return experience;
	}
}