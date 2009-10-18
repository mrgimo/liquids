package ch.hsr.ifs.liquids.helpers.factories;

import ch.hsr.ifs.liquids.helpers.Config;
import ch.hsr.ifs.liquids.logic.Particle;
import ch.hsr.ifs.liquids.logic.Player;
import ch.hsr.ifs.liquids.logic.PlayingField;

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

			float x, y;
			do {
				x = player.device.position.getX();
				y = player.device.position.getY();

				x = (float) (Math.cos(angle) * radius) + x;
				y = (float) (Math.sin(angle) * radius) + y;

				radius += 0.01;
				angle += 1 / radius;
			} while (!isAccessible(field, x, y));

			particles[i] = createParticle(field, player, x, y);
		}

		return particles;
	}

	private static void initConstants(Config config) {
		Particle.setDamage(config.particles.damage);
		Particle.setHealing(config.particles.healing);
		Particle.setSize(config.particles.size);
	}

	private static boolean isAccessible(PlayingField playingField, float x,
			float y) {
		int index = playingField.calcIndex(x, y);

		return playingField.get(index) == PlayingField.ACCESSIBLE;
	}

	private static Particle createParticle(PlayingField field, Player player,
			float x,
			float y) {
		Particle particle = new Particle(field, player, x, y);

		int index = field.calcIndex(x, y);
		field.set(particle, index);

		return particle;
	}

}
