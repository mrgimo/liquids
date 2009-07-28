package ch.hsr.ifs.liquids;

import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;

import ch.hsr.ifs.liquids.interfaces.Drawable;
import ch.hsr.ifs.liquids.util.list.List;

import com.sun.opengl.util.Animator;

public class Liquids implements GLEventListener {

	private GLCanvas canvas;
	private Animator animator;
	private Frame frame;

	private List<Drawable> drawables = new List<Drawable>();

	public Liquids() {
		initCanvas();
		initAnimator();
		initFrame();

		animator.start();
	}

	private void initCanvas() {
		canvas = new GLCanvas(new GLCapabilities());
		canvas.addGLEventListener(this);
	}

	private void initAnimator() {
		animator = new Animator(canvas);
	}

	private void initFrame() {
		frame = new Frame();

		frame.add(canvas);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				animator.stop();
				System.exit(0);
			}
		});

		frame.setUndecorated(true);
		frame.setAlwaysOnTop(true);
		GraphicsDevice device = frame.getGraphicsConfiguration().getDevice();
		device.setFullScreenWindow(frame);

		frame.setVisible(true);
	}

	public void init(GLAutoDrawable drawable) {
		GL gl = drawable.getGL();

		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

		gl.glMatrixMode(GL.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glOrtho(0.0d, 1.0d, 0.0d, 1.0d, -1.0d, 1.0d);
	}

	public void display(GLAutoDrawable autoDrawable) {
		GL gl = autoDrawable.getGL();

		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
		for (Drawable drawable : drawables) {
			drawable.draw(gl);
		}
		gl.glFlush();
	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) {
		System.out.println("reshape this, biatch!");
		// TODO: implement method reshape
	}

	public void displayChanged(GLAutoDrawable drawable, boolean modeChanged,
			boolean deviceChanged) {
		System.out.println("who touched my display?!");
		// TODO: implement method displayChanged
	}

	public static void main(String[] args) {
		new Liquids();
	}

}
