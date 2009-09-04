package ch.hsr.ifs.liquids;

import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import ch.hsr.ifs.liquids.game.engines.RenderingEngine;
import ch.hsr.ifs.liquids.widgets.screens.StartUpScreen;

public class Liquids {

	private Frame frame;
	private RenderingEngine renderer;

	public Liquids() {
		frame = new JFrame();
		setupFrame();

		renderer = new RenderingEngine(frame);
		setupRenderer();

		frame.setVisible(true);
		renderer.start();
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
				renderer.stop();

				System.exit(0);
			}

		};
	}

	private void setFullscreen(Window window) {
		GraphicsDevice device = window.getGraphicsConfiguration().getDevice();
		device.setFullScreenWindow(window);
	}

	private void setupRenderer() {
		StartUpScreen screen = new StartUpScreen();
		renderer.setRenderable(screen);
	}

	public static void main(String[] args) {
		new Liquids();
	}

}
