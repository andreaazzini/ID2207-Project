package model;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CarTests {

	private Car car;
	private Client client;
	private Car car2;
	private Car car3;
	
	@Before
	public void setUp () {
		client = new Client(0, "a", "v", 18, "sdf@", "0735548969", "0014HB", new ArrayList<Car>());
		car = new Car(0, client, "Skoda", 200000, "normal");
		car2 = new Car(0, client, "Mazda", 4587, "Heavy insurance");
		car3 = new Car(1, client, "Toyota", 5615, "Insane insurance");
	}
	
	@Test
	public void getIdTest() {
		Assert.assertEquals(0, car.getId());
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
	public void getNameTest () {
		Assert.assertEquals(car.getName(), "Skoda");
	}
	
	@Test
	public void hashCodeTest () {
		Assert.assertEquals(car.hashCode(), car2.hashCode());
	}
	
	@Test
	public void equalsTest () {
		Assert.assertEquals(car, car2);
	}
	

	@Test
	public void equals2Test () {
		Assert.assertNotEquals(car, car3);
	}
	
	
	
	
	
}
