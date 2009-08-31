package ch.hsr.ifs.liquids.widgets;

import static org.junit.Assert.assertEquals;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Before;
import org.junit.Test;

import sun.awt.image.ByteInterleavedRaster;

public class ImageTest {
	
	private static final String IMAGE_PATH = "test_data/test_image.png";
	
	private Image image;

	@Before
	public void init() {
		image = new Image(IMAGE_PATH);
	}

	@Test
	public void testGetPixelsFrom() throws IOException {
		BufferedImage bufferedImage = ImageIO.read(new File(IMAGE_PATH));
		byte[] pixels = image.getPixelsAsByteArray(bufferedImage);

		int expectedLength = image.getWidth() * image.getHeight() * Image.NUMBER_OF_BANDS;
		int actualLength = pixels.length;

		assertEquals(expectedLength, actualLength);
	}
	
	@Test
	public void bla() throws IOException {

		BufferedImage bufferedImage = ImageIO.read(new File(IMAGE_PATH));
		
		
		byte[] pixels = image.getPixelsAsByteArray(bufferedImage);
		
		for(byte b : pixels){
			System.out.println(b);
		}
		System.out.println();
		
		for(byte b : ((ByteInterleavedRaster) bufferedImage.getData()).getByteData(0, 0, image.getWidth(), image.getHeight(), (byte[]) null)){
			System.out.println(b);
		}
		
	}
	
}
