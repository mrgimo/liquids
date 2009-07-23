package ch.hsr.ifs.liquid;

import javax.media.opengl.GL;

import processing.core.PApplet;
import processing.opengl.PGraphicsOpenGL;
import ch.hsr.ifs.liquid.controller.controllers.DefaultController;
import ch.hsr.ifs.liquid.view.core.elements.Screen;
import ch.hsr.ifs.liquid.view.screens.MenuScreen;

public class Liquid extends PApplet {
	private static final long serialVersionUID = -3265151160788933115L;

	private static final int FRAME_RATE = 60;
	
	private static final boolean V_SYNC = true;
	
	public static final int IN_GAME_BACKGROUND = 0xff000000;

	protected Screen screen;

	@Override
	public void setup() {
		size(super.screen.width*9/10, super.screen.height*9/10, OPENGL);
		hint(ENABLE_OPENGL_4X_SMOOTH);
		setVSync(V_SYNC);
		frameRate(FRAME_RATE);
		background(IN_GAME_BACKGROUND);
		
		new DefaultController(this);
		
		screen = new MenuScreen(this);
	}

	public void setVSync(boolean enable) {
		PGraphicsOpenGL pgl = (PGraphicsOpenGL) g;
		GL gl = pgl.beginGL();
		gl.setSwapInterval(enable ? 1 : 0);
		pgl.endGL();
	}

	public void changeScreen(Screen screen) {
		this.screen = screen;
	}

	@Override
	public void draw() {
		screen.drawOn(this);
	}
}
