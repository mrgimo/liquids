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

	public static int healing;
	public static int damage;

	public static float step;

	public static Vector size;

	private final Vector position;
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

		index = field.calcIndex(position.getX(), position.getY());
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
		final float x = position.getX();
		final float y = position.getY();

		final float x_d = player.device.position.getX() - x;
		final float y_d = player.device.position.getY() - y;

		final float d = (float) Math.sqrt(x_d * x_d + y_d * y_d);
		if (d == 0)
			return;

		final float a = step / d;

		final float x_a = x_d * a;
		final float y_a = y_d * a;

		if (tryToMove(x + x_a, y + y_a))
			return;

		interact();

		float swap = RandomBoolean.random() ? -1 : 1;
		if (tryToMove(x + swap * y_a, y - swap * x_a))
			return;

		if (tryToMove(x - swap * y_a, y + swap * x_a))
			return;

		tryToMove(x - x_a, y - y_a);
	}

	private final boolean tryToMove(final float x, final float y) {
		final int index = field.calcIndex(x, y);

		target = field.get(index);
		if (target != ACCESSIBLE && target != this)
			return false;

		field.set(ACCESSIBLE, this.index);
		field.set(this, index);

		position.setX(x);
		position.setY(y);

		this.index = index;

		return true;
	}

	private final void interact() {
		if (target == INACCESSIBLE || target == ACCESSIBLE)
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
