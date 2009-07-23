package ch.hsr.ifs.liquid.view.core.elements;

import processing.core.PApplet;
import ch.hsr.ifs.liquid.view.core.Element;

public class Cursor extends Element{
	public static final int BORDER_COLOUR = 0xff000000;
	
	private static final float WIDTH = 25;
	private static final float HEIGHT = 25;
	
	public int colour;
	
	public void drawOn(PApplet applet) {
		applet.fill(colour);
		applet.ellipse(position.x, position.y, WIDTH, HEIGHT);
	}

}
