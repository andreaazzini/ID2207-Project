package model;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GarageTests {

	private Garage garage;
	private Car car1;
	private Car car2;
	private Car car3;
	private Client client;
	
	
	@Before
	public void setUp () {
		garage = new Garage();
		client = new Client(0, "adsf", "sf", 24, "sf@afd", "188165181", "8s4d8f", new ArrayList<Car>());
		car1 = new Car(0, client, "toyota", 450000, "high");
		car2 = new Car(1, client, "honda", 550000, "high");
		car3 = new Car(2, client, "suzuki", 300000, "medium");
	}
	
	@Test
	public void idTest() {
		Assert.assertFalse(car1.getId() == car2.getId());
		Assert.assertFalse(car1.getId() == car3.getId());
		Assert.assertFalse(car2.getId() == car3.getId());
	}
	
	@Test
	public void getNotRepairedTest () {
		garage.addNotRepairedCar(car1);
		garage.addNotRepairedCar(car2);
		garage.addNotRepairedCar(car3);
		Assert.assertArrayEquals(garage.getNotRepairedCars().toArray(), new Car[]{car1, car2, car3});
	}
	
	@Test
	public void setRepairedTest () {
		garage.addNotRepairedCar(car1);
		garage.addNotRepairedCar(car2);
		garage.addNotRepairedCar(car3);
		garage.setRepaired(car1);
		Assert.assertArrayEquals(garage.getNotRepairedCars().toArray(), new Car[]{car2, car3});
		Assert.assertArrayEquals(garage.getRepairedCars().toArray(), new Car[]{car1});
	}
	
	@Test
	public void setDeliveredTest () {
		garage.addNotRepairedCar(car1);
		garage.addNotRepairedCar(car2);
		garage.addNotRepairedCar(car3);
		garage.setRepaired(car1);
		garage.setDelivered(car1);
		Assert.assertArrayEquals(garage.getNotRepairedCars().toArray(), new Car[]{car2, car3});
		Assert.assertArrayEquals(garage.getRepairedCars().toArray(), new Car[]{});
	}
	
}
