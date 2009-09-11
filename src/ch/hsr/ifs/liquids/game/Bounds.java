package ch.hsr.ifs.liquids.game;

import java.awt.Dimension;
import java.awt.Toolkit;

import ch.hsr.ifs.liquids.widgets.Image;

public class Bounds {

	private static final Particle ACCESSIBLE = new Particle();
	private static final Particle BOUNDS = null;

	protected int gridSize;

	protected int width;
	protected int height;

	protected Particle[] bounds;

	public Bounds(Image image) {
		gridSize = getGridSize();

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		width = calcWidth(screen.width);
		height = calcHeight(screen.height);

		bounds = initBounds(image);
	}

	private int getGridSize() {
		return (Integer) Config.map.get("gridSize");
	}

	private int calcWidth(int screenWidth) {
		return subtractOffcut(screenWidth, gridSize) / gridSize;
	}

	private int calcHeight(int screenHeight) {
		return subtractOffcut(screenHeight, gridSize) / gridSize;
	}

	private int subtractOffcut(int value, int factor) {
		return value - value % factor;
	}

	private Particle[] initBounds(Image image) {
		byte[] pixels = (byte[]) image.getPixelBuffer().array();

		Particle[] bounds = new Particle[width * height];
		for (int i = 0; i < bounds.length; i++) {
			bounds[i] = extractBoundsValue(i, pixels);
		}

		return bounds;
	}

	private Particle extractBoundsValue(int index, byte[] pixels) {
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

	private int calcPixelIndex(int index) {
		int x = index % width * gridSize;
		int y = index / width * gridSize;
		return x + y * width * gridSize;
	}

	private byte[] getSample(int pixel, byte[] pixels) {
		int index = pixel * Image.NUMBER_OF_BANDS;

		byte red = pixels[index];
		byte green = pixels[index + 1];
		byte blue = pixels[index + 2];

		return new byte[] { red, green, blue };
	}

	private boolean isBlack(byte[] sample) {
		return sample[0] == 0 && sample[1] == 0 && sample[2] == 0;
	}

}
