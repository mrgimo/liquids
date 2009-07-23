package ch.hsr.ifs.liquid.view.core.elements;

import ch.hsr.ifs.liquid.view.core.Element;
import processing.core.PApplet;
import processing.core.PFont;

public class Button extends Element {
	public static final int DEFAULT_FONT_COLOUR = 0xffffffff;
	public static final int DEFAULT_BACKGROUND = 0xff000000;
	public static final int DEFAULT_BORDER = 0xffffffff;

	public static final int HOVER_FONT_COLOUR = 0xff000000;
	public static final int HOVER_BACKGROUND = 0xffffffff;
	public static final int HOVER_BORDER = 0xff000000;

	public String text;
	public PFont font;

	public int borderColour;
	public int backgroundColour;
	public int fontColour;

	public Button(String text, PFont font) {
		this.text = text;
		this.font = font;

		dimension.width = 100;
		dimension.height = 25;

		borderColour = DEFAULT_BORDER;
		backgroundColour = DEFAULT_BACKGROUND;
		fontColour = DEFAULT_FONT_COLOUR;
	}

	@Override
	public void drawOn(PApplet applet) {
		applet.stroke(borderColour);
		applet.fill(backgroundColour);
		applet.rect(position.x, position.y, dimension.width, dimension.height);

		applet.fill(fontColour);
		applet.textAlign(PApplet.CENTER, PApplet.CENTER);
		applet.textFont(font);
		applet.text(text, position.x, position.y, dimension.width,
				dimension.height);
	}
}
