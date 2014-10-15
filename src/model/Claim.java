package model;

public class Claim {

	private int id;
	private String text;
	private Client client;
	private String date;
	private boolean complex;
	private int amount;
	private String place;
	private String policeReport;
	private Car car;

	private boolean ok;
	private boolean decided;

	public Claim(int id, String date, Client client, String text, int amount, Car car) {
		if (client != null) {
			client.addClaim(this);
		}
		this.id = id;
		this.date = date;
		this.client = client;
		this.text = text;
		this.complex = false;
		this.amount = amount;
		this.car = car;
		decided = false;
	}

	public Claim(int id, String date, Client client, String text, int amount, Car car, String place, String policeReport) {
		this(id, date, client, text, amount, car);
		complex = true;
		this.place = place;
		this.policeReport = policeReport;
	}

	public Claim(int id, String date, Client client, String text, int amount, Car car, boolean decided, boolean ok) {
		this(id, date, client, text, amount, car);
		this.ok = ok;
		this.decided = decided;
	}

	public Claim(int id, String date, Client client, String text, int amount, Car car, String place,
			String policeReport, boolean decided, boolean ok) {
		this(id, date, client, text, amount, car, place, policeReport);
		this.ok = ok;
		this.decided = decided;
	}

	public boolean isDecided() {
		return decided;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
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

	public void setClient(Client client) {
		this.client = client;
	}

	public boolean isComplex() {
		return complex;
	}

	public String getPlace() throws UnsupportedOperationException {
		if (!this.complex)
			throw new UnsupportedOperationException();
		return place;
	}

	public String getPoliceReport() throws UnsupportedOperationException {
		if (!this.complex)
			throw new UnsupportedOperationException();
		return policeReport;
	}

	public boolean isOK() throws UnsupportedOperationException {
		if (!decided) {
			throw new UnsupportedOperationException();
		}
		return ok;
	}

	public void setOK(boolean ok) {
		decided = true;
		this.ok = ok;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
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

	@Override
	public String toString() {
		String approved = decided ? (ok ? "Approved" : "Not approved") : "Ongoing";
		return String.format("%4d %13s %13s %12s", id, client.getName(), client.getSurname(), approved);
	}

}
