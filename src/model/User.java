package model;

public class User {

	private String username;
	private String password;
	private Employment employment;
	private String name;
	private String surname;

	public User(String username, String password, Employment employment, String name, String surname) {
		this.username = username;
		this.password = password;
		this.employment = employment;
		this.name = name;
		this.surname = surname;
	}

	public Employment getEmployment() {
		return employment;
	}

	public void setEmployment(Employment employment) {
		this.employment = employment;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}
	
	
	
	
}
