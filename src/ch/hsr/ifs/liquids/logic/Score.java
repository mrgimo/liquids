package ch.hsr.ifs.liquids.logic;

import java.io.File;
import java.io.IOException;

import javax.media.opengl.GL;

import com.sun.opengl.util.texture.Texture;

import ch.hsr.ifs.liquids.common.Renderable;
import ch.hsr.ifs.liquids.util.Vector;
import ch.hsr.ifs.liquids.util.graphics.TextureUtil;
import ch.hsr.ifs.liquids.widgets.Window;

public class Score implements Renderable {

	private static final String TEXTURE_PATH = "data/textures/score.png";

	private static final float OPAQUE = 0.5f;

	protected Player[] players;

	private Vector position;
	private Vector size;

	protected int numberOfParticles;

	private Texture texture;

	public Score(Player[] players) {
		this.players = players;

		calcNumberOfParticles(players);

		Vector windowSize = Window.getWindow().getSize();

		float x = windowSize.getX() / 10;
		float y = windowSize.getY() / 100;

		position = new Vector(x, y);
		size = new Vector(x * 8, y);
	}

	private void calcNumberOfParticles(Player[] players) {
		for (Player player : players) {
			numberOfParticles += player.numberOfParticles;
		}
	}

	public void init() throws IOException {
		texture = TextureUtil.loadTexture(new File(TEXTURE_PATH));
	}

	public void render(GL gl) {
		texture.bind();

		float widthSum = 0;
		for (Player player : players) {
			setColor(gl, player);

			float width = calcWidth(player);

			float x1 = position.getX() + widthSum;
			float y1 = position.getY();

			float x2 = x1 + width;
			float y2 = y1 + size.getY();

			gl.glBegin(GL.GL_TRIANGLE_STRIP);

			gl.glTexCoord2f(1, 1);
			gl.glVertex3f(x2, y2, 0);

			gl.glTexCoord2f(0, 1);
			gl.glVertex3f(x1, y2, 0);

			gl.glTexCoord2f(1, 0);
			gl.glVertex3f(x2, y1, 0);

			gl.glTexCoord2f(0, 0);
			gl.glVertex3f(x1, y1, 0);

			gl.glEnd();

			widthSum += width;
		}
	}

	private int calcWidth(Player player) {
		float factor = (float) player.numberOfParticles / numberOfParticles;
		return (int) (size.getX() * factor);
	}

	private void setColor(GL gl, Player player) {
		float r = player.color.getR();
		float g = player.color.getG();
		float b = player.color.getB();

		gl.glColor4f(r, g, b, OPAQUE);
	}

}
