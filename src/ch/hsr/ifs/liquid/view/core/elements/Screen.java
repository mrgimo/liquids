package ch.hsr.ifs.liquid.view.core.elements;

import ch.hsr.ifs.liquid.view.core.Component;
import processing.core.PApplet;

public abstract class Screen extends Component {
	public Screen(PApplet applet) {
		position.x = 0;
		position.y = 0;
		
		dimension.width = applet.width;
		dimension.height = applet.height;
	}
	
	@Override
	protected void added() {
		throw new RuntimeException("Screens could not be added to other components!");
	}
}
