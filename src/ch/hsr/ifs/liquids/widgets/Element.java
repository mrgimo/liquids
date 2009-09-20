package ch.hsr.ifs.liquids.widgets;

import ch.hsr.ifs.liquids.common.Renderable;
import ch.hsr.ifs.liquids.util.Vector;

public abstract class Element implements Renderable {

	protected int width;
	protected int height;

	protected Vector position;

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Vector getPosition() {
		return position;
	}

}
