package ch.hsr.ifs.liquid.controller.listeners.buttons;

import ch.hsr.ifs.liquid.Liquid;
import ch.hsr.ifs.liquid.controller.core.events.CursorEvent;
import ch.hsr.ifs.liquid.controller.core.listeners.CursorListener;
import ch.hsr.ifs.liquid.view.core.elements.Button;
import ch.hsr.ifs.liquid.view.screens.GameScreen;

public class PlayButtonListener implements CursorListener {

	private Liquid applet;
	private Button button;

	public PlayButtonListener(Liquid applet, Button button) {
		this.applet = applet;
		this.button = button;
	}

	public void cursorClicked(CursorEvent event) {
		int x = event.getX();
		int y = event.getY();

		if (hasIntersection(x, y)) {
			applet.changeScreen(new GameScreen(applet));
		}
	}

	public void cursorMoved(CursorEvent event) {
		int x = event.getX();
		int y = event.getY();

		if (hasIntersection(x, y)) {
			button.backgroundColour = Button.HOVER_BACKGROUND;
			button.borderColour = Button.HOVER_FONT_COLOUR;
			button.fontColour = Button.HOVER_FONT_COLOUR;
		} else {
			button.backgroundColour = Button.DEFAULT_BACKGROUND;
			button.borderColour = Button.DEFAULT_FONT_COLOUR;
			button.fontColour = Button.DEFAULT_FONT_COLOUR;
		}
	}

	private boolean hasIntersection(int x, int y) {
		return x >= button.position.x
				&& x <= button.position.x + button.dimension.width
				&& y >= button.position.y
				&& y <= button.position.y + button.dimension.height;
	}

	public void cursorDragged(CursorEvent event) {
		return;
	}

	public void cursorPressed(CursorEvent event) {
		return;
	}

	public void cursorReleased(CursorEvent event) {
		return;
	}
}
