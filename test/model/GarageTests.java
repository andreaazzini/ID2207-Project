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
		client = new Client("adsf", "sf", 24, "sf@afd", "188165181", "8s4d8f", new ArrayList<Car>());
		car1 = new Car(client, "toyota", 450000, "high");
		car2 = new Car(client, "honda", 550000, "high");
		car3 = new Car(client, "suzuki", 300000, "medium");
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
