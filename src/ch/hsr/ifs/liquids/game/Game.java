package ch.hsr.ifs.liquids.game;

import javax.media.opengl.GL;

import ch.hsr.ifs.liquids.common.Movable;
import ch.hsr.ifs.liquids.common.Renderable;
import ch.hsr.ifs.liquids.util.Vector;

public class Game implements Renderable, Movable {

	protected PlayingField playingField;

	protected Score score;

	protected Player[] players;
	protected Particle[] particles;

	public void setup() {
		positionPlayers();
		positionParticles();
	}

	private void positionPlayers() {
		float x = Config.SCREEN_WIDTH / 2;
		float y = Config.SCREEN_HEIGHT / 2;

		double radius = calcRadius();
		double angle = calcAngle();
		for (int i = 0; i < players.length; i++) {
			Vector position = calcPosition(x, y, radius, angle * i);

			players[i].cursor.position.x = position.x;
			players[i].cursor.position.y = position.y;
		}
	}

	private double calcRadius() {
		int width = Config.SCREEN_WIDTH;
		int height = Config.SCREEN_HEIGHT;

		double range = Math.min(width, height);

		return range * 4 / 10;
	}

	private double calcAngle() {
		return 2 * Math.PI / players.length;
	}

	private void positionParticles() {
		Player player = null;

		double radius = 0;
		double angle = 0;
		for (Particle particle : particles) {
			if (particle.player != player) {
				player = particle.player;

				radius = 0;
				angle = 0;
			}

			Vector position;
			do {
				float x = player.cursor.position.x;
				float y = player.cursor.position.y;

				position = calcPosition(x, y, radius, angle);

				radius += 0.01;
				angle += 1 / radius;
			} while (!isAccessible(position));

			positionParticle(particle, position);
		}

	}

	private Vector calcPosition(float x, float y, double radius, double angle) {
		x = calcX(radius, angle) + x;
		y = calcY(radius, angle) + y;

		return new Vector(x, y);
	}

	private float calcX(double radius, double angle) {
		return (float) (Math.cos(angle) * radius);
	}

	private float calcY(double radius, double angle) {
		return (float) (Math.sin(angle) * radius);
	}

	private boolean isAccessible(Vector position) {
		int index = playingField.positionToIndex(position);

		return playingField.isAccessible(index);
	}

	private void positionParticle(Particle particle, Vector position) {
		int index = playingField.positionToIndex(position);

		particle.position = position;
		playingField.setParticle(particle, index);
	}

	public final void render(GL gl) {
		playingField.render(gl);

		for (Particle particle : particles) {
			particle.render(gl);
		}

		score.render(gl);

		for (Player player : players) {
			player.render(gl);
		}

	}

	public final void move() {
		for (Particle particle : particles) {
			particle.move();
		}
	}

}
