package ch.hsr.ifs.liquids.engines;

import java.awt.Frame;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;

import ch.hsr.ifs.liquids.common.Renderable;

import com.sun.opengl.util.Animator;
import com.sun.opengl.util.FPSAnimator;

public class RenderingEngine implements GLEventListener {

	private static final int FRAMES_PER_SECOND = 60;

	private Renderable renderable;
	private Animator animator;

	public RenderingEngine(Frame frame) {
		GLCanvas canvas = new GLCanvas(createCapabilities());
		canvas.addGLEventListener(this);
		frame.add(canvas);

		animator = new FPSAnimator(canvas, FRAMES_PER_SECOND);
	}

	private GLCapabilities createCapabilities() {
		GLCapabilities capabilities = new GLCapabilities();

		capabilities.setDoubleBuffered(true);
		capabilities.setHardwareAccelerated(true);

		return capabilities;
	}

	public void init(GLAutoDrawable drawable) {
		GL gl = drawable.getGL();

		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

		int width = drawable.getWidth();
		int height = drawable.getHeight();
		reshape(drawable, 0, 0, width, height);
	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) {
		GL gl = drawable.getGL();

		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glOrtho(0, width, 0, height, -1, 1);
	}

	public void display(GLAutoDrawable drawable) {
		GL gl = drawable.getGL();

		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
		renderable.render(gl);
		gl.glFlush();
	}

	public void displayChanged(GLAutoDrawable drawable, boolean modeChanged,
			boolean deviceChanged) {
		init(drawable);
	}

	public void start() {
		animator.start();
	}

	public void stop() {
		if (animator.isAnimating()) {
			animator.stop();
		}
	}

	public void setRenderable(Renderable renderable) {
		if (animator.isAnimating()) {
			animator.stop();
		}

		this.renderable = renderable;
	}

}