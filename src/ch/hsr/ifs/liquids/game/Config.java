package ch.hsr.ifs.liquids.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import org.ho.yaml.Yaml;

import ch.hsr.ifs.liquids.devices.Accelerometer;
import ch.hsr.ifs.liquids.devices.Device;
import ch.hsr.ifs.liquids.devices.Keyboard;
import ch.hsr.ifs.liquids.devices.Mouse;
import ch.hsr.ifs.liquids.util.Color;

public class Config {

	private static final String CONFIG_PATH = "data/liquids.config";
	private static final String ERROR_MESSAGE = "reading config at '"
			+ CONFIG_PATH + "' has failed";

	private static final Map<String, Class<? extends Device>> devices;
	private static final Map<String, Color> colors;

	static {
		devices = new HashMap<String, Class<? extends Device>>();
		devices.put("mouse", Mouse.class);
		devices.put("keyboard", Keyboard.class);
		devices.put("accelerometer", Accelerometer.class);

		colors = new HashMap<String, Color>();
		colors.put("black", new Color(0, 0, 0));
		colors.put("white", new Color(255, 255, 255));
		colors.put("red", new Color(255, 0, 0));
		colors.put("green", new Color(0, 255, 0));
		colors.put("blue", new Color(0, 0, 255));
		colors.put("yellow", new Color(255, 255, 0));
		colors.put("cyan", new Color(0, 255, 255));
		colors.put("magenta", new Color(255, 0, 255));
	}

	public HashMap<String, Object> map;
	public HashMap<String, Map<String, String>> maps;

	public HashMap<String, String>[] players;
	public HashMap<String, Integer> particles;

	public static Game createGame() {
		Config config = null;
		try {
			config = Yaml.loadType(new File(CONFIG_PATH), Config.class);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(ERROR_MESSAGE, e);
		}

		PlayingField playingField = createPlayingField(config);

		Player[] players = createPlayers(config);
		Particle[] particles = createParticles(config, players, playingField);

		return new Game(playingField, players, particles);
	}

	private static PlayingField createPlayingField(Config config) {
		int gridSize = getGridSize(config);

		String name = getPlayingFieldName(config);
		String boundsPath = getBoundsPath(config, name);
		String texturePath = getTexturePath(config, name);

		return new PlayingField(boundsPath, texturePath, gridSize);
	}

	private static Player[] createPlayers(Config config) {
		Player[] players = new Player[config.players.length];

		for (int i = 0; i < players.length; i++) {
			Player player = new Player();

			player.color = getPlayerColor(config, i);
			player.device = getPlayerDevice(config, i);
			player.numberOfParticles = getParticlesQuantity(config);

			player.device.plug();

			players[i] = player;
		}

		return players;
	}

	private static Particle[] createParticles(Config config, Player[] players,
			PlayingField playingField) {
		int quantity = getParticlesQuantity(config);
		int health = getParticlesHealth(config);
		int healing = getParticlesHealing(config);
		int damage = getParticlesDamage(config);

		Particle[] particles = new Particle[quantity * players.length];
		for (int i = 0; i < players.length; i++) {
			Player player = players[i];
			for (int j = 0; j < quantity; j++) {
				Particle particle = new Particle();

				particle.player = player;
				particle.playingField = playingField;

				particle.setHealth(health);
				particle.setHealing(healing);
				particle.setDamage(damage);

				particles[i * quantity + j] = particle;
			}
		}

		return particles;
	}

	private static int getGridSize(Config config) {
		return (Integer) config.map.get("gridSize");
	}

	private static String getPlayingFieldName(Config config) {
		return (String) config.map.get("name");
	}

	private static String getBoundsPath(Config config, String playingFieldName) {
		return config.maps.get(playingFieldName).get("bounds");
	}

	private static String getTexturePath(Config config, String playingFieldName) {
		return config.maps.get(playingFieldName).get("texture");
	}

	private static Color getPlayerColor(Config config, int index) {
		String color = config.players[index].get("color");
		return colors.get(color);
	}

	private static Device getPlayerDevice(Config config, int index) {
		String device = config.players[index].get("device");

		try {
			return devices.get(device).newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Device not found!", e);
		}
	}

	private static Integer getParticlesQuantity(Config config) {
		return config.particles.get("quantity");
	}

	private static Integer getParticlesHealth(Config config) {
		return config.particles.get("health");
	}

	private static Integer getParticlesHealing(Config config) {
		return config.particles.get("healing");
	}

	private static Integer getParticlesDamage(Config config) {
		return config.particles.get("damage");
	}

}
