package ch.hsr.ifs.liquids.controller;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

import ch.hsr.ifs.liquids.drawables.widgets.Screen;

public class Engine implements GLEventListener {

	private Screen screen;

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
		screen.draw(gl);
		gl.glFlush();
	}

	public void displayChanged(GLAutoDrawable drawable, boolean modeChanged,
			boolean deviceChanged) {
		// TODO: implement method displayChanged
	}

	public void setScreen(Screen screen) {
		screen.addGLEventListener(this);
		this.screen = screen;
	}

}