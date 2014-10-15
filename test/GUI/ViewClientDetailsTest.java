package GUI;

import java.awt.Frame;

import javax.swing.JButton;

import model.Claim;
import junit.framework.TestCase;

public class ViewClientDetailsTest extends TestCase {

	public void test() throws Exception {
		AuthenticationTest auth = new AuthenticationTest();
		auth.test();
		
		LogoutTest logoutTest = new LogoutTest();
		
		MainView main = logoutTest.findMainFrame();
		
		JButton viewAllClaims = (JButton) TestUtils.getChildNamed(main, "ViewAllClaims");
		viewAllClaims.doClick();
		TestUtils.delay();
		
		DisplayList<Claim> listClaims = findListOfClaimsFrame();
		

	}
	
	@SuppressWarnings("unchecked")
	public DisplayList<Claim> findListOfClaimsFrame () {
		DisplayList<Claim> list = null;
		Frame[] frames = Frame.getFrames();
		for (Frame f : frames) {
			if (f instanceof DisplayList<?>) {
				list = (DisplayList<Claim>) f;
				break;
			}
		}
		return list;
	}
	
}
