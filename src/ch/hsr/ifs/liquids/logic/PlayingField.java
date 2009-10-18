package ch.hsr.ifs.liquids.logic;

import java.io.File;
import java.io.IOException;

import javax.media.opengl.GL;

import ch.hsr.ifs.liquids.common.Renderable;
import ch.hsr.ifs.liquids.util.Vector;
import ch.hsr.ifs.liquids.util.graphics.BitMap;
import ch.hsr.ifs.liquids.util.graphics.TextureUtil;
import ch.hsr.ifs.liquids.util.graphics.BitMap.Bit;
import ch.hsr.ifs.liquids.widgets.Window;

import com.sun.opengl.util.texture.Texture;

public class PlayingField implements Renderable {

	public static final Particle ACCESSIBLE = null;
	public static final Particle INACCESSIBLE = new Particle();

	private Texture texture;
	private File textureFile;

	private Particle[] bounds;

	private Vector position;

	private final int gridSize;

	public final int widthInPixels;
	public final int heightInPixels;

	private final int widthInFields;
	private final int heightInFields;

	public PlayingField(File boundsFile, File textureFile, int gridSize) {
		this.textureFile = textureFile;
		this.gridSize = gridSize;

		Vector windowSize = Window.getWindow().getSize();

		int width = (int) windowSize.getX();
		int height = (int) windowSize.getY();

		position = new Vector(width / 2, height / 2);

		widthInPixels = width - width % gridSize;
		heightInPixels = height - height % gridSize;

		widthInFields = widthInPixels / gridSize;
		heightInFields = heightInPixels / gridSize;

		initBounds(boundsFile);
	}

	private void initBounds(File file) {
		bounds = new Particle[widthInFields * heightInFields];

		BitMap bitMap = new BitMap(file, widthInPixels, heightInPixels);
		for (int x = 0; x < bitMap.bits.length; x++) {
			for (int y = 0; y < bitMap.bits[x].length; y++) {
				if(bitMap.bits[x][y] == Bit.BLACK) {
					int index = calcIndex(x, y);
					bounds[index] = INACCESSIBLE;
				}
			}
		}
	}

	public final void init() throws IOException {
		texture = TextureUtil.loadTexture(textureFile);
	}

	public void render(GL gl) {
		texture.bind();
		gl.glColor4f(1, 1, 1, 1);
		TextureUtil.renderTexture(position, widthInPixels, heightInPixels, gl);
	}

	public final int calcIndex(final float x, final float y) {
		if (x >= widthInPixels || x <= 0 || y >= widthInPixels || y <= 0)
			return -1;

		return (int) x / gridSize + (int) y / gridSize * widthInFields;
	}

	public final Particle get(int index) {
		if (isInvalid(index))
			return INACCESSIBLE;

		return bounds[index];
	}

	public final void set(Particle particle, int index) {
		if (isInvalid(index))
			return;

		bounds[index] = particle;
	}

	private final boolean isInvalid(int index) {
		return index < 0 || index >= bounds.length;
	}

}
