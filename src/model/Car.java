package model;

public class Car {
	
	private IdHandler idHandler = new IdHandler();
	private int id;
	private Client owner;
	private String name;
	private int price;
	private String insurance;
	
	public Car(Client owner, String name, int price, String insurance) {
		owner.addCar(this);
		this.id = idHandler.get();
		this.owner = owner;
		this.name = name;
		this.price = price;
		this.insurance = insurance;
	}

	public int getId() {
		return id;
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
		Car other = (Car) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("%4d %11s    OWNER: %s %s", id, name, owner.getName(), owner.getSurname());
	}

}
