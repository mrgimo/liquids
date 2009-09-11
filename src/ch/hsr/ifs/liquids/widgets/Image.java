package ch.hsr.ifs.liquids.widgets;

import static java.awt.RenderingHints.*;
import static java.awt.geom.AffineTransform.getScaleInstance;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.Buffer;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;
import javax.media.opengl.GL;

import ch.hsr.ifs.liquids.common.Renderable;

public class Image implements Renderable {

	public static final int NUMBER_OF_BANDS = 3;

	private static final int BIT_MASK_RED = 0x00ff0000;
	private static final int BIT_MASK_GREEN = 0x0000ff00;
	private static final int BIT_MASK_BLUE = 0x000000ff;

	private int width;
	private int height;

	private Buffer pixelBuffer;

	public Image(String path) {
		BufferedImage image = readImage(path);

		width = image.getWidth();
		height = image.getHeight();

		pixelBuffer = ByteBuffer.wrap(getPixels(image));
	}

	public Image(String path, int width, int height) {
		BufferedImage image = readImage(path);
		image = scale(image, width, height);

		this.width = image.getWidth();
		this.height = image.getHeight();

		pixelBuffer = ByteBuffer.wrap(getPixels(image));
	}

	private BufferedImage scale(BufferedImage image, int width, int height) {
		int type = BufferedImage.TYPE_INT_ARGB;
		BufferedImage scaledImage = new BufferedImage(width, height, type);

		double scaleX = (double) width / image.getWidth();
		double scaleY = (double) height / image.getHeight();
		AffineTransform xform = getScaleInstance(scaleX, scaleY);
		
		Graphics2D graphics = scaledImage.createGraphics();
		graphics.setRenderingHint(KEY_INTERPOLATION, VALUE_INTERPOLATION_BICUBIC);
		graphics.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
		graphics.setRenderingHint(KEY_RENDERING, VALUE_RENDER_QUALITY);
		graphics.setRenderingHint(KEY_COLOR_RENDERING, VALUE_COLOR_RENDER_QUALITY);
		
		graphics.drawRenderedImage(image, xform);
		graphics.dispose();

		return scaledImage;
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
		byte[] pixels = new byte[width * height * NUMBER_OF_BANDS];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int index = (x + y * width) * NUMBER_OF_BANDS;
				int pixel = image.getRGB(x, y);

				pixels[index] = getRed(pixel);
				pixels[index + 1] = getGreen(pixel);
				pixels[index + 2] = getBlue(pixel);
			}
		}

		swapPixels(pixels);

		return pixels;
	}

	private byte getRed(int pixel) {
		return (byte) ((pixel & BIT_MASK_RED) >> 16);
	}

	private byte getGreen(int pixel) {
		return (byte) ((pixel & BIT_MASK_GREEN) >> 8);
	}

	private byte getBlue(int pixel) {
		return (byte) (pixel & BIT_MASK_BLUE);
	}

	protected void swapPixels(byte[] pixels) {
		int a = 0;
		int b = pixels.length - NUMBER_OF_BANDS;

		while (a < b) {
			swapPixel(a, b, pixels);

			a += NUMBER_OF_BANDS;
			b -= NUMBER_OF_BANDS;
		}
	}

	protected void swapPixel(int a, int b, byte[] pixels) {
		for (int i = 0; i < NUMBER_OF_BANDS; i++) {
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
