package ch.hsr.ifs.liquids.widgets;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.Buffer;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;
import javax.media.opengl.GL;

import sun.awt.image.ByteInterleavedRaster;
import ch.hsr.ifs.liquids.common.Renderable;

public class Image implements Renderable {

	public static final int NUMBER_OF_BANDS = 3;

	private int width;
	private int height;

	private Buffer pixelBuffer;

	public Image(String path) {
		BufferedImage image = readImage(path);

		width = image.getWidth();
		height = image.getHeight();

		pixelBuffer = ByteBuffer.wrap(getPixels(image));
	}

	private BufferedImage readImage(String path) {
		File file = new File(path);
		try {
			return ImageIO.read(file);
		} catch (Exception e) {
			String message = "reading image at '" + path + "' has failed";
			throw new RuntimeException(message, e);
		}
	}

	protected byte[] getPixels(BufferedImage image) {
		ByteInterleavedRaster raster = (ByteInterleavedRaster) image.getData();
		byte[] pixels = raster.getByteData(0, 0, width, height, (byte[]) null);

		swapPixels(pixels);

		return pixels;
	}

	protected void swapPixels(byte[] pixels) {
		int a = 0;
		int b = pixels.length - 3;

		while (a < b) {
			swapPixel(a, b, pixels);

			a += 3;
			b -= 3;
		}
	}

	protected void swapPixel(int a, int b, byte[] pixels) {
		for (int i = 0; i < 3; i++) {
			swap(a + i, b + i, pixels);
		}
	}

	protected void swap(int a, int b, byte[] array) {
		array[a] ^= array[b];
		array[b] ^= array[a];
		array[a] ^= array[b];
	}

	public void render(GL gl) {
		gl.glDrawPixels(width, height, GL.GL_RGB, GL.GL_UNSIGNED_BYTE,
				pixelBuffer);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Buffer getPixelBuffer() {
		return pixelBuffer;
	}

}
