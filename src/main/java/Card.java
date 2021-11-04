
public class Card {
	private String suite;
	private int value;
	
	Card(String theSuite, int theValue) {
		this.suite = theSuite;
		this.value = theValue;
	}
	
	public String getSuite() {
		return this.suite;
	}
	public void setSuite(String suite) {
		this.suite = suite;
	}
	public int getValue() {
		return this.value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	public int getBaccaratValue() {
		if (value > 9) {
			return 0;
		} else {
			return value;
		}
	}
  }
