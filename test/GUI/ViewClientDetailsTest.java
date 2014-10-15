package GUI;

import java.awt.Frame;
import java.awt.Robot;
import java.awt.event.InputEvent;

import javax.swing.JButton;
import javax.swing.JList;

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
		
		@SuppressWarnings("unchecked")
		JList<String> list = (JList<String>) TestUtils.getChildNamed(listClaims, "JList");
		
		Robot rob = new Robot();
		int x = list.getLocationOnScreen().x;
		int y = list.getLocationOnScreen().y;
		rob.mouseMove(x+30, y+70);
		rob.mousePress(InputEvent.BUTTON1_MASK);
		rob.mouseRelease(InputEvent.BUTTON1_MASK);
		
		TestUtils.delay();
		
		ClaimView claim = findClaimViewFrame();
		
		JButton clientDetails = (JButton) TestUtils.getChildNamed(claim, "ClientButton");
		clientDetails.doClick();
		
		TestUtils.delay();

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
	
	public ClaimView findClaimViewFrame () {
		ClaimView claimView = null;
		Frame[] frames = Frame.getFrames();
		for (Frame f : frames) {
			if (f instanceof ClaimView) {
				claimView = (ClaimView) f;
				break;
			}
		}
		return claimView;
	}
	
}
