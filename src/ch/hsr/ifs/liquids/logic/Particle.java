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

public class Particle implements Renderable, Moveable {

	private static final String TEXTURE_PATH = "data/textures/particle.png";

	private static final int MAX_HEALTH = 500;
	private static final int MIN_HEALTH = 0;

	private static Texture texture;
	private static int size;

	private static int healing;
	private static int damage;

	public static void loadTexture() throws IOException {
		texture = TextureUtil.loadTexture(new File(TEXTURE_PATH));
	}

	public static Texture getTexture() {
		return texture;
	}

	public static void setSize(int size) {
		Particle.size = size;
	}

	public static void setHealing(int healing) {
		Particle.healing = healing;
	}

	public static void setDamage(int damage) {
		Particle.damage = damage;
	}

	private PlayingField field;

	private Player player;
	private Vector position;

	private int index;

	private int health = MAX_HEALTH;

	private float newX;
	private float newY;

	private int newIndex;

	private Particle interactor;

	protected Particle() {
		return;
	}

	public Particle(PlayingField field, Player player, float x, float y) {
		this.field = field;

		this.player = player;
		this.position = new Vector(x, y);

		index = field.calcIndex(position.getX(), position.getY());
	}

	public void init() throws IOException {
		loadTexture();
	}

	public final void render(GL gl) {
		final float r = player.color.getR();
		final float g = player.color.getG();
		final float b = player.color.getB();

		final float a = health / MAX_HEALTH;

		gl.glColor4f(r, g, b, a);

		TextureUtil.renderTexture(position, size, size, gl);
	}

	public final void move() {
		newX = player.device.position.getX() - position.getX();
		newY = player.device.position.getY() - position.getY();

		final float distance = (float) Math.sqrt(newX * newX + newY * newY);
		if (distance == 0) {
			return;
		}

		newX *= size / distance;
		newY *= size / distance;

		if (tryToMove(position.getX() + newX, position.getY() + newY))
			return;

		interact();

		if (RandomBoolean.random()) {
			if (tryToMove(position.getX() + newY, position.getY() - newX))
				return;

			if (tryToMove(position.getX() - newY, position.getY() + newX))
				return;
		} else {
			if (tryToMove(position.getX() - newY, position.getY() + newX))
				return;

			if (tryToMove(position.getX() + newY, position.getY() - newX))
				return;
		}

		tryToMove(position.getX() - newX, position.getY() - newY);
	}

	private final boolean tryToMove(final float x, final float y) {
		newIndex = field.calcIndex(x, y);
		interactor = field.get(newIndex);

		if (interactor != ACCESSIBLE && interactor != this)
			return false;

		field.set(ACCESSIBLE, index);
		field.set(this, newIndex);

		position.setX(x);
		position.setY(y);

		index = newIndex;

		return true;
	}

	private final void interact() {
		if (interactor == INACCESSIBLE)
			return;

		if (interactor.player != player)
			attack();
		else
			heal();
	}

	private final void attack() {
		interactor.health -= damage;
		if (interactor.health >= MIN_HEALTH)
			return;

		++player.numberOfParticles;
		--interactor.player.numberOfParticles;

		interactor.player = player;
		interactor.health = MAX_HEALTH;
	}

	private final void heal() {
		interactor.health += healing;
		if (interactor.health > MAX_HEALTH)
			interactor.health = MAX_HEALTH;
	}

	public Player getPlayer() {
		return player;
	}

}
