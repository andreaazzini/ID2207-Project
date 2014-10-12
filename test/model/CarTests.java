package model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CarTests {

	private Car car;
	private Client client;
	
	@Before
	public void setUp () {
		client = new Client("a", "v", 18, "sdf@", "0735548969", "0014HB", null);
		car = new Car(client, "Skoda", 200000, "normal");
	}
	
	@Test
	public void getOwnerTest () {
		Assert.assertEquals(car.getOwner(), client);
	}
	
	@Test
	public void setPriceTest () {
		car.setPrice(100000);
		Assert.assertEquals(car.getPrice(), 100000);
	}
	
	@Test
	public void setInsuranceTest () {
		car.setInsurance("Bla bal");
		Assert.assertEquals(car.getInsurance(), "Bla bal");
	}
	
	@Test
	public void getIdTest () {
		Assert.assertEquals(car.getId(), 0);
	}
	
	@Test
	public void getNameTest () {
		Assert.assertEquals(car.getName(), "Skoda");
	}
	
	
}
