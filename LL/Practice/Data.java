public class Data {

	private int num;
	private String testString;

	public Data() {
		num = 0;
		testString = "";
	}

	public Data(int num, String testString) {
		this.num = num;
		this.testString = testString;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getNum() {
		return num;
	}

	public void setTestString(String testString) {
		this.testString = testString;
	}

	public String getTestString() {
		return testString;
	}

	public String toString() {
		return "Data[number = " + num +  ", testString = " + testString + "]";
	}

}