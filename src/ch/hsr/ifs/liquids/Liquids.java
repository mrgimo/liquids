package ch.hsr.ifs.liquids;

import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import ch.hsr.ifs.liquids.engines.PhysicsEngine;
import ch.hsr.ifs.liquids.engines.RenderingEngine;
import ch.hsr.ifs.liquids.game.Config;
import ch.hsr.ifs.liquids.game.Game;

public class Liquids {

	private Frame frame;

	private RenderingEngine renderingEngine;
	private PhysicsEngine physicsEngine;

	private Game game = Config.loadConfig().createGame();

	public Liquids() {
		game.setup();

		frame = new JFrame();
		setupFrame();

		renderingEngine = new RenderingEngine(frame);
		setupRenderingEngine();

		physicsEngine = new PhysicsEngine();
		setupPhysicsEngine();

		frame.setVisible(true);
	}

	private void setupFrame() {
		frame.addWindowListener(createWindowListener());

		frame.setUndecorated(true);
		frame.setAlwaysOnTop(true);

		setFullscreen(frame);
	}

	private WindowAdapter createWindowListener() {
		return new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				frame.setVisible(false);

				renderingEngine.stop();
				physicsEngine.stop();

				System.exit(0);
			}

		};
	}

	private void setFullscreen(Window window) {
		GraphicsDevice device = window.getGraphicsConfiguration().getDevice();
		device.setFullScreenWindow(window);
	}

	private void setupRenderingEngine() {
		renderingEngine.setRenderable(game);
		renderingEngine.start();
	}

	private void setupPhysicsEngine() {
		physicsEngine.setMovable(game);
		physicsEngine.start();
	}

	public static void main(String[] args) {
		System.setProperty("java.library.path", "native/linux");

		new Liquids();
	}

}
