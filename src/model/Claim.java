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
	
	private boolean ok;
	private boolean decided;
	
	public Claim(Client client, String text, int amount) {
		id = idHandler.get();
		date = new Date();
		this.client = client;
		this.text = text;
		this.complex = false;
		this.amount = amount;
		decided = false;
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
	
	public boolean isOK () throws UnsupportedOperationException {
		if (!decided) {
			throw new UnsupportedOperationException();
		}
		return ok;
	}
	
	public void setOK (boolean ok) {
		this.ok = ok;
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
		Claim other = (Claim) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
