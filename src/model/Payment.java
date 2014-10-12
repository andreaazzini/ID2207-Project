package model;

import java.util.Date;

public class Payment {
	
	private static IdHandler idHandler = new IdHandler();
	private int id;
	private Client client;
	private Date date;
	private int amount;
	private String IBAN;
	private boolean approved;
	
	public Payment(Client client, int amount) {
		this.id = idHandler.get();
		this.client = client;
		this.amount = amount;
		this.date = new Date();
		IBAN = client.getIBAN();
		approved = false;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
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
}
