package ch.hsr.ifs.liquids.game;

import java.awt.Dimension;
import java.awt.Toolkit;

import ch.hsr.ifs.liquids.util.Config;
import ch.hsr.ifs.liquids.widgets.Image;

public class Bounds {

	protected static final Particle ACCESSIBLE = new Particle();
	protected static final Particle BOUNDS = null;

	protected int gridSize;

	protected int width;
	protected int height;

	protected Particle[] bounds;

	public Bounds(String imagePath) {
		gridSize = (Integer) Config.map.get("gridSize");

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int widthInPixels = calcWidthInPixels(screen.width);
		int heightInPixels = calcHeightInPixels(screen.height);

		width = widthInPixels / gridSize;
		height = heightInPixels / gridSize;

		Image image = new Image(imagePath, widthInPixels, heightInPixels);
		bounds = initBounds(image);
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

	protected Particle[] initBounds(Image image) {
		byte[] pixels = (byte[]) image.getPixelBuffer().array();

		Particle[] bounds = new Particle[width * height];
		for (int i = 0; i < bounds.length; i++) {
			bounds[i] = extractBoundsValue(i, pixels);
		}

		return bounds;
	}

	protected Particle extractBoundsValue(int index, byte[] pixels) {
		int blackPixels = 0;
		int whitePixels = 0;

		int firstPixel = calcPixelIndex(index);
		for (int x = 0; x < gridSize; x++) {
			for (int y = 0; y < gridSize; y++) {
				int pixel = firstPixel + x + y * width * gridSize;

				byte[] sample = getSample(pixel, pixels);
				if (isBlack(sample)) {
					blackPixels++;
				} else {
					whitePixels++;
				}
			}
		}

		return blackPixels > whitePixels ? BOUNDS : ACCESSIBLE;
	}

	protected int calcPixelIndex(int index) {
		int x = index % width * gridSize;
		int y = index / width * gridSize;

		return x + y * width * gridSize;
	}

	protected byte[] getSample(int pixel, byte[] pixels) {
		int index = pixel * Image.NUMBER_OF_BANDS;

		byte red = pixels[index];
		byte green = pixels[index + 1];
		byte blue = pixels[index + 2];

		return new byte[] { red, green, blue };
	}

	protected boolean isBlack(byte[] sample) {
		return sample[0] == 0 && sample[1] == 0 && sample[2] == 0;
	}

	public boolean hasParticleAt(int index) {
		return bounds[index] != BOUNDS && !isAccessible(index);
	}

	public boolean isAccessible(int index) {
		return bounds[index] == ACCESSIBLE;
	}

	public Particle getParticleAt(int index) {
		return bounds[index];
	}

}
