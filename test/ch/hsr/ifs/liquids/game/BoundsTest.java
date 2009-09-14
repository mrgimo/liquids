package ch.hsr.ifs.liquids.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.hsr.ifs.liquids.widgets.Image;

public class BoundsTest {

	private static final String TEST_IMAGE_PATH = "test_data/test_bounds.png";

	private static final int NUMBER_OF_BANDS = 3;

	private static final int GRID_SIZE = 2;

	private static final int WIDTH = 2;
	private static final int HEIGHT = 2;

	private static byte[] pixels;

	private Bounds bounds;

	@BeforeClass
	public static void setUpClass() {
		int size = WIDTH * HEIGHT * GRID_SIZE * GRID_SIZE * NUMBER_OF_BANDS;
		pixels = new byte[size];

		for (int i = 0; i < pixels.length; i++) {
			byte band = ((Double) (Math.random() * 255)).byteValue();
			pixels[i] = band;
		}
	}

	@Before
	public void setUp() {
		bounds = new Bounds(TEST_IMAGE_PATH);

		bounds.bounds = new Particle[WIDTH * HEIGHT];

		bounds.gridSize = GRID_SIZE;

		bounds.width = WIDTH;
		bounds.height = HEIGHT;
	}

	@Test
	public void testInitBounds() {
		Image image = new Image(TEST_IMAGE_PATH, WIDTH * GRID_SIZE, HEIGHT * GRID_SIZE);
		bounds.initBounds(image);
	}

	@Test
	public void testExtractBoundsValue() {
		int index = 2;
		Particle actual = bounds.extractBoundsValue(index, pixels);

		assertEquals(Bounds.ACCESSIBLE, actual);
	}

	@Test
	public void testCalcPixelIndex() {
		int index = 1;

		int expected = GRID_SIZE;
		int actual = bounds.calcPixelIndex(index);

		assertEquals(expected, actual);
	}

	@Test
	public void testGetSample() {
		int pixel = 4;

		byte[] samples = bounds.getSample(pixel, pixels);

		assertEquals(pixels[pixel * NUMBER_OF_BANDS], samples[0]);
		assertEquals(pixels[pixel * NUMBER_OF_BANDS + 1], samples[1]);
		assertEquals(pixels[pixel * NUMBER_OF_BANDS + 2], samples[2]);
	}

	@Test
	public void testIsBlack() {
		byte[] black = { 0, 0, 0 };
		byte[] white = { (byte) 255, (byte) 255, (byte) 255 };

		boolean isBlack = bounds.isBlack(black);
		boolean isWhite = bounds.isBlack(white);

		assertTrue(isBlack);
		assertFalse(isWhite);
	}

}
