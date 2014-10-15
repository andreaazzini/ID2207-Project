package GUI;

import java.awt.Frame;
import java.io.IOException;

import javax.swing.JButton;

import junit.framework.TestCase;

public class LogoutTest extends TestCase {

	private MainView main;
	
	public void test () throws IOException {
		AuthenticationTest auth = new AuthenticationTest();
		auth.test();
		TestUtils.delay();
		main = findMainFrame();
		JButton logout = (JButton) TestUtils.getChildNamed(main, "LogoutButton");
		logout.doClick();
		
		TestUtils.delay();
		
	}
	
	public MainView findMainFrame () {
		MainView mainview = null;
		Frame[] frames = Frame.getFrames();
		for (Frame f : frames) {
			if (f instanceof MainView) {
				mainview = (MainView) f;
				break;
			}
		}
		return mainview;
	}
}
