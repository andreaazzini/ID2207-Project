package global.session;

import java.io.IOException;

import global.storage.StorageHandler;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.Employment;
import model.User;

public class SessionHandlerTests {

	private User user;
	
	@Before
	public void setUp () throws IOException {
		user = new User("mario", "mariopass", Employment.CLAIM_HANDLER_B, "Mario", "Markich");
		StorageHandler.loadAll();
	}
	
	@Test
	public void authenticationTest () {
		Assert.assertTrue(SessionHandler.authenticate(user.getUsername(), user.getPassword()));
	}
	
	@Test
	public void currentUserTest () {
		Assert.assertTrue(SessionHandler.authenticate(user.getUsername(), user.getPassword()));
		Assert.assertTrue(SessionHandler.getCurrentUser().equals(user));
	}
	
	@Test
	public void logoutTest () {
		Assert.assertTrue(SessionHandler.authenticate(user.getUsername(), user.getPassword()));
		Assert.assertTrue(SessionHandler.getCurrentUser().equals(user));
		SessionHandler.logout();
		Assert.assertTrue(SessionHandler.getCurrentUser() == null);
	}
	
}
