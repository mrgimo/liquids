package ch.hsr.ifs.liquids.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import ch.hsr.ifs.liquids.widgets.Image;

public class PlayingFieldTest {

	private static final String BOUNDS_PATH = "test_data/test_bounds.png";
	private static final String TEXTURE_PATH = "test_data/test_texture.jpg";

	private static final int GRID_SIZE = 2;

	private PlayingField playingField;

	@Before
	public void setUp() {
		playingField = new PlayingField(BOUNDS_PATH, TEXTURE_PATH, GRID_SIZE);
	}

	@Test
	public void testExtractBoundsValue() {
		byte[] pixels = createPixels(playingField.widthInPixels * GRID_SIZE);
		int index = 2;

		Particle particle = playingField.extractBoundsValue(index, pixels);
		assertEquals(PlayingField.ACCESSIBLE, particle);
	}

	@Test
	public void testCalcPixelIndex() {
		int index = 1;

		int expected = GRID_SIZE;
		int actual = playingField.calcPixelIndex(index);

		assertEquals(expected, actual);
	}

	@Test
	public void testGetSample() {
		byte[] pixels = createPixels(15);
		int pixel = 4;

		byte[] samples = playingField.getSample(pixel, pixels);

		assertEquals(pixels[pixel * Image.NUMBER_OF_BANDS], samples[0]);
		assertEquals(pixels[pixel * Image.NUMBER_OF_BANDS + 1], samples[1]);
		assertEquals(pixels[pixel * Image.NUMBER_OF_BANDS + 2], samples[2]);
	}

	private byte[] createPixels(int numberOfPixels) {
		byte[] pixels = new byte[numberOfPixels * Image.NUMBER_OF_BANDS];

		for (int i = 0; i < pixels.length; i++) {
			byte band = ((Double) (Math.random() * 255)).byteValue();
			pixels[i] = band;
		}

		return pixels;
	}

	@Test
	public void testIsBlack() {
		byte[] black = { 0, 0, 0 };
		byte[] white = { (byte) 255, (byte) 255, (byte) 255 };

		boolean isBlack = playingField.isBlack(black);
		boolean isWhite = playingField.isBlack(white);

		assertTrue(isBlack);
		assertFalse(isWhite);
	}

}
