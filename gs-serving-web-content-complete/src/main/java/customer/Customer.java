package customer;

public class Customer {
	private String first_name;
	private String last_name;
	
	public Customer(String first, String last) {
		this.first_name = first;
		this.last_name = last;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	
	@Override
	public String toString() {
		return String.format("%s %s", first_name,last_name);
	}
}
