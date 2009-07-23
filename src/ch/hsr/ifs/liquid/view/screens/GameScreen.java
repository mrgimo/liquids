package ch.hsr.ifs.liquid.view.screens;

import processing.core.PApplet;
import ch.hsr.ifs.liquid.Liquid;
import ch.hsr.ifs.liquid.model.Game;
import ch.hsr.ifs.liquid.view.core.elements.Screen;

public class GameScreen extends Screen {
	
	private Game game;
	
	public GameScreen(PApplet applet) {
		super(applet);
		
		game = new Game(applet);
	}

	@Override
	public void drawOn(PApplet applet) {
		applet.background(Liquid.IN_GAME_BACKGROUND);
		game.update(applet);
	}

}
