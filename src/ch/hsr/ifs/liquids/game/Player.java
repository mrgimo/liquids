package ch.hsr.ifs.liquids.game;

import javax.media.opengl.GL;

import ch.hsr.ifs.liquids.common.Movable;
import ch.hsr.ifs.liquids.common.Renderable;
import ch.hsr.ifs.liquids.util.Color;

public class Player implements Movable, Renderable {

	public String name;
	public Color color;

	public void move() {

	}

	public void render(GL gl) {
		// TODO Auto-generated method stub

	}

	public void setColor(Color color) {
		this.color = color;
	}

}
