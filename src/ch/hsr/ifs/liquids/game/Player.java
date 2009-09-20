package ch.hsr.ifs.liquids.game;

import javax.media.opengl.GL;

import ch.hsr.ifs.liquids.common.Renderable;
import ch.hsr.ifs.liquids.devices.Device;
import ch.hsr.ifs.liquids.util.Color;

public class Player implements Renderable {

	protected Color color;
	protected Device device;

	protected int numberOfParticles;

	public void render(GL gl) {
		gl.glColor3f(color.red, color.green, color.blue);

		gl.glBegin(GL.GL_POLYGON);
		gl.glVertex3f(device.getX() - 5, device.getY() - 5, 0);
		gl.glVertex3f(device.getX() + 5, device.getY() - 5, 0);
		gl.glVertex3f(device.getX() + 5, device.getY() + 5, 0);
		gl.glVertex3f(device.getX() - 5, device.getY() + 5, 0);
		gl.glEnd();
	}

}
