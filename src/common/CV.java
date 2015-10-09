package common;

public class CV {
	int id;
	String firstName;
	String lastName;
	int age;
	
	public boolean setId(int i) {
		id = i;
		return true;
	}
	public boolean setFirstName(String fName) {
		firstName = fName;
		return true;
	}
	public boolean setLastName(String lName) {
		lastName = lName;
		return true;
	}
	public boolean setAge(int a) {
		age = a;
		return true;
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
	public int getAge() {
		return age;
	}
}