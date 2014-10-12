package model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IdHandlerTests {

	private IdHandler idHandler;
	
	@Before
	public void setUp () {
		idHandler = new IdHandler();
	}
	
	@Test
	public void getTest () {
		Assert.assertEquals(idHandler.get(), 0);
		Assert.assertEquals(idHandler.get(), 1);
		Assert.assertEquals(idHandler.get(), 2);
		Assert.assertEquals(idHandler.get(), 3);
	}
}
