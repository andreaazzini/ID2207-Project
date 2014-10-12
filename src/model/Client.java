package model;

import java.util.ArrayList;
import java.util.List;

public class Client {
	
	private int id;
	private static IdHandler idHandler = new IdHandler();
	private String name;
	private String surname;
	private int age;
	private String email;
	private String phone;
	private List<Claim> claims;
	private List<Car> cars;
	private String IBAN;
	
	public Client(String name, String surname, int age, String email,
			String phone, String IBAN, List<Car> cars) {
		id = idHandler.get();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.phone = phone;
		this.setIBAN(IBAN);
		claims = new ArrayList<Claim>();
		this.cars = cars;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<Car> getCars() {
		return cars;
	}

	public String getIBAN() {
		return IBAN;
	}

	public void setIBAN(String iBAN) {
		IBAN = iBAN;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public List<Claim> getClaims() {
		return claims;
	}
	
	public void addCar(Car car) {
		this.cars.add(car);
	}
	
	public void removeCar(Car car) {
		this.cars.remove(car);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (id != other.id)
			return false;
		return true;
	}


} 
