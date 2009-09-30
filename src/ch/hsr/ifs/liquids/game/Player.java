package ch.hsr.ifs.liquids.game;

import javax.media.opengl.GL;

import ch.hsr.ifs.liquids.common.Renderable;
import ch.hsr.ifs.liquids.cursor.Cursor;
import ch.hsr.ifs.liquids.util.Color;

public class Player implements Renderable {

	private static final float OPAQUE = 0.5f;

	protected Color color;
	protected Cursor cursor;

	protected int numberOfParticles;

	public void render(GL gl) {
		float r = color.red;
		float g = color.green;
		float b = color.blue;

		gl.glColor4f(r, g, b, OPAQUE);

		cursor.render(gl);
	}

}
