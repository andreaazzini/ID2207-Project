package model;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClientTests {
	private Client client;
	private List<Car> cars;
	
	@Before
	public void setUp () {
		cars = new ArrayList<Car>();
		client = new Client(0, "a", "v", 18, "sdf@", "0735548969", "0014HB", cars);
	}
	
	@Test
	public void getIdTest() {
		Assert.assertEquals(0, client.getId());
	}
	
	@Test
	public void getNameTest () {
		Assert.assertEquals(client.getName(), "a");
	}
	
	@Test
	public void getSurnameTest () {
		Assert.assertEquals(client.getSurname(), "v");
	}
	
	@Test
	public void getAgeTest() {
		Assert.assertEquals(client.getAge(), 18);
	}
	
	@Test
	public void setAgeTest() {
		client.setAge(19);
		Assert.assertEquals(client.getAge(), 19);
	}
	
	@Test
	public void getEmailTest() {
		Assert.assertEquals(client.getEmail(), "sdf@");
	}
	
	@Test
	public void setEmailTest() {
		String email = "email@email.com";
		client.setEmail(email);
		Assert.assertEquals(client.getEmail(), email);
	}
	
	@Test
	public void getPhoneTest() {
		Assert.assertEquals(client.getPhone(), "0735548969");
	}
	
	@Test
	public void setPhoneTest() {
		String phone = "0294328932";
		client.setPhone(phone);
		Assert.assertEquals(client.getPhone(), phone);
	}
	
	@Test
	public void getIBANTest() {
		Assert.assertEquals(client.getIBAN(), "0014HB");
	}
	
	@Test
	public void setIBANTest() {
		String iban = "KFDSF2134DSA";
		client.setIBAN(iban);
		Assert.assertEquals(client.getIBAN(), iban);
	}
	
	@Test
	public void addCarTest() {
		Car car = new Car(0, client, "Fiat", 300000, "normal");
		client.addCar(car);
		cars.add(car);
		Assert.assertEquals(client.getCars(), cars);
	}
	
	@Test
	public void removeCarTest() {
		Car car = new Car(0, client, "Fiat", 300000, "normal");
		client.addCar(car);
		client.removeCar(car);
		Assert.assertEquals(client.getCars(), cars);
	}
	
	@Test
	public void getCarsTest() {
		Assert.assertEquals(client.getCars(), cars);
	}
	
	@Test
	public void addClaimTest () {
		new Claim(1, client, "This is a claim", 3999);
		Assert.assertTrue(client.getClaims().size() == 1);
		Assert.assertTrue(client.getClaims().get(0).getText().equals("This is a claim"));
		Assert.assertTrue(client.getClaims().get(0).getAmount() == 3999);
	}
		
		
		

}
