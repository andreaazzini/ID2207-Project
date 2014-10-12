package model;

public class Car {
	
	private Client owner;
	private String name;
	private int price;
	private String insurance;
	
	public Car(Client owner, String name, int price, String insurance) {
		this.owner = owner;
		this.name = name;
		this.price = price;
		this.insurance = insurance;
	}

	public Client getOwner() {
		return owner;
	}

	public String getInsurance() {
		return insurance;
	}

	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}
}
