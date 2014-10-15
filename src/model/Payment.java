package model;

public class Payment {
	
	private int id;
	private Client client;
	private String date;
	private int amount;
	private String IBAN;
	private boolean approved;
	
	public Payment(int id, String date, Client client, int amount) {
		this.id = id;
		this.client = client;
		this.amount = amount;
		this.date = date;
		IBAN = client.getIBAN();
		approved = false;
	}

	public Payment(int id, String date, Client client, int amount, boolean approved) {
		this(id, date, client, amount);
		this.approved = approved;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getIBAN() {
		return IBAN;
	}

	public void setIBAN(String iBAN) {
		IBAN = iBAN;
	}

	public Client getClient() {
		return client;
	}
	
	public int getAmount() {
		return amount;
	}

	public int getId() {
		return id;
	}

	public boolean isApproved() {
		return approved;
	}
	
	public void approve() {
		approved = true;
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
		Payment other = (Payment) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return String.format("%4d %13s %13s %7dSEK", id, client.getName(), client.getSurname(), amount);
	}
}
