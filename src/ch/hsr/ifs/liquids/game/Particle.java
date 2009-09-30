package ch.hsr.ifs.liquids.game;

import javax.media.opengl.GL;

import ch.hsr.ifs.liquids.common.Movable;
import ch.hsr.ifs.liquids.util.Random;
import ch.hsr.ifs.liquids.util.Vector;
import ch.hsr.ifs.liquids.widgets.Element;

public class Particle extends Element implements Movable {

	protected enum Direction {
		FORWARDS, RIGHT, LEFT, BACKWARDS;
	}

	private static final float STEP = 1f;
	private static final float OPAQUE = 0.6f;

	private static final int MAX_HEALTH = 500;
	private static final int MIN_HEALTH = 0;

	protected PlayingField playingField;

	protected Player player;

	protected int health;

	protected int healing;
	protected int damage;

	public final void render(GL gl) {
		setColor(gl);

		float x1 = position.x;
		float y1 = position.y;

		float x2 = x1 + width;
		float y2 = y1 + height;

		gl.glBegin(GL.GL_POLYGON);
			gl.glVertex3f(x1, y1, 0);
			gl.glVertex3f(x2, y1, 0);
			gl.glVertex3f(x2, y2, 0);
			gl.glVertex3f(x1, y2, 0);
		gl.glEnd();
	}

	private final void setColor(GL gl) {
		float r = player.color.red;
		float g = player.color.green;
		float b = player.color.blue;

		float a = OPAQUE * health / MAX_HEALTH;

		gl.glColor4f(r, g, b, a);
	}

	public final void move() {
		float x = player.cursor.position.x - position.x;
		float y = player.cursor.position.y - position.y;

		float distance = (float) Math.sqrt(x * x + y * y);
		if (distance == 0) {
			return;
		}

		x *= STEP * width / distance;
		y *= STEP * height / distance;

		tryToMove(x, y);
	}

	private void tryToMove(float x, float y) {
		for (Direction direction : Direction.values()) {
			Vector move = calcMove(direction, x, y);

			int index = playingField.positionToIndex(move);

			if (playingField.isInaccessible(index)) {
				continue;
			}

			if (playingField.isAccessible(index)) {
				moveTo(move, index);
				return;
			}

			if(direction == Direction.FORWARDS){
				interact(index);
			}
		}
	}

	private final Vector calcMove(Direction direction, float x, float y) {
		Vector move = new Vector(position.x, position.y);

		switch (direction) {
		case FORWARDS:
			move.x += x;
			move.y += y;

			break;
		case BACKWARDS:
			move.x -= x;
			move.y -= y;

			break;
		default:
			boolean swap = Random.random() > 0.5;

			move.x += swap ? y : -y;
			move.y += swap ? -x : x;
		}

		return move;
	}

	private final void moveTo(Vector newPosition, int newIndex) {
		int index = playingField.positionToIndex(position);

		playingField.setAccessible(index);
		playingField.setParticle(this, newIndex);

		position = newPosition;
	}

	private final void interact(int index) {
		Particle particle = playingField.getParticle(index);

		if (particle.player != player) {
			attack(particle);
		} else {
			heal(particle);
		}
	}

	private final void attack(Particle particle) {
		if ((particle.health -= damage) <= MIN_HEALTH) {
			--particle.player.numberOfParticles;

			particle.player = player;
			particle.health = MAX_HEALTH;

			++player.numberOfParticles;
		}
	}

	private final void heal(Particle particle) {
		if ((particle.health += healing) > MAX_HEALTH) {
			particle.health = MAX_HEALTH;
		}
	}

}
