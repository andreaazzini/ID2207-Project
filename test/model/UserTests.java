package model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserTests {

	private User user1;
	private User user2;

	@Before
	public void setUp() {
		user1 = new User("username", "password", Employment.CLAIM_HANDLER_A, "Name", "Surname");
		user2 = new User("username", "pass", Employment.FINANACIAL_EMPLOYEE, "ime", "prezime");
	}

	@Test
	public void getEmpolyementTest() {
		Assert.assertEquals(user1.getEmployment(), Employment.CLAIM_HANDLER_A);
	}
	
	@Test
	public void setEmpolyementTest() {
		user1.setEmployment(Employment.CLAIM_HANDLER_B);
		Assert.assertEquals(user1.getEmployment(), Employment.CLAIM_HANDLER_B);
	}
	
	@Test
	public void getUsernameTest () {
		Assert.assertEquals(user1.getUsername(), "username");
	}
	

	@Test
	public void getPasswordTest () {
		Assert.assertEquals(user1.getPassword(), "password");
	}
	
	
	@Test
	public void getNameTest () {
		Assert.assertEquals(user1.getName(), "Name");
	}
	
	@Test
	public void getSurnameTest () {
		Assert.assertEquals(user1.getSurname(), "Surname");
	}
	
	@Test
	public void hashcodeTest () {
		Assert.assertEquals(user1, user2);
	}
	
	@Test
	public void equalsTest () {
		Assert.assertEquals(user1, user2);
	}
	
	
	


}
