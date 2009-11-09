package ch.hsr.ifs.liquids.engines;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;

import ch.hsr.ifs.liquids.common.Renderable;
import ch.hsr.ifs.liquids.util.Vector;
import ch.hsr.ifs.liquids.widgets.Window;

import com.sun.opengl.util.Animator;

public final class Renderer implements GLEventListener {

	private static final int COLOR_BITS = 8;

	private final Renderable renderable;

	private final GLCanvas canvas;
	private final Animator animator;

	public Renderer(Renderable renderable, Window window) {
		this.renderable = renderable;

		canvas = new GLCanvas(createCapabilities());
		animator = new Animator(canvas);

		canvas.addGLEventListener(this);

		window.add(canvas);
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

	public void start() {
		animator.start();
	}

	public void stop() {
		animator.stop();
	}

	public void init(GLAutoDrawable drawable) {
		try {
			renderable.init();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		Vector size = Window.getWindow().getSize();
		reshape(drawable, 0, 0, (int) size.getX(), (int) size.getY());
	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) {
		GL gl = drawable.getGL();

		gl.glMatrixMode(GL.GL_PROJECTION);
		gl.glShadeModel(GL.GL_SMOOTH);

		gl.glDisable(GL.GL_DEPTH_TEST);

		gl.glEnable(GL.GL_TEXTURE_2D);
		gl.glEnable(GL.GL_BLEND);

		gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);

		gl.glHint(GL.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);
		gl.glHint(GL.GL_POINT_SMOOTH_HINT, GL.GL_NICEST);

		gl.glClearColor(0, 0, 0, 0);
		gl.glClearDepth(1.0);

		gl.glViewport(x, y, width, height);
		gl.glLoadIdentity();
		gl.glOrtho(x, width, y, height, -1, 1);
	}

	public final void display(final GLAutoDrawable drawable) {
		GL gl = drawable.getGL();

		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		renderable.render(gl);
		gl.glFlush();
	}

	public final void displayChanged(GLAutoDrawable drawable,
			boolean modeChanged, boolean deviceChanged) {
		init(drawable);
	}

}