package ch.hsr.ifs.liquids.controller;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

import ch.hsr.ifs.liquids.common.Renderable;

import com.sun.opengl.util.Animator;

public class RenderingEngine implements GLEventListener {

	protected Renderable renderable;
	protected Animator animator;

	public RenderingEngine(Renderable renderable, Animator animator) {
		this.renderable = renderable;
		this.animator = animator;
	}

	public void init(GLAutoDrawable autoDrawable) {
		GL gl = autoDrawable.getGL();

		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

		int width = autoDrawable.getWidth();
		int height = autoDrawable.getHeight();
		reshape(autoDrawable, 0, 0, width, height);
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

	public void displayChanged(GLAutoDrawable drawable,
			boolean modeChanged, boolean deviceChanged) {
		init(drawable);
	}

	public void start() {
		animator.start();
	}

	public void stop() {
		animator.stop();
	}

	public void setDrawable(Renderable renderable) {
		if (animator.isAnimating()) {
			animator.stop();
		}

		this.renderable = renderable;
	}

}