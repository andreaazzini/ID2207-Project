package GUI;

import java.io.IOException;

import global.session.SessionHandler;
import global.storage.StorageHandler;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import junit.framework.TestCase;

public class AuthenticationTest extends TestCase {

	private Authentication auth;
	
	public void initialize () throws IOException {
		StorageHandler.loadAll();
		auth = new Authentication();
		auth.setVisible(true);
	}

	public void test() throws IOException {
		initialize();

		JTextField usr = (JTextField) TestUtils.getChildNamed(auth, "UsernameField");
		JPasswordField pass = (JPasswordField) TestUtils.getChildNamed(auth, "PasswordField");
		TestUtils.delay();
		usr.setText("admin");
		usr.postActionEvent();
		TestUtils.delay();
		pass.setText("adminpass");
		pass.postActionEvent();

		assertEquals(new String(pass.getPassword()), "adminpass");
		assertEquals(usr.getText(), "admin");

		TestUtils.delay();
		
		JButton ok = (JButton) TestUtils.getChildNamed(auth, "OK");
		ok.doClick();
		
		TestUtils.delay();

		assertEquals(SessionHandler.getCurrentUser().getUsername(), "admin");
	}
		
}
