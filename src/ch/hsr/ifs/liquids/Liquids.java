package ch.hsr.ifs.liquids;

import static ch.hsr.ifs.liquids.helpers.factories.PlayingFieldFactory.createPlayingField;
import static ch.hsr.ifs.liquids.helpers.factories.ParticleFactory.createParticles;
import static ch.hsr.ifs.liquids.helpers.factories.PlayerFactory.createPlayers;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import ch.hsr.ifs.liquids.engines.Physics;
import ch.hsr.ifs.liquids.engines.Renderer;
import ch.hsr.ifs.liquids.helpers.Config;
import ch.hsr.ifs.liquids.logic.Logic;
import ch.hsr.ifs.liquids.logic.Particle;
import ch.hsr.ifs.liquids.logic.Player;
import ch.hsr.ifs.liquids.logic.PlayingField;
import ch.hsr.ifs.liquids.util.Vector;
import ch.hsr.ifs.liquids.widgets.Window;

public class Liquids {

	private static final String CONFIG_PATH = "data/liquids.config";

	private final Window window;
	private final Logic logic;
	private final Physics physics;

	public Liquids() {
		Config config = Config.load(CONFIG_PATH);

		window = createWindow(config);
		logic = createLogic(config);

		window.addGLEventListener(new Renderer(logic));
		window.open();

		physics = new Physics(logic);
		physics.start();
	}

	private Logic createLogic(Config config) {
		PlayingField field = createPlayingField(config);

		Player[] players = createPlayers(config, field);
		Particle[] particles = createParticles(config, field, players);

		return new Logic(field, players, particles);
	}

	private Window createWindow(Config config) {
		Window window = Window.getWindow();

		int x = (int) (Window.SCREEN_WIDTH / 10);
		int y = (int) (Window.SCREEN_HEIGHT / 10);

		window.setPosition(new Vector(x, y));
		window.setSize(new Vector(x * 8, y * 8));

		window.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				physics.stop();
				Liquids.this.window.close();
				System.exit(0);
			}

		});

		return window;
	}

	public static void main(String[] args) {
		new Liquids();
	}

}
