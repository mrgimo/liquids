package ch.hsr.ifs.liquids.logic;

import java.io.File;
import java.io.IOException;

import javax.media.opengl.GL;

import ch.hsr.ifs.liquids.common.Renderable;
import ch.hsr.ifs.liquids.util.Color;
import ch.hsr.ifs.liquids.util.Vector;
import ch.hsr.ifs.liquids.util.graphics.TextureUtil;
import ch.hsr.ifs.liquids.widgets.Window;

import com.sun.opengl.util.texture.Texture;

public final class UserInterface implements Renderable {

	private static final float OPAQUE = 0.5f;

	private static final String SCORE_PATH = "data/textures/score.png";
	private static final String WINNER_PATH = "data/textures/winner.png";

	private int numberOfParticles;

	private final Player[] players;

	private final Vector scorePosition;
	private final Vector scoreSize;

	private final Vector winnerPosition;
	private final Vector winnerSize;

	protected Player winner;

	private Texture scoreTexture;
	private Texture winnerTexture;

	private final Vector size = new Vector();
	private final Vector position = new Vector();

	public UserInterface(Player[] players) {
		this.players = players;

		calcNumberOfParticles(players);

		Vector windowSize = Window.getWindow().getSize();

		float x = windowSize.getX();
		float y = windowSize.getY();

		scorePosition = new Vector(x * 0.5f, y * 0.05f);
		scoreSize = new Vector(x * 0.8f, y * 0.01f);

		winnerPosition = new Vector(x * 0.5f, y * 0.5f);
		winnerSize = new Vector(x * 0.5f, y * 0.25f);
	}

	private void calcNumberOfParticles(Player[] players) {
		for (Player player : players) {
			numberOfParticles += player.numberOfParticles;
		}
	}

	public void init() throws IOException {
		scoreTexture = TextureUtil.loadTexture(new File(SCORE_PATH));
		winnerTexture = TextureUtil.loadTexture(new File(WINNER_PATH));
	}

	public final void render(final GL gl) {
		scoreTexture.bind();

		float xAlpha = scorePosition.getX() - scoreSize.getX() / 2;
		for (final Player player : players) {
			renderScore(gl, xAlpha, player);

			xAlpha += size.getX();
		}

		if (winner != null) {
			setColor(gl, winner.color);

			winnerTexture.bind();
			TextureUtil.renderTexture(winnerPosition, winnerSize, gl);
		}
	}

	private final void renderScore(final GL gl, float xAlpha, final Player player) {
		size.setX(calcWidth(player));
		size.setY(scoreSize.getY());

		position.setX(xAlpha + size.getX() / 2);
		position.setY(scorePosition.getY());

		setColor(gl, player.color);
		TextureUtil.renderTexture(position, size, gl);
	}

	private final float calcWidth(final Player player) {
		return scoreSize.getX() * (float) player.numberOfParticles
				/ numberOfParticles;
	}

	private final void setColor(final GL gl, final Color color) {
		final float r = color.getR();
		final float g = color.getG();
		final float b = color.getB();

		gl.glColor4f(r, g, b, OPAQUE);
	}

}
