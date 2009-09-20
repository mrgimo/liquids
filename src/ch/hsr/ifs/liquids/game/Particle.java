package ch.hsr.ifs.liquids.game;

import javax.media.opengl.GL;

import ch.hsr.ifs.liquids.common.Movable;
import ch.hsr.ifs.liquids.common.Renderable;
import ch.hsr.ifs.liquids.util.Random;
import ch.hsr.ifs.liquids.util.Vector;

public class Particle implements Renderable, Movable {

	protected enum Direction {
		FORWARDS, RIGHT, LEFT, BACKWARDS;
	}

	private static final float STEP = 1.8f;
	private static final float OPAQUE = 0.6f;

	public static final int MAX_HEALTH = 150;
	public static final int MIN_HEALTH = 1;

	public static final int MAX_HEALING = 50;
	public static final int MIN_HEALING = 1;

	public static final int MAX_DAMAGE = 50;
	public static final int MIN_DAMAGE = 1;

	protected PlayingField playingField;

	protected Player player;
	protected Vector position;

	protected int health;

	protected int healing;
	protected int damage;

	public void render(GL gl) {
		float red = player.color.red;
		float green = player.color.green;
		float blue = player.color.blue;

		float alpha = OPAQUE * health / MAX_HEALTH;

		gl.glColor4f(red, green, blue, alpha);

		gl.glBegin(GL.GL_POLYGON);
		gl.glVertex3f(position.x, position.y, 0);
		gl.glVertex3f(position.x + playingField.gridSize, position.y, 0);
		gl.glVertex3f(position.x + playingField.gridSize, position.y
				+ playingField.gridSize, 0);
		gl.glVertex3f(position.x, position.y + playingField.gridSize, 0);
		gl.glEnd();
	}

	public void move() {
		float x = player.device.getX() - position.x;
		float y = player.device.getY() - position.y;

		float distance = (float) Math.sqrt(x * x + y * y);

		x *= STEP * playingField.gridSize / distance;
		y *= STEP * playingField.gridSize / distance;

		for (Direction direction : Direction.values()) {
			Vector step = calcStep(direction, x, y);

			int index = playingField.positionToIndex(step);

			if (playingField.isBound(index)) {
				continue;
			}

			if (playingField.isAccessible(index)) {
				moveTo(step, index);
				return;
			} else {
				interact(index);
			}
		}

	}

	private Vector calcStep(Direction direction, float x, float y) {
		Vector step = new Vector(position.x, position.y);

		switch (direction) {
		case FORWARDS:
			step.x += x;
			step.y += y;

			break;
		case BACKWARDS:
			step.x -= x;
			step.y -= y;

			break;
		default:
			boolean swap = Random.random() > 0.5;

			step.x += swap ? y : -y;
			step.y += swap ? -x : x;
		}

		return step;
	}

	private void moveTo(Vector newPosition, int newIndex) {
		int index = playingField.positionToIndex(position);

		playingField.setAccessible(index);
		playingField.setParticle(this, newIndex);

		position = newPosition;
	}

	private void interact(int index) {
		Particle particle = playingField.getParticle(index);

		if (particle.player != player) {
			attack(particle);
		} else {
			heal(particle);
		}
	}

	private void attack(Particle particle) {
		particle.health -= damage;

		if (particle.health <= MIN_HEALTH) {
			particle.player = player;
			particle.health = MAX_HEALTH;

			particle.player.numberOfParticles--;
			player.numberOfParticles++;
		}
	}

	private void heal(Particle particle) {
		particle.health += healing;

		if (particle.health > MAX_HEALTH) {
			particle.health = MAX_HEALTH;
		}
	}

	protected void setHealth(int health) {
		if (health > MAX_HEALTH) {
			health = MAX_HEALTH;
		} else if (health < MIN_HEALTH) {
			health = MIN_HEALTH;
		}

		this.health = health;
	}

	public void setHealing(int healing) {
		if (healing > MAX_HEALING) {
			healing = MAX_HEALING;
		} else if (healing < MIN_HEALING) {
			healing = MIN_HEALING;
		}

		this.healing = healing;
	}

	protected void setDamage(int damage) {
		if (damage > MAX_DAMAGE) {
			damage = MAX_DAMAGE;
		} else if (damage < MAX_DAMAGE) {
			damage = MAX_DAMAGE;
		}

		this.damage = damage;
	}

}
