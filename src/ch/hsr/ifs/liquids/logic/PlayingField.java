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

public final class PlayingField implements Renderable {

	public static final Particle ACCESSIBLE = null;
	public static final Particle INACCESSIBLE = new Particle(null, null, null);

	public final Vector sizeInPixels = new Vector();
	public final Vector sizeInFields = new Vector();

	private final int gridSize;
	private final Particle[] bounds;

	private final Vector position = new Vector();

	private Texture texture;
	private File textureFile;

	public PlayingField(File boundsFile, File textureFile, int gridSize) {
		this.textureFile = textureFile;
		this.gridSize = gridSize;

		float width = Window.getWindow().getSize().getX();
		float height = Window.getWindow().getSize().getY();

		position.setX(width / 2);
		position.setY(height / 2);

		sizeInPixels.setX(width - width % gridSize);
		sizeInPixels.setY(height - height % gridSize);

		sizeInFields.setX(sizeInPixels.getX() / gridSize);
		sizeInFields.setY(sizeInPixels.getY() / gridSize);

		bounds = createBounds(boundsFile);
	}

	private Particle[] createBounds(File file) {
		int widthInFields = (int) sizeInFields.getX();
		int heightInFields = (int) sizeInFields.getY();

		int widthInPixels = (int) sizeInPixels.getX();
		int heightInPixels = (int) sizeInPixels.getY();

		Particle[] bounds = new Particle[widthInFields * heightInFields];
		BitMap bitMap = new BitMap(file, widthInPixels, heightInPixels);

		for (int x = 0; x < bitMap.bits.length; x++) {
			for (int y = 0; y < bitMap.bits[x].length; y++) {
				if (bitMap.bits[x][y] == Bit.BLACK) {
					Vector position = new Vector(x, y);

					bounds[calcIndex(position)] = INACCESSIBLE;
				}
			}
		}

		return bounds;
	}

	public void init() throws IOException {
		texture = TextureUtil.loadTexture(textureFile);
	}

	public final void render(final GL gl) {
		gl.glColor4f(1, 1, 1, 1);

		texture.bind();
		TextureUtil.renderTexture(position, sizeInPixels, gl);
	}

	public final int calcIndex(final Vector position) {
		final float x = position.getX();
		final float y = position.getY();

		final float width = sizeInPixels.getX();
		final float height = sizeInPixels.getY();

		if (x >= width || x < 0 || y >= height || y < 0)
			return -1;

		final int xi = (int) x / gridSize;
		final int yi = (int) y / gridSize * (int) sizeInFields.getX();

		return xi + yi;
	}

	public final Particle get(final int index) {
		if (isInvalid(index))
			return INACCESSIBLE;

		return bounds[index];
	}

	public final void set(final Particle particle, final int index) {
		if (isInvalid(index))
			return;

		bounds[index] = particle;
	}

	private final boolean isInvalid(final int index) {
		return index < 0 || index >= bounds.length;
	}

}
