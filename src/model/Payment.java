package model;

import java.util.Date;

public class Payment {
	private Client client;
	private Date date;
	private int amount;
	private String IBAN;
	private boolean approved;
	
	public Payment(Client client, int amount) {
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

	public boolean isApproved() {
		return approved;
	}
	
	public void approve() {
		approved = true;
	}
}
