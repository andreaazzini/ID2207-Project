package model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PaymentTests {
	private Payment payment;
	private Client client;
	private int amount;
	
	@Before
	public void setUp() {
		amount = 5000;
		client = new Client(0, "jan", "tomljanovic", 22, "jt@email.com", "07365873937", "KDJF3487KD", null);
		payment = new Payment(0, client, amount);
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
}
