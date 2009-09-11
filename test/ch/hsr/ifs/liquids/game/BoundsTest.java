package ch.hsr.ifs.liquids.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import ch.hsr.ifs.liquids.widgets.Image;

public class BoundsTest {

	private static final String TEST_IMAGE_PATH = "test_data/test_bounds.png";

	private Bounds bounds;

	byte[] pixels = { 32, 78, 24, 67, 32, 4, 23, 92, 45, 29, 45, 34, 67, 32, 4,
			23, 92, 45, 29, 45, 34, 78, 89, 28, 67, 32, 4, 23, 92, 45, 29, 45,
			34, 78, 89, 28, 67, 32, 4, 23, 92, 45, 29, 45, 34, 78, 89, 28, 67,
			32, 4, 23, 92, 45, 29, 45, 34, 78, 89, 28, 67, 32, 4, 23, 92, 45,
			29, 45, 34, 78, 89, 28, 67, 32, 4, 23, 92, 45, 29, 45, 34, 78, 89,
			28, 67, 32, 4, 23, 92, 45, 29, 45, 34, 78, 89, 28, 67, 32, 4, 23,
			92, 45, 29, 45, 34, 78, 89, 28, 67, 32, 4, 23, 92, 45, 29, 45, 34,
			78, 89, 28, 67, 32, 4, 23, 92, 45, 29, 45, 34, 78, 89, 28, 67, 32,
			4, 23, 92, 45, 29, 45, 34, 78, 89, 28, 67, 32, 4, 23, 92, 45, 29,
			45, 34, 78, 89, 28, 67, 32, 4, 23, 92, 45, 29, 45, 34, 78, 89, 28,
			67, 32, 4, 23, 92, 45, 29, 45, 34, 78, 89, 28, 67, 32, 4, 23, 92,
			45, 29, 45, 34, 78, 89, 28, 67, 32, 4, 23, 92, 45, 29, 45, 34, 78,
			89, 28, 67, 32, 4, 23, 92, 45, 29, 45, 34, 78, 89, 28, 67, 32, 4,
			23, 92, 45, 29, 45, 34, 78, 89, 28, 67, 32, 4, 23, 92, 45, 29, 45,
			34, 78, 89, 28, 67, 32, 4, 23, 92, 45, 29, 45, 34, 78, 89, 28, 67,
			32, 4, 23, 92, 45, 29, 45, 34, 78, 89, 28, 67, 32, 4, 23, 92, 45,
			29, 45, 34, 78, 89, 28, 67, 32, 4, 23, 92, 45, 29, 45, 34, 78, 89,
			28, 78, 89, 28, 73, 84, 87 };

	@Before
	public void setUp() {
		bounds = new Bounds(null);
	}

	@Test
	public void testInitBounds() {
		int width = 100;
		int height = 100;

		bounds.gridSize = 5;
		bounds.width = width;

		Image image = new Image(TEST_IMAGE_PATH, width, height);
		bounds.initBounds(image);
	}

	@Test
	public void testExtractBoundsValue() {
		bounds.gridSize = 2;
		bounds.width = 3;

		int index = 2;

		Particle actual = bounds.extractBoundsValue(index, pixels);

		assertEquals(Bounds.ACCESSIBLE, actual);
	}

	@Test
	public void testCalcPixelIndex() {
		bounds.gridSize = 2;
		bounds.width = 3;

		int index = 5;
		int actual = bounds.calcPixelIndex(index);

		assertEquals(16, actual);
	}

	@Test
	public void testGetSample() {
		int pixel = 4;

		byte[] samples = bounds.getSample(pixel, pixels);

		assertEquals(67, samples[0]);
		assertEquals(32, samples[1]);
		assertEquals(4, samples[2]);
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
