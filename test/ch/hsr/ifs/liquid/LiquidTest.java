package ch.hsr.ifs.liquid;

import org.junit.Before;
import org.junit.Test;

import ch.hsr.ifs.liquid.view.core.elements.Screen;
import static org.junit.Assert.*;

public class LiquidTest {
	
	private Liquid liquid;
	
	@Before
	public void initialise(){
		liquid = new Liquid();
	}
	
	@Test
	public void testChangeScreen(){
		liquid.changeScreen(null);
		assertNull(liquid.screen);
		
		Screen screen = new Screen(liquid){};
		liquid.changeScreen(screen);
		assertEquals(screen, liquid.screen);
	}
}
