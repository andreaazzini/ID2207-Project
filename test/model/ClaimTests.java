package model;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClaimTests {

	private Claim claim1;
	private Claim claim2;
	private Client client;
	private Car car;
	
	@Before
	public void setUp () {
		client = new Client(0, "afs", "sdf", 23, "asdf@adsf.com", "0735565888", "HS5343B", new ArrayList<Car>());
		car = new Car(0, client, "yugo", 5000, "super-mega");
		client.addCar(car);
		claim1 = new Claim(0, client, "This is a claim", 1000);
		claim2 = new Claim(1, client, "This is a complex claim", 10000, "T-centralen", "report");
	}
	
	@Test
	public void getIdTest() {
		Assert.assertEquals(0, claim1.getId());
		Assert.assertEquals(1, claim2.getId());
	}
	
	@Test
	public void getAmountTest () {
		Assert.assertEquals(claim1.getAmount(), 1000);
	}
	
	@Test
	public void getTextTest () {
		Assert.assertEquals(claim1.getText(), "This is a claim");
	}
	
	@Test
	public void getClientTest () {
		Assert.assertEquals(claim1.getClient(), client);
	}
	
	@Test
	public void getPoliceReportTest () {
		Assert.assertEquals(claim2.getPoliceReport(), "report");
	}
	
	@Test (expected = UnsupportedOperationException.class)
	public void getPoliceReport2Test () {
		Assert.assertEquals(claim1.getPoliceReport(), "bla");
	}
	
	@Test
	public void getPlaceTest () {
		Assert.assertEquals(claim2.getPlace(), "T-centralen" );
	}
	
	@Test (expected = UnsupportedOperationException.class)
	public void getPlace2Test () {
		Assert.assertEquals(claim1.getPlace(), "bla");
	}

	@Test (expected = UnsupportedOperationException.class)
	public void isOKTest () {
		Assert.assertEquals(claim1.isOK(), false);
	}
	
	public void isOK2Test () {
		claim1.setOK(true);
		Assert.assertEquals(claim1.isOK(), true);
	}
	
	public void setAmountTest () {
		claim2.setAmount(99);
		Assert.assertEquals(claim2.getAmount(), 99);
	}
	
	public void isComplexTest () {
		Assert.assertEquals(claim2.isComplex(), true);
	}
	
	
	public void isComplex2Test () {
		Assert.assertEquals(claim1.isComplex(), false);
	}
}
