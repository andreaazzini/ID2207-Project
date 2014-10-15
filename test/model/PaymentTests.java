package model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PaymentTests {
	private Payment payment;
	private Payment payment2;
	private Client client;
	private int amount;
	
	@Before
	public void setUp() {
		amount = 5000;
		client = new Client(0, "jan", "tomljanovic", 22, "jt@email.com", "07365873937", "KDJF3487KD", null);
		payment = new Payment(0, "21.5.2002.", client, amount);
		payment2 = new Payment(0, "21.1.2003.", client, 4000, true);
	}
	
	@Test
	public void getId() {
		Assert.assertEquals(0, payment.getId());
	}
	
	@Test
	public void getIbanTest() {
		Assert.assertEquals(payment.getIBAN(), client.getIBAN());
	}
	
	@Test
	public void setIbanTest() {
		String iban = "LSDUGH2183";
		payment.setIBAN(iban);
		Assert.assertEquals(payment.getIBAN(), iban);
	}
	
	@Test
	public void getClientTest() {
		Assert.assertEquals(payment.getClient(), client);
	}
	
	@Test
	public void getAmountTest() {
		Assert.assertEquals(payment.getAmount(), amount);
	}
	
	@Test
	public void isApprovedTest() {
		Assert.assertFalse(payment.isApproved());
		payment.approve();
		Assert.assertTrue(payment.isApproved());
	}
	
	@Test
	public void getDateTest () {
		Assert.assertEquals(payment.getDate(), "21.5.2002.");
	}
	
	@Test
	public void setDateTest () {
		payment.setDate("21.4.2002.");
		Assert.assertEquals(payment.getDate(), "21.4.2002.");
	}
	
	@Test
	public void hashcodeTest () {
		Assert.assertEquals(payment.hashCode(), payment2.hashCode());
	}
	
	@Test
	public void equalsTest () {
		Assert.assertEquals(payment, payment2);
	}
	
	
}
