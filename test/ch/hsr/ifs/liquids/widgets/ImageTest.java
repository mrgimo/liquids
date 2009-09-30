package ch.hsr.ifs.liquids.widgets;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class ImageTest {

	private static final String IMAGE_PATH = "test_data/test_image.png";

	private static final int width = 800;
	private static final int height = 600;

	private Image image;

	@Before
	public void init() {
		image = new Image(IMAGE_PATH, width, height);
	}

	@Test
	public void testGetPixels() throws IOException {
		byte[] pixels = (byte[]) image.getPixelBuffer().array();
		
		assertEquals(width * height * Image.NUMBER_OF_BANDS, pixels.length);
	}

	@Test
	public void testSwapPixels() {
		byte[] pixels1 = { 87, 23, 85, 34, 65, 38, 21, 30, 89, 93, 58, 91 };

		image.swapPixels(pixels1);

		assertEquals(93, pixels1[0]);
		assertEquals(58, pixels1[1]);
		assertEquals(91, pixels1[2]);

		assertEquals(21, pixels1[3]);
		assertEquals(30, pixels1[4]);
		assertEquals(89, pixels1[5]);

		assertEquals(34, pixels1[6]);
		assertEquals(65, pixels1[7]);
		assertEquals(38, pixels1[8]);

		assertEquals(87, pixels1[9]);
		assertEquals(23, pixels1[10]);
		assertEquals(85, pixels1[11]);
	}

	@Test
	public void testSwapPixel() {
		byte[] pixels = { 28, 72, 92, 53, 23, 12 };

		image.swapPixel(0, 3, pixels);

		assertEquals(53, pixels[0]);
		assertEquals(23, pixels[1]);
		assertEquals(12, pixels[2]);

		assertEquals(28, pixels[3]);
		assertEquals(72, pixels[4]);
		assertEquals(92, pixels[5]);
	}

	@Test
	public void testSwap() {
		byte[] array = { 34, 98 };

		image.swap(0, 1, array);

		assertEquals(98, array[0]);
		assertEquals(34, array[1]);
	}

}
