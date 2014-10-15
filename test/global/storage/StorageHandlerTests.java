package global.storage;

import java.io.IOException;
import java.util.ArrayList;

import model.Car;
import model.Claim;
import model.Client;
import model.Employment;
import model.Payment;
import model.User;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import global.storage.StorageHandler;

public class StorageHandlerTests {

	private Client client;
	private Claim claim;
	private Car car;
	private Payment payment;
	
	@Before
	public void setUp () {
		client = new Client(10, "afs", "sdf", 23, "asdf@adsf.com", "0735565888", "HS5343B", new ArrayList<Car>());
		car = new Car(0, client, "yugo", 5000, "super-mega");
		claim = new Claim(10, "5.7.2000.", client, "Text", 1000, car);
		payment = new Payment(9, "21.5.2002.", client, 20020);
	}
	
	
	@Test
	public void loadingUsersTest() throws IOException {
		StorageHandler.loadUsers("storage/users.txt");
		Assert.assertTrue(StorageHandler.getUsers().containsValue(
				new User("jantom", "jantompass", Employment.CLAIM_HANDLER_A, "Jan", "Tomljanović")));
	}
	
	@Test
	public void loadingCarsTest () throws IOException {
		StorageHandler.loadCars("storage/cars.txt");
		Assert.assertTrue(StorageHandler.getCars().containsValue(new Car(1, null, "Honda", 10000, "Insurance")));
	}
	
	@Test
	public void loadingClaimsTest () throws IOException {
		StorageHandler.loadCars("storage/cars.txt");
		StorageHandler.loadClaims("storage/claims.txt");
		Assert.assertTrue(StorageHandler.getClaims().containsKey(0));
	}
	
	@Test
	public void loadingClients () throws IOException {
		StorageHandler.loadCars("storage/cars.txt");
		StorageHandler.loadClaims("storage/claims.txt");
		StorageHandler.loadClients("storage/clients.txt");
		Assert.assertTrue(StorageHandler.getClients().containsKey(2));
	}
	
	@Test
	public void loadingPayments () throws IOException {
		StorageHandler.loadAll();
		Assert.assertTrue(StorageHandler.getPayments().containsKey(2));
	}
	
	@Test
	public void getBiggestClaimIdTest () throws IOException {
		StorageHandler.loadAll();
		Assert.assertEquals(StorageHandler.getBiggestClaimId(), 5);
	}
	
	@Test
	public void getBiggestPaymentIdTest () throws IOException {
		StorageHandler.loadAll();
		Assert.assertEquals(StorageHandler.getBiggestPaymentId(), 3);
	}
	
	@Test
	public void addNewClaimTest () throws IOException {
		StorageHandler.loadAll();
		StorageHandler.addNewClaim(claim);
		Assert.assertEquals(StorageHandler.getBiggestClaimId(), 10);
		StorageHandler.getClaims().remove(10);
	}
	
	@Test
	public void addNewPaymentTest () throws IOException {
		StorageHandler.loadAll();
		StorageHandler.addNewPayment(payment);
		Assert.assertEquals(StorageHandler.getBiggestPaymentId(), 9);
		StorageHandler.getPayments().remove(9);
	}
	
	
}