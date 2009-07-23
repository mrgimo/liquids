package ch.hsr.ifs.liquid.view.screens;

import processing.core.PApplet;
import ch.hsr.ifs.liquid.Liquid;
import ch.hsr.ifs.liquid.controller.core.listeners.CursorListener;
import ch.hsr.ifs.liquid.controller.listeners.buttons.PlayButtonListener;
import ch.hsr.ifs.liquid.view.core.elements.Button;
import ch.hsr.ifs.liquid.view.core.elements.Screen;

public class MenuScreen extends Screen {

	private static final String FONT_PATH = "../data/fonts/CourierNew-12.vlw";

	public MenuScreen(PApplet applet) {
		super(applet);

		Button button = new Button("play", applet.loadFont(FONT_PATH));

		button.position.x = dimension.width / 4;
		button.position.y = dimension.height / 4;

		CursorListener listener = new PlayButtonListener((Liquid) applet,
				button);
		button.addCursorListener(listener);

		add(button);
	}

}
