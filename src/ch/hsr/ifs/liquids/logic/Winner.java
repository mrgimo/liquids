package ch.hsr.ifs.liquids.logic;

import java.io.File;
import java.io.IOException;

import javax.media.opengl.GL;

import ch.hsr.ifs.liquids.common.Renderable;
import ch.hsr.ifs.liquids.util.Color;
import ch.hsr.ifs.liquids.util.Vector;
import ch.hsr.ifs.liquids.util.graphics.TextureUtil;
import ch.hsr.ifs.liquids.widgets.Window;

import com.sun.opengl.util.texture.Texture;

public class Winner implements Renderable {

	private static final String TEXTURE_PATH = "data/textures/winner.png";

	private Texture texture;
	private Color color;

	private Vector position;

	private float width;
	private float height;

	public Winner() {
		Vector size = Window.getWindow().getSize();		
		
		this.position = new Vector(size.getX() / 2, size.getY() / 2);

		this.width = size.getX() * 0.4f;
		this.height = size.getX() * 0.15f;
	}

	public void init() throws IOException {
		texture = TextureUtil.loadTexture(new File(TEXTURE_PATH));
	}

	public void render(GL gl) {
		float r = color.getR();
		float g = color.getG();
		float b = color.getB();
		float a = color.getA();

		gl.glColor4f(r, g, b, a);

		texture.bind();
		TextureUtil.renderTexture(position, width, height, gl);
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
