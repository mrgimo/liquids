package ch.hsr.ifs.liquids.logic;

import java.io.IOException;

import javax.media.opengl.GL2;

import ch.hsr.ifs.liquids.common.Moveable;
import ch.hsr.ifs.liquids.common.Renderable;

public final class Logic implements Renderable, Moveable {

	private final PlayingField field;

	private final Player[] players;
	private final Particle[] particles;

	private final UserInterface gui;

	public Logic(PlayingField field, Player[] players, Particle[] particles) {
		this.field = field;

		this.players = players;
		this.particles = particles;

		gui = new UserInterface(players);
	}

	public void init(GL2 gl) throws IOException {
		field.init(gl);

		Player.staticInit(gl);
		Particle.staticInit(gl);

		gui.init(gl);
	}

	public final void render(final GL2 gl) {
		field.render(gl);

		Particle.texture.bind(gl);
		for (final Particle particle : particles) {
			particle.render(gl);
		}

		Player.texture.bind(gl);
		for (final Player player : players) {
			player.render(gl);

			if (player.numberOfParticles == particles.length) {
				gui.winner = player;
			}
		}

		gui.render(gl);
	}

	public final void move() {
		for (final Particle particle : particles) {
			particle.move();
		}
	}

}
