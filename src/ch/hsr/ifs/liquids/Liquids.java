package ch.hsr.ifs.liquids;

import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.swing.JFrame;

import ch.hsr.ifs.liquids.common.Renderable;
import ch.hsr.ifs.liquids.controller.RenderingEngine;
import ch.hsr.ifs.liquids.widgets.screens.StartUpScreen;

import com.sun.opengl.util.Animator;

public class Liquids {

	private Frame frame;
	private RenderingEngine renderer;

	public Liquids() {
		initializeFrame();
		initializeRenderer();

		frame.setVisible(true);
		renderer.start();
	}

	private void initializeFrame() {
		frame = new JFrame();

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

	private void initializeRenderer() {
		GLCapabilities capabilities = new GLCapabilities();
		GLCanvas canvas = new GLCanvas(capabilities);
		Animator animator = new Animator(canvas);
		Renderable screen = new StartUpScreen();

		renderer = new RenderingEngine(screen, animator);

		canvas.addGLEventListener(renderer);
	}

	public static void main(String[] args) {
		new Liquids();
	}

}
