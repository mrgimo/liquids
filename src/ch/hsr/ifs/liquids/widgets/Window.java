package ch.hsr.ifs.liquids.widgets;

import java.awt.Component;
import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.Toolkit;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import ch.hsr.ifs.liquids.util.Vector;

public class Window {

	public static final int SCREEN_WIDTH;
	public static final int SCREEN_HEIGHT;

	static {
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		SCREEN_WIDTH = toolkit.getScreenSize().width;
		SCREEN_HEIGHT = toolkit.getScreenSize().height;
	}

	private static Window instance;

	private Frame frame;

	private boolean isOpen = false;
	private boolean isFullscreen = false;

	public static Window getWindow() {
		if (instance == null) {
			instance = new Window();
		}

		return instance;
	}

	private Window() {
		frame = new JFrame();
	}

	public void open() {
		frame.setVisible(true);
	}

	public void close() {
		frame.setVisible(false);
	}

	public void add(Component component) {
		frame.add(component);
	}

	public void addWindowListener(WindowListener listener) {
		frame.addWindowListener(listener);
	}

	public void setPosition(Vector position) {
		frame.setLocation((int) position.getX(), (int) position.getY());
	}

	public void setSize(Vector size) {
		frame.setSize((int) size.getX(), (int) size.getY());
	}

	public void setFullscreen(boolean fullscreen) {		
		frame.setAlwaysOnTop(fullscreen);
		frame.setUndecorated(fullscreen);

		GraphicsDevice device = frame.getGraphicsConfiguration().getDevice();
		device.setFullScreenWindow(fullscreen ? frame : null);

		isFullscreen = fullscreen;
	}

	public Vector getPosition() {
		return new Vector(frame.getX(), frame.getY());
	}

	public Vector getSize() {
		return new Vector(frame.getWidth(), frame.getHeight());
	}

	public boolean isOpen() {
		return isOpen;
	}

	public boolean isFullscreen() {
		return isFullscreen;
	}

}
