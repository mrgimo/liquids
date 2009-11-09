package ch.hsr.ifs.liquids.widgets;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;

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

	private final Frame frame;

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

	public void hideCursor(boolean hide) {
		frame.setCursor(hide ? createHiddenCursor() : null);
	}

	private Cursor createHiddenCursor() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension size = toolkit.getBestCursorSize(1, 1);

		BufferedImage image = new BufferedImage(size.width, size.height,
				BufferedImage.TYPE_INT_ARGB);

		Graphics2D graphics = image.createGraphics();

		graphics.setBackground(new Color(0, 0, 0, 0));
		graphics.clearRect(0, 0, size.width, size.height);

		graphics.dispose();

		return toolkit.createCustomCursor(image, new Point(0, 0),
				"hiddenCursor");
	}

	public Vector getPosition() {
		return new Vector(frame.getX(), frame.getY());
	}

	public Vector getSize() {
		return new Vector(frame.getWidth(), frame.getHeight());
	}

	public Vector getMousePosition() {
		Point mousePosition = frame.getMousePosition();

		return new Vector(mousePosition.x, frame.getHeight() - mousePosition.y);
	}

	public boolean isOpen() {
		return isOpen;
	}

	public boolean isFullscreen() {
		return isFullscreen;
	}

}
