package GUI;

import java.awt.Component;
import java.awt.Container;
import java.awt.Frame;

import javax.swing.JFrame;

public class TestUtils {

	private static final int DELAY = 2000;
	
	public static Component getChildNamed (Component parent, String name) {
		if (parent instanceof Container) {
			Component[] comps = ((Container) parent).getComponents();
			if (comps == null || comps.length == 0) {
				return null;
			}
			for (Component comp : comps){
				if (!(comp.getName() == null) && comp.getName().equals(name)) {
					return comp;
				}
			}
			for (Component comp : comps) {
				Component result = getChildNamed(comp, name);
				if (result != null) {
					return result;
				}
			}
		}
		return null;
	}
	
	public static void delay () {
		try {
			Thread.sleep(DELAY);
		} catch (InterruptedException ignored) {}
	}
	
	
}
