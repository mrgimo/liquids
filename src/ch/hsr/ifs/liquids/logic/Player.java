package ch.hsr.ifs.liquids.logic;

import java.io.File;
import java.io.IOException;

import javax.media.opengl.GL2;

import ch.hsr.ifs.liquids.common.Renderable;
import ch.hsr.ifs.liquids.devices.Device;
import ch.hsr.ifs.liquids.util.Color;
import ch.hsr.ifs.liquids.util.Vector;
import ch.hsr.ifs.liquids.util.graphics.TextureUtil;

import com.jogamp.opengl.util.texture.Texture;

public final class Player implements Renderable {

	private static final float OPAQUE = 0.5f;

	private static final Vector SIZE = new Vector(20, 20);

	private static final String TEXTURE_PATH = "data/textures/player.png";

	protected static Texture texture;

	public final Device device;
	public final Color color;

	protected int numberOfParticles;

	public Player(Color color, Device device, int numberOfParticles) {
		this.color = color;
		this.device = device;

		this.numberOfParticles = numberOfParticles;
	}

	public static void staticInit(GL2 gl) throws IOException {
		texture = TextureUtil.initTexture(gl, new File(TEXTURE_PATH));
	}

	public void init(GL2 gl) throws IOException {
		staticInit(gl);
	}

	public final void render(final GL2 gl) {
		gl.glColor4f(color.getR(), color.getG(), color.getB(), OPAQUE);
		TextureUtil.renderTexture(device.getX(), device.getY(), SIZE, gl);
	}

}
