package ch.hsr.ifs.liquids.logic;

import java.io.File;
import java.io.IOException;

import javax.media.opengl.GL;

import ch.hsr.ifs.liquids.common.Renderable;
import ch.hsr.ifs.liquids.devices.Device;
import ch.hsr.ifs.liquids.util.Color;
import ch.hsr.ifs.liquids.util.Vector;
import ch.hsr.ifs.liquids.util.graphics.TextureUtil;

import com.sun.opengl.util.texture.Texture;

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

	public static void staticInit() throws IOException {
		texture = TextureUtil.loadTexture(new File(TEXTURE_PATH));
	}

	public void init() throws IOException {
		staticInit();
	}

	public final void render(final GL gl) {
		gl.glColor4f(color.getR(), color.getG(), color.getB(), OPAQUE);
		TextureUtil.renderTexture(device.position, SIZE, gl);
	}

}
