package ch.hsr.ifs.liquids.game;

import javax.media.opengl.GL;

import ch.hsr.ifs.liquids.common.Renderable;

public class Game implements Renderable {

	protected Player[] players;
	protected Particle[] particles;

	protected PlayingField field;

	public Game() {
		players = Player.createPlayers();
		particles = Particle.createParticles(players);

		field = new PlayingField();
	}

	public void render(GL gl) {
		field.render(gl);

		for (Particle particle : particles) {
			particle.render(gl);
		}

		for (Player player : players) {
			player.render(gl);
		}
	}

}
