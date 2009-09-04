package ch.hsr.ifs.liquids.game;

import javax.media.opengl.GL;

import ch.hsr.ifs.liquids.common.Movable;
import ch.hsr.ifs.liquids.common.Renderable;
import ch.hsr.ifs.liquids.util.Color;
import ch.hsr.ifs.liquids.util.Vector;

public class Particle implements Renderable, Movable {

	public static final int MAX_HEALTH = 150;
	public static final int MIN_HEALTH = 1;

	public static final int MAX_DAMAGE = 50;
	public static final int MIN_DAMAGE = 1;

	protected Player player;
	protected Vector position;

	protected Color color = new Color();

	protected int health;
	protected int damage;

	public static Particle[] createParticles(Player[] players) {
		int numberOfPlayers = players.length;
		int numberOfParticles = Config.particles.get("quantity");

		Particle[] particles = new Particle[numberOfPlayers * numberOfParticles];

		int i = 0;
		for (Player player : players) {
			for (int j = 0; j < numberOfParticles; j++) {
				Particle particle = new Particle();

				particle.player = player;

				particle.color.red = player.color.red;
				particle.color.green = player.color.green;
				particle.color.blue = player.color.blue;

				particle.setHealth(Config.particles.get("health"));
				particle.setDamage(Config.particles.get("damage"));

				particles[i++] = particle;
			}
		}

		return particles;
	}

	public void render(GL gl) {
		
	}

	public void move() {

	}

	protected void setHealth(int health) {
		if (health > MAX_HEALTH) {
			this.health = MAX_HEALTH;
		}

		if (health < MIN_HEALTH) {
			this.health = MIN_HEALTH;
		}

		this.health = health;
	}

	protected void setDamage(int damage) {
		if (damage > MAX_DAMAGE) {
			this.damage = MAX_DAMAGE;
		}

		if (damage < MAX_DAMAGE) {
			this.damage = MAX_DAMAGE;
		}

		this.damage = damage;
	}

}
