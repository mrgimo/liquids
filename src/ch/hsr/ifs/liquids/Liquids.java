package ch.hsr.ifs.liquids;

import java.awt.Cursor;
import java.awt.Frame;

import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;

import ch.hsr.ifs.liquids.listeners.EventListener;
import ch.hsr.ifs.liquids.listeners.WindowListener;

import com.sun.opengl.util.Animator;

public class Liquids {

	private static final String FRAME_TITLE = "Liquids";

	private Frame frame;
	private Animator animator;
	private GLCanvas canvas;

	public Liquids() {
		canvas = initialzeCanvas();
		animator = initializeAnimator();
		frame = initializeFrame();

		frame.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		
		animator.start();
	}

	private GLCanvas initialzeCanvas() {
		GLCanvas canvas = new GLCanvas(new GLCapabilities());

		canvas.addGLEventListener(new EventListener());

		return canvas;
	}

	private Animator initializeAnimator() {
		return new Animator(canvas);
	}

	private Frame initializeFrame() {
		Frame frame = new Frame(FRAME_TITLE);

		frame.add(canvas);
		frame.addWindowListener(new WindowListener(animator));

		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		return frame;
	}

	public static void main(String[] args) {
		new Liquids();
	}

}
