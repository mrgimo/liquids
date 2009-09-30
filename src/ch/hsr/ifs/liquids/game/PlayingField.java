package ch.hsr.ifs.liquids.game;

import javax.media.opengl.GL;

import ch.hsr.ifs.liquids.common.Renderable;
import ch.hsr.ifs.liquids.util.Vector;
import ch.hsr.ifs.liquids.widgets.Image;

public class PlayingField implements Renderable {

	protected static final Particle ACCESSIBLE = null;
	protected static final Particle INACCESSIBLE = new Particle();

	protected Particle[] bounds;
	protected Renderable texture;

	protected int gridSize;

	protected int widthInPixels;
	protected int heightInPixels;

	protected int widthInFields;
	protected int heightInFields;

	public PlayingField(String boundsPath, String texturePath, int gridSize) {
		this.gridSize = gridSize;

		widthInPixels = calcWidthInPixels(Config.SCREEN_WIDTH);
		heightInPixels = calcHeightInPixels(Config.SCREEN_HEIGHT);

		widthInFields = widthInPixels / gridSize;
		heightInFields = heightInPixels / gridSize;

		bounds = initBounds(boundsPath);
		texture = new Image(texturePath, widthInPixels, heightInPixels);
	}

	protected int calcWidthInPixels(int screenWidth) {
		return subtractOffcut(screenWidth, gridSize);
	}

	protected int calcHeightInPixels(int screenHeight) {
		return subtractOffcut(screenHeight, gridSize);
	}

	protected int subtractOffcut(int value, int factor) {
		return value - value % factor;
	}

	protected Particle[] initBounds(String boundsPath) {
		Image image = new Image(boundsPath, widthInPixels, heightInPixels);
		byte[] pixels = (byte[]) image.getPixelBuffer().array();

		Particle[] bounds = new Particle[widthInFields * heightInFields];
		for (int i = 0; i < bounds.length; i++) {
			bounds[i] = extractBoundsValue(i, pixels);
		}

		return bounds;
	}

	protected Particle extractBoundsValue(int index, byte[] pixels) {
		int firstPixel = calcPixelIndex(index);

		for (int x = 0; x < gridSize; x++) {
			for (int y = 0; y < gridSize; y++) {
				int pixel = firstPixel + x + y * widthInPixels;

				byte[] sample = getSample(pixel, pixels);
				if (isBlack(sample)) {
					return INACCESSIBLE;
				}
			}
		}

		return ACCESSIBLE;
	}

	protected int calcPixelIndex(int index) {
		int x = index % widthInFields * gridSize;
		int y = index / widthInFields * gridSize;

		return x + y * widthInPixels;
	}

	protected byte[] getSample(int pixel, byte[] pixels) {
		int index = pixel * Image.NUMBER_OF_BANDS;

		byte r = pixels[index];
		byte g = pixels[index + 1];
		byte b = pixels[index + 2];

		return new byte[] { r, g, b };
	}

	protected boolean isBlack(byte[] sample) {
		return sample[0] == 0 && sample[1] == 0 && sample[2] == 0;
	}

	public int positionToIndex(Vector position) {
		if (isNotOnScreen(position)) {
			return -1;
		}

		int x = (int) position.x / gridSize;
		int y = (int) position.y / gridSize;

		return x + y * widthInFields;
	}

	private boolean isNotOnScreen(Vector position) {
		return position.x >= widthInPixels || position.x <= 0
				|| position.y >= widthInPixels || position.y <= 0;
	}

	public Particle getParticle(int index) {
		if (isNotValid(index)) {
			return INACCESSIBLE;
		}

		return bounds[index];
	}

	public void setParticle(Particle particle, int index) {
		if (isNotValid(index)) {
			return;
		}

		bounds[index] = particle;
	}

	public void setAccessible(int index) {
		if (isNotValid(index)) {
			return;
		}

		bounds[index] = ACCESSIBLE;
	}

	private boolean isNotValid(int index) {
		return index < 0 || index >= bounds.length;
	}

	public boolean isAccessible(int index) {
		return index >= 0 && index < bounds.length
				&& bounds[index] == ACCESSIBLE;
	}

	public boolean isInaccessible(int index) {
		return index < 0 || index >= bounds.length
				|| bounds[index] == INACCESSIBLE;
	}

	public void render(GL gl) {
		texture.render(gl);
	}

}
