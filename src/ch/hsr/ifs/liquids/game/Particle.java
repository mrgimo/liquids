package ch.hsr.ifs.liquids.game;

import javax.media.opengl.GL;

import ch.hsr.ifs.liquids.common.Movable;
import ch.hsr.ifs.liquids.common.Renderable;
import ch.hsr.ifs.liquids.util.Vector;

public class Particle implements Renderable, Movable {

	protected Player player;
	protected Vector position;
	
	public Particle(Player player) {
		this.player = player;
	}
	
	public void render(GL gl) {
		gl.glColor3f(player.color.red, player.color.green, player.color.blue);

		gl.glBegin(GL.GL_POLYGON);
			gl.glVertex3f(position.x, position.y, position.z);
			gl.glVertex3f(position.x, position.y, position.z);
			gl.glVertex3f(position.x, position.y, position.z);
			gl.glVertex3f(position.x, position.y, position.z);
		gl.glEnd();
	}

	public void move() {
		
	}

}
