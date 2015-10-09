package common;

public class CV {
	int id;
	String name;
	String education;
	String skills;
	String experience;
	String leadership;
	int age;
	
	public boolean setId(int i) {
		id = i;
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
}