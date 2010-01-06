package ch.hsr.ifs.liquids.widgets;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;

import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.swing.JFrame;

import ch.hsr.ifs.liquids.util.Vector;

import com.sun.opengl.util.Animator;

public class Window {

	public static final int SCREEN_WIDTH;
	public static final int SCREEN_HEIGHT;

	private static final int COLOR_BITS = 8;

	static {
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		SCREEN_WIDTH = toolkit.getScreenSize().width;
		SCREEN_HEIGHT = toolkit.getScreenSize().height;
	}

	private final static Window instance = new Window();

	private final Frame frame;

	private final GLCanvas canvas;
	private final Animator animator;

	public static Window getWindow() {
		return instance;
	}

	private Window() {
		frame = new JFrame();

		canvas = new GLCanvas(createCapabilities());
		animator = new Animator(canvas);

		frame.add(canvas);
		frame.setCursor(createHiddenCursor());
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

	private GLCapabilities createCapabilities() {
		GLCapabilities capabilities = new GLCapabilities();

		capabilities.setDoubleBuffered(true);
		capabilities.setHardwareAccelerated(true);

		capabilities.setRedBits(COLOR_BITS);
		capabilities.setGreenBits(COLOR_BITS);
		capabilities.setBlueBits(COLOR_BITS);
		capabilities.setAlphaBits(COLOR_BITS);

		return capabilities;
	}

	public void open() {
		frame.setVisible(true);
		animator.start();
	}

	public void close() {
		frame.setVisible(false);
		animator.stop();
	}

	public void addWindowListener(WindowListener listener) {
		frame.addWindowListener(listener);
	}

	public void addGLEventListener(GLEventListener listener) {
		canvas.addGLEventListener(listener);
	}

	public void setPosition(Vector position) {
		frame.setLocation((int) position.getX(), (int) position.getY());
	}

	public void setSize(Vector size) {
		canvas.setSize((int) size.getX(), (int) size.getY());
		frame.pack();
	}

	public Vector getPosition() {
		return new Vector(frame.getX(), frame.getY());
	}

	public Vector getSize() {
		return new Vector(canvas.getWidth(), canvas.getHeight());
	}

	public Vector getMousePosition() {
		Point mousePosition = frame.getMousePosition();
		if (mousePosition == null)
			return new Vector();

		return new Vector(mousePosition.x, frame.getHeight() - mousePosition.y);
	}

}
