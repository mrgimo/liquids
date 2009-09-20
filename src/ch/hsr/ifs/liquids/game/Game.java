package ch.hsr.ifs.liquids.game;

import javax.media.opengl.GL;

import ch.hsr.ifs.liquids.common.Movable;
import ch.hsr.ifs.liquids.common.Renderable;
import ch.hsr.ifs.liquids.util.Vector;

public class Game implements Renderable, Movable {

	protected PlayingField playingField;

	protected Player[] players;
	protected Particle[] particles;

	public Game(PlayingField playingField, Player[] players,
			Particle[] particles) {
		this.playingField = playingField;

		this.players = players;
		this.particles = particles;

		positionPlayers();
		positionParticles();
	}

	private void positionPlayers() {
		Vector center = getCenter();
		double radius = calcRadius();

		double angle = 2 * Math.PI / players.length;
		for (int i = 0; i < players.length; i++) {
			Vector position = calcPosition(center, radius, angle * i);

			players[i].device.setX(position.x);
			players[i].device.setY(position.y);
		}
	}

	private Vector getCenter() {
		float x = playingField.widthInPixels / 2;
		float y = playingField.heightInPixels / 2;

		return new Vector(x, y);
	}

	private double calcRadius() {
		int width = playingField.widthInPixels;
		int height = playingField.heightInPixels;

		double range = Math.min(width, height);

		return range * 4 / 10;
	}

	private void positionParticles() {
		for (Player player : players) {
			Particle[] particles = getParticlesFrom(player);

			int circle = 0;

			int parts = 0;
			int part = 0;

			int positioned = 0;
			while (positioned < particles.length) {
				if (part == parts) {
					circle++;

					parts = 4 * circle;
					part = 0;
				}

				double radius = circle * playingField.gridSize;
				double angle = 2 * Math.PI / parts * part;

				Vector position = calcParticlePosition(player, radius, angle);
				if (playingField.isAccessible(position)) {
					positionParticle(particles[positioned], position);
					positioned++;
				}

				part++;
			}
		}
	}

	private Particle[] getParticlesFrom(Player player) {
		int size = particles.length / players.length;
		Particle[] particles = new Particle[size];

		int i = 0;
		for (Particle particle : this.particles) {
			if (particle.player == player) {
				particles[i++] = particle;
			}
		}

		return particles;
	}

	private Vector calcParticlePosition(Player player, double r, double a) {
		float centerX = player.device.getX();
		float centerY = player.device.getY();

		Vector center = new Vector(centerX, centerY);

		return calcPosition(center, r, a);
	}

	private Vector calcPosition(Vector center, double radius, double angle) {
		float x = calcX(radius, angle);
		float y = calcY(radius, angle);

		return new Vector(center.x + x, center.y + y);
	}

	private float calcX(double radius, double angle) {
		return (float) (Math.cos(angle) * radius);
	}

	private float calcY(double radius, double angle) {
		return (float) (Math.sin(angle) * radius);
	}

	private void positionParticle(Particle particle, Vector position) {
		playingField.setParticle(particle, position);
		particle.position = position;
	}

	public void render(GL gl) {
		playingField.render(gl);

		for (Particle particle : particles) {
			particle.render(gl);
		}

		for (Player player : players) {
			player.render(gl);
		}
	}

	public void move() {
		for (Particle particle : particles) {
			particle.move();
		}
	}

}
