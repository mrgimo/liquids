package ch.hsr.ifs.liquids;

import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import ch.hsr.ifs.liquids.controller.RenderingEngine;
import ch.hsr.ifs.liquids.widgets.Image;

public class Liquids {

	private Frame frame;
	private RenderingEngine renderer;

	public Liquids() {
		initializeFrame();
		initializeRenderer();

		frame.setVisible(true);
	}

	private void initializeFrame() {
		frame = new JFrame();

		frame.addWindowListener(createWindowListener());

		frame.setUndecorated(true);
		frame.setAlwaysOnTop(true);

		setFullscreen(frame);
	}

	private WindowAdapter createWindowListener() {
		return new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				frame.setVisible(false);
				renderer.stop();
				System.exit(0);
			}

		};
	}

	private void setFullscreen(Window window) {
		GraphicsDevice device = window.getGraphicsConfiguration().getDevice();
		device.setFullScreenWindow(window);
	}

	private void initializeRenderer() {
		renderer = new RenderingEngine(frame);
		//renderer.setRenderable(new Image("data/maps/arcadia"));
	}

	public static void main(String[] args) {
		new Liquids();
		/*JFrame frame = new JFrame();

		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GLCanvas canvas = new GLCanvas(new GLCapabilities());
		canvas.addGLEventListener(new GLEventListener() {

			public void init(GLAutoDrawable drawable) {
				GL gl = drawable.getGL();

				gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

				int width = drawable.getWidth();
				int height = drawable.getHeight();
				reshape(drawable, 0, 0, width, height);
			}

			public void reshape(GLAutoDrawable drawable, int x, int y,
					int width, int height) {
				GL gl = drawable.getGL();

				gl.glViewport(0, 0, width, height);
				gl.glMatrixMode(GL.GL_PROJECTION);
				gl.glLoadIdentity();
				gl.glOrtho(0, width, 0, height, -1, 1);
			}

			public void display(GLAutoDrawable drawable) {
				GL gl = drawable.getGL();

				//gl.glClear(GL.GL_COLOR_BUFFER_BIT);

				//gl.glColor3f(1f, 1f, 1f);
				gl.glBegin(GL.GL_POLYGON);
				gl.glVertex3f(100, 100, 0f);
				gl.glVertex3f(200, 100, 0f);
				gl.glVertex3f(200, 200, 0f);
				gl.glVertex3f(100, 200, 0f);
				gl.glEnd();

				gl.glFlush();
			}

			public void displayChanged(GLAutoDrawable drawable,
					boolean modeChanged, boolean deviceChanged) {
				init(drawable);
			}
		});
		
		frame.add(canvas);

		frame.setVisible(true);*/

	}

}
