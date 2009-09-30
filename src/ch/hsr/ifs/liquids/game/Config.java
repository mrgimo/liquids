package ch.hsr.ifs.liquids.game;

import java.awt.Toolkit;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.ho.yaml.Yaml;

import ch.hsr.ifs.liquids.cursor.AccelerometerCursor;
import ch.hsr.ifs.liquids.cursor.Cursor;
import ch.hsr.ifs.liquids.cursor.KeyboardCursor;
import ch.hsr.ifs.liquids.cursor.MouseCursor;
import ch.hsr.ifs.liquids.util.Color;

public class Config {

	private static final String CONFIG_PATH = "data/liquids.config";

	public static final int SCREEN_WIDTH;
	public static final int SCREEN_HEIGHT;

	static {
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		SCREEN_WIDTH = toolkit.getScreenSize().width;
		SCREEN_HEIGHT = toolkit.getScreenSize().height;
	}

	private enum DefaultCursor {

		MOUSE(MouseCursor.class), KEYBOARD(KeyboardCursor.class), ACCELEROMETER(
				AccelerometerCursor.class);

		private Class<? extends Cursor> clazz;

		private DefaultCursor(Class<? extends Cursor> clazz) {
			this.clazz = clazz;
		}

		public Cursor createInstance() {
			try {
				return clazz.newInstance();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

	}

	private enum DefaultColor {

		BLACK(new Color(0, 0, 0)), WHITE(new Color(255, 255, 255)), RED(
				new Color(255, 0, 0)), GREEN(new Color(0, 255, 0)), BLUE(
				new Color(0, 0, 255)), YELLOW(new Color(255, 255, 0)), CYAN(
				new Color(0, 255, 255)), MAGENTA(new Color(255, 0, 255));

		public Color color;

		private DefaultColor(Color color) {
			this.color = color;
		}

	}

	public HashMap<String, Object> map;
	public HashMap<String, Map<String, String>> maps;

	public HashMap<String, String>[] players;
	public HashMap<String, Integer> particles;

	public static Config loadConfig() {
		try {
			File file = new File(CONFIG_PATH);

			return Yaml.loadType(file, Config.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Game createGame() {
		Game game = new Game();

		game.playingField = createPlayingField();

		game.players = createPlayers();
		game.particles = createParticles(game);

		game.score = new Score(game.players);

		return game;
	}

	private PlayingField createPlayingField() {
		return new PlayingField(boundsPath(), texturePath(), gridSize());
	}

	private Player[] createPlayers() {
		Player[] players = new Player[numberOfPlayers()];

		for (int i = 0; i < players.length; i++) {
			Player player = new Player();

			player.color = playerColor(i);
			player.cursor = playerDevice(i);

			player.numberOfParticles = particlesQuantity();

			player.cursor.plug();

			players[i] = player;
		}

		return players;
	}

	private Particle[] createParticles(Game game) {
		int quantity = particlesQuantity();
		int totalQuantity = quantity * game.players.length;

		Particle[] particles = new Particle[totalQuantity];

		for (int i = 0; i < game.players.length; i++) {
			for (int j = 0; j < quantity; j++) {
				Particle particle = new Particle();

				particle.player = game.players[i];
				particle.playingField = game.playingField;

				particle.health = particlesHealth();
				particle.healing = particlesHealing();
				particle.damage = particlesDamage();

				particle.width = gridSize();
				particle.height = gridSize();

				particles[i * quantity + j] = particle;
			}
		}

		return particles;
	}

	private int gridSize() {
		return (Integer) map.get("gridSize");
	}

	private String boundsPath() {
		return maps.get(mapName()).get("bounds");
	}

	private String texturePath() {
		return maps.get(mapName()).get("texture");
	}

	private String mapName() {
		return (String) map.get("name");
	}

	private int numberOfPlayers() {
		return this.players.length;
	}

	private Color playerColor(int index) {
		HashMap<String, String> player = players[index];

		for (DefaultColor color : DefaultColor.values()) {
			String name = player.get("color").toUpperCase();
			if (color.toString().equals(name)) {
				return color.color;
			}
		}

		throw new RuntimeException();
	}

	private Cursor playerDevice(int index) {
		HashMap<String, String> player = players[index];

		for (DefaultCursor defaultCursor : DefaultCursor.values()) {
			String name = player.get("device").toUpperCase();
			if (defaultCursor.toString().equals(name)) {
				Cursor cursor = defaultCursor.createInstance();
				
				cursor.width = gridSize() * 5;
				cursor.height = gridSize() * 5;
				
				return cursor;
			}
		}

		throw new RuntimeException();
	}

	private int particlesQuantity() {
		return particles.get("quantity");
	}

	private int particlesHealth() {
		return particles.get("health");
	}

	private int particlesHealing() {
		return particles.get("healing");
	}

	private int particlesDamage() {
		return particles.get("damage");
	}

}
