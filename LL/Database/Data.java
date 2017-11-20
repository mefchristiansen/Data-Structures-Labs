public class Data {

	private String firstName;
	private String lastName;
	private int age;
	private boolean isStudent;

	public Data() {
		firstName = "";
		lastName = "";
		age = 0;
		isStudent = false;
	}

	public Data(String firstName, String lastName, int age, boolean isStudent) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.isStudent = isStudent;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean getIsStudent() {
		return isStudent;
	}

	public void setIsStudent(boolean isStudent) {
		this.isStudent = isStudent;
	}

	public String printData() {
		String retString = "Entry: " + firstName + " " + lastName + ", " + age + ", ";
		if (isStudent) {
			retString += "student";
		}
		else {
			retString += "not a student";
		}
		return retString;
	}


}