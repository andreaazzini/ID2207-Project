package model;

import java.util.Date;

public class Claim {
	private int id;
	private static IdHandler idHandler = new IdHandler();
	private String text;
	private Client client;
	private Date date;
	private boolean complex;
	private int amount;
	private String place;
	private String policeReport;
	
	public Claim(Client client, String text, int amount) {
		id = idHandler.get();
		date = new Date();
		this.client = client;
		this.text = text;
		this.complex = false;
		this.amount = amount;
	}
	
	public Claim(Client client, String text, int amount, String place, String policeReport) {
		this(client, text, amount);
		complex = true;
		this.place = place;
		this.policeReport = policeReport;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public Client getClient() {
		return client;
	}

	public boolean isComplex() {
		return complex;
	}

	public String getPlace() throws UnsupportedOperationException {
		if (!this.complex) throw new UnsupportedOperationException();
		return place;
	}

	public String getPoliceReport() throws UnsupportedOperationException {
		if (!this.complex) throw new UnsupportedOperationException();
		return policeReport;
	}
}
