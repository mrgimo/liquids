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

	private Game game = Config.createGame();

	public Liquids() {
		frame = new JFrame();
		setupFrame();

		renderingEngine = new RenderingEngine(frame);
		setupRenderingEngine();

		physicsEngine = new PhysicsEngine();
		setupPhysicsEngine();

		frame.setVisible(true);

		renderingEngine.start();
		physicsEngine.start();
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
	}

	private void setupPhysicsEngine() {
		physicsEngine.setMovable(game);
	}

	public static void main(String[] args) {
		new Liquids();
	}

}
