package ch.hsr.ifs.liquids.game;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.media.opengl.GL;

import ch.hsr.ifs.liquids.common.Renderable;
import ch.hsr.ifs.liquids.util.Vector;
import ch.hsr.ifs.liquids.widgets.Image;

public class PlayingField implements Renderable {

	
	protected enum Bounds {
		ACCESSIBLE, BOUNDS, PARTICLE;
		
		public Particle particle;
	}
	
	
	protected static final Particle ACCESSIBLE = new Particle();
	protected static final Particle BOUNDS = null;

	protected Particle[] bounds;
	protected Renderable texture;

	protected int gridSize;

	protected int widthInPixels;
	protected int heightInPixels;

	protected int widthInFields;
	protected int heightInFields;

	public PlayingField(String boundsPath, String texturePath, int gridSize) {
		this.gridSize = gridSize;

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		widthInPixels = calcWidthInPixels(screen.width);
		heightInPixels = calcHeightInPixels(screen.height);

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
					return BOUNDS;
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
		int x = (int) position.x / gridSize;
		int y = (int) position.y / gridSize;

		return x + y * widthInFields;
	}

	public Particle getParticle(Vector position) {
		return getParticle(positionToIndex(position));
	}

	public Particle getParticle(int index) {
		return bounds[index];
	}

	public void setParticle(Particle particle, Vector position) {
		setParticle(particle, positionToIndex(position));
	}

	public void setParticle(Particle particle, int index) {
		bounds[index] = particle;
	}

	public void setAccessible(int index) {
		bounds[index] = ACCESSIBLE;
	}

	public boolean isAccessible(Vector position) {
		return isAccessible(positionToIndex(position));
	}

	public boolean isAccessible(int index) {
		return index >= 0 && index < bounds.length
				&& bounds[index] == ACCESSIBLE;
	}

	public boolean isBound(int index) {
		return index < 0 || index >= bounds.length || bounds[index] == BOUNDS;
	}

	public void render(GL gl) {
		texture.render(gl);
	}


}
