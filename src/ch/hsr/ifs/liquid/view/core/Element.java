package ch.hsr.ifs.liquid.view.core;

import processing.core.PApplet;
import ch.hsr.ifs.liquid.controller.core.Controller;
import ch.hsr.ifs.liquid.controller.core.interfaces.Controllable;
import ch.hsr.ifs.liquid.controller.core.listeners.CursorListener;
import ch.hsr.ifs.liquid.controller.core.listeners.InputListener;
import ch.hsr.ifs.liquid.util.Dimension;
import ch.hsr.ifs.liquid.util.Vector;
import ch.hsr.ifs.liquid.view.core.interfaces.Drawable;

public abstract class Element implements Drawable, Controllable {
	protected Component parent;

	public Vector position = new Vector();
	public Dimension dimension = new Dimension();

	protected void added() {
		position.x += parent.position.x;
		position.y += parent.position.y;
	}
	
	public void addCursorListener(CursorListener listener) {
		Controller.addCursorListener(listener);
	}
	
	public void addInputListener(InputListener listener) {
		Controller.addInputListener(listener);
	}

	public abstract void drawOn(PApplet applet);
}
