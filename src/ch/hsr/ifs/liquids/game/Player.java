package ch.hsr.ifs.liquids.game;

import ch.hsr.ifs.liquids.common.Colorizable;
import ch.hsr.ifs.liquids.common.Positionable;
import ch.hsr.ifs.liquids.util.Color;
import ch.hsr.ifs.liquids.util.Vector;

public class Player implements Positionable, Colorizable {

	protected Vector position;
	protected Color color;

	public Vector getPosition() {
		return position;
	}

	public void setPosition(Vector position) {
		this.position = position;
	}

	public Color getColor(Color color) {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
