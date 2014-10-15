package model;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClaimTests {

	private Claim claim1;
	private Claim claim2;
	private Claim claim3;
	private Client client;
	private Car car;
	
	@Before
	public void setUp () {
		client = new Client(0, "afs", "sdf", 23, "asdf@adsf.com", "0735565888", "HS5343B", new ArrayList<Car>());
		car = new Car(0, client, "yugo", 5000, "super-mega");
		client.addCar(car);
		
		
		claim1 = new Claim(0, "5.7.2000.", client, "Text", 1000, car);
		claim2 = new Claim(1, "4.12.1993", client, "Again text", 2000, car, false, false);
		claim3 = new Claim(2, "12.12.2012.", client, "Claim text", 100, car, "sweden", "report", false, true);
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
		Assert.assertEquals(claim1.getText(), "Text");
	}
	
	@Test
	public void getClientTest () {
		Assert.assertEquals(claim1.getClient(), client);
	}
	
	@Test
	public void getPoliceReportTest () {
		Assert.assertEquals(claim3.getPoliceReport(), "report");
	}
	
	@Test (expected = UnsupportedOperationException.class)
	public void getPoliceReport2Test () {
		Assert.assertEquals(claim1.getPoliceReport(), "bla");
	}
	
	@Test
	public void getPlaceTest () {
		Assert.assertEquals(claim3.getPlace(), "sweden" );
	}
	
	@Test (expected = UnsupportedOperationException.class)
	public void getPlace2Test () {
		Assert.assertEquals(claim1.getPlace(), "bla");
	}

	@Test (expected = UnsupportedOperationException.class)
	public void isOKTest () {
		Assert.assertEquals(claim1.isOK(), false);
	}
	
	@Test
	public void isOK2Test () {
		claim1.setOK(true);
		Assert.assertEquals(claim1.isOK(), true);
		Assert.assertTrue(claim1.isDecided());
	}
	
	@Test
	public void setAmountTest () {
		claim2.setAmount(99);
		Assert.assertEquals(claim2.getAmount(), 99);
	}
	
	@Test
	public void isComplexTest () {
		Assert.assertEquals(claim3.isComplex(), true);
	}
	
	@Test
	public void isComplex2Test () {
		Assert.assertEquals(claim1.isComplex(), false);
	}
	
	@Test
	public void getDateTest () {
		Assert.assertEquals(claim1.getDate(), "5.7.2000.");
	}
	
	@Test
	public void setDateTest () {
		claim2.setDate("9.3.2001.");
		Assert.assertEquals(claim2.getDate(), "9.3.2001.");
	}
	
	@Test
	public void hashCodeTest () {
		Assert.assertNotEquals(claim1.hashCode(), claim2.hashCode());
	}
	
	@Test
	public void hashCodeTest2 () {
		Claim claim4 = new Claim(0, "12.12.1212.", client, "sdf", 1232, car, "asdff", "ddfg");
		Assert.assertEquals(claim1.hashCode(), claim4.hashCode());
	}
	
	@Test
	public void equalsTest () {
		Assert.assertNotEquals(claim1, claim2);
	}
	
	@Test
	public void equalsTest2 () {
		Claim claim4 = new Claim(0, "12.12.1212.", client, "sdf", 1232, car, "asdff", "ddfg");
		Assert.assertEquals(claim1, claim4);
	}
	
	
}
