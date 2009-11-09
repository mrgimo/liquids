package ch.hsr.ifs.liquids.logic;

import static ch.hsr.ifs.liquids.logic.PlayingField.ACCESSIBLE;
import static ch.hsr.ifs.liquids.logic.PlayingField.INACCESSIBLE;

import java.io.File;
import java.io.IOException;

import javax.media.opengl.GL;

import ch.hsr.ifs.liquids.common.Moveable;
import ch.hsr.ifs.liquids.common.Renderable;
import ch.hsr.ifs.liquids.util.RandomBoolean;
import ch.hsr.ifs.liquids.util.Vector;
import ch.hsr.ifs.liquids.util.graphics.TextureUtil;

import com.sun.opengl.util.texture.Texture;

public final class Particle implements Renderable, Moveable {

	private static final String TEXTURE_PATH = "data/textures/particle.png";

	private static final float OPAQUE = 0.75f;
	
	private static final int MAX_HEALTH = 500;
	private static final int MIN_HEALTH = 0;

	protected static Texture texture;

	public static Vector size;

	public static int healing;
	public static int damage;

	private final Vector position;

	private final Vector alpha = new Vector();
	private final Vector move = new Vector();

	private final PlayingField field;

	private Player player;
	private Particle target;

	private int index;
	private int health;

	public Particle(PlayingField field, Player player, Vector position) {
		this.field = field;

		this.player = player;
		this.position = position;

		if (field == null || player == null || position == null)
			return;

		index = field.calcIndex(position);
		health = MAX_HEALTH;
	}

	public static void staticInit() throws IOException {
		texture = TextureUtil.loadTexture(new File(TEXTURE_PATH));
	}

	public void init() throws IOException {
		staticInit();
	}

	public final void render(final GL gl) {
		setColor(gl);
		TextureUtil.renderTexture(position, size, gl);
	}

	private final void setColor(final GL gl) {
		final float r = player.color.getR();
		final float g = player.color.getG();
		final float b = player.color.getB();

		final float a = OPAQUE * health / MAX_HEALTH;

		gl.glColor4f(r, g, b, a);
	}

	public final void move() {
		calcAlpha();

		if (tryToMoveForward())
			return;

		interact();

		if (RandomBoolean.random()) {
			if (tryToMoveRight() || tryToMoveLeft())
				return;
		} else {
			if (tryToMoveLeft() || tryToMoveRight())
				return;
		}

		tryToMoveBackwards();
	}

	private final void calcAlpha() {
		alpha.setX(player.device.position.getX() - position.getX());
		alpha.setY(player.device.position.getY() - position.getY());

		float x = alpha.getX() * alpha.getX();
		float y = alpha.getY() * alpha.getY();

		final float distance = (float) Math.sqrt(x + y);
		if (distance == 0) {
			x = 0;
			y = 0;
		} else {
			x = alpha.getX() * size.getX() / distance;
			y = alpha.getY() * size.getY() / distance;
		}

		alpha.setX(x);
		alpha.setY(y);
	}

	private final boolean tryToMoveForward() {
		move.setX(position.getX() + alpha.getX());
		move.setY(position.getY() + alpha.getY());

		return tryToMove();
	}

	private final boolean tryToMoveRight() {
		move.setX(position.getX() + alpha.getY());
		move.setY(position.getY() - alpha.getX());

		return tryToMove();
	}

	private final boolean tryToMoveLeft() {
		move.setX(position.getX() - alpha.getY());
		move.setY(position.getY() + alpha.getX());

		return tryToMove();
	}

	private final boolean tryToMoveBackwards() {
		move.setX(position.getX() - alpha.getX());
		move.setY(position.getY() - alpha.getY());

		return tryToMove();
	}

	private final boolean tryToMove() {
		final int index = field.calcIndex(move);

		target = field.get(index);
		if (target != ACCESSIBLE && target != this)
			return false;

		field.set(ACCESSIBLE, this.index);
		field.set(this, index);

		position.setX(move.getX());
		position.setY(move.getY());

		this.index = index;

		return true;
	}

	private final void interact() {
		if (target == INACCESSIBLE)
			return;

		if (target.player != player)
			attack();
		else
			heal();
	}

	private final void attack() {
		target.health -= damage;
		if (target.health >= MIN_HEALTH)
			return;

		++player.numberOfParticles;
		--target.player.numberOfParticles;

		target.player = player;
		target.health = MAX_HEALTH;
	}

	private final void heal() {
		target.health += healing;
		if (target.health > MAX_HEALTH)
			target.health = MAX_HEALTH;
	}

}
