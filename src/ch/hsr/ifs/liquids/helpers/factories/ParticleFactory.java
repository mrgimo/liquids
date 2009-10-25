package ch.hsr.ifs.liquids.helpers.factories;

import ch.hsr.ifs.liquids.helpers.Config;
import ch.hsr.ifs.liquids.logic.Particle;
import ch.hsr.ifs.liquids.logic.Player;
import ch.hsr.ifs.liquids.logic.PlayingField;
import ch.hsr.ifs.liquids.util.Vector;

public class ParticleFactory {

	public static Particle[] createParticles(Config config, PlayingField field,
			Player[] players) {
		initConstants(config);

		int particlesPerPlayer = config.particles.quantity;
		int numberOfParticles = particlesPerPlayer * players.length;

		Particle[] particles = new Particle[numberOfParticles];
		Player previousPlayer = null;

		double radius = 0;
		double angle = 0;
		for (int i = 0; i < particles.length; i++) {
			Player player = players[i / particlesPerPlayer];
			if (player != previousPlayer) {
				radius = 0;
				angle = 0;

				previousPlayer = player;
			}

			Vector p = new Vector();
			do {
				p.setX(player.device.position.getX());
				p.setY(player.device.position.getY());

				p.setX((float) (Math.cos(angle) * radius) + p.getX());
				p.setY((float) (Math.sin(angle) * radius) + p.getY());

				radius += 0.01;
				angle += 1 / radius;
			} while (!isAccessible(field, p));

			particles[i] = createParticle(field, player, p);
		}

		return particles;
	}

	private static void initConstants(Config config) {
		Particle.damage = config.particles.damage;
		Particle.healing = config.particles.healing;

		float size = config.particles.size;
		Particle.size = new Vector(size, size);
	}

	private static boolean isAccessible(PlayingField field, Vector position) {
		int index = field.calcIndex(position);

		return field.get(index) == PlayingField.ACCESSIBLE;
	}

	private static Particle createParticle(PlayingField field, Player player,
			Vector position) {
		Particle particle = new Particle(field, player, position);

		int index = field.calcIndex(position);
		field.set(particle, index);

		return particle;
	}

}
