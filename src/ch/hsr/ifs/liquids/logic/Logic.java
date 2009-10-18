package ch.hsr.ifs.liquids.logic;

import java.io.IOException;

import javax.media.opengl.GL;

import ch.hsr.ifs.liquids.common.Moveable;
import ch.hsr.ifs.liquids.common.Renderable;

public class Logic implements Renderable, Moveable {

	protected PlayingField field;

	protected Player[] players;
	protected Particle[] particles;

	protected Score score;
	protected Winner winner;

	public Logic(PlayingField field, Player[] players, Particle[] particles) {
		this.field = field;

		this.players = players;
		this.particles = particles;

		score = new Score(players);
		winner = new Winner();
	}

	public void init() throws IOException {
		field.init();

		Player.loadTexture();
		Particle.loadTexture();

		score.init();
		winner.init();
	}

	public void render(GL gl) {
		field.render(gl);

		Particle.getTexture().bind();
		for (Particle particle : particles) {
			particle.render(gl);
		}

		score.render(gl);

		Player.getTexture().bind();
		for (Player player : players) {
			player.render(gl);

			if (player.numberOfParticles == particles.length) {
				winner.setColor(player.color);
				winner.render(gl);

				Player.getTexture().bind();
			}
		}
	}

	public final void move() {
		for (Particle particle : particles) {
			particle.move();
		}
	}

}
