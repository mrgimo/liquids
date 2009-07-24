package ch.hsr.ifs.liquids.listeners;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

public class EventListener implements GLEventListener {

	private static final int SWAP_INTERVAL = 1;

	public void display(GLAutoDrawable drawable) {
		GL gl = drawable.getGL();

		// Clear the drawing area
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		// Reset the current matrix to the "identity"
		gl.glLoadIdentity();

		// Move the "drawing cursor" around
		gl.glTranslatef(-1.5f, 0.0f, -6.0f);

		// Drawing Using Triangles
		gl.glBegin(GL.GL_TRIANGLES);
		gl.glColor3f(1.0f, 0.0f, 0.0f); // Set the current drawing color to red
		gl.glVertex3f(0.0f, 1.0f, 0.0f); // Top
		gl.glColor3f(0.0f, 1.0f, 0.0f); // Set the current drawing color to
										// green
		gl.glVertex3f(-1.0f, -1.0f, 0.0f); // Bottom Left
		gl.glColor3f(0.0f, 0.0f, 1.0f); // Set the current drawing color to blue
		gl.glVertex3f(1.0f, -1.0f, 0.0f); // Bottom Right
		// Finished Drawing The Triangle
		gl.glEnd();

		// Move the "drawing cursor" to another position
		gl.glTranslatef(3.0f, 0.0f, 0.0f);
		// Draw A Quad
		gl.glBegin(GL.GL_QUADS);
		gl.glColor3f(0.5f, 0.5f, 1.0f); // Set the current drawing color to
		// light blue
		gl.glVertex3f(-1.0f, 1.0f, 0.0f); // Top Left
		gl.glVertex3f(1.0f, 1.0f, 0.0f); // Top Right
		gl.glVertex3f(1.0f, -1.0f, 0.0f); // Bottom Right
		gl.glVertex3f(-1.0f, -1.0f, 0.0f); // Bottom Left
		// Done Drawing The Quad
		gl.glEnd();

		// Flush all drawing operations to the graphics card
		gl.glFlush();
	}

	public void displayChanged(GLAutoDrawable drawable, boolean modeChanged,
			boolean deviceChanged) {
		System.out.println("lol");
		return;
	}

	public void init(GLAutoDrawable drawable) {
		GL gl = drawable.getGL();

		gl.setSwapInterval(SWAP_INTERVAL);

		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		gl.glShadeModel(GL.GL_SMOOTH);
	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) {
		GL gl = drawable.getGL();
		GLU glu = new GLU();

		if (height <= 0) {
			height = 1;
		}

		float h = (float) width / (float) height;

		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL.GL_PROJECTION);
		gl.glLoadIdentity();
		glu.gluPerspective(45.0f, h, 1.0, 20.0);
		gl.glMatrixMode(GL.GL_MODELVIEW);
		gl.glLoadIdentity();
	}

}
