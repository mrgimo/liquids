package ch.hsr.ifs.liquids.widgets;

import static java.awt.RenderingHints.KEY_ANTIALIASING;
import static java.awt.RenderingHints.KEY_COLOR_RENDERING;
import static java.awt.RenderingHints.KEY_INTERPOLATION;
import static java.awt.RenderingHints.KEY_RENDERING;
import static java.awt.RenderingHints.VALUE_ANTIALIAS_ON;
import static java.awt.RenderingHints.VALUE_COLOR_RENDER_QUALITY;
import static java.awt.RenderingHints.VALUE_INTERPOLATION_BICUBIC;
import static java.awt.RenderingHints.VALUE_RENDER_QUALITY;
import static java.awt.geom.AffineTransform.getScaleInstance;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.Buffer;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;
import javax.media.opengl.GL;

public class Image extends Element {

	private static final int RED_SHIFT = 16;
	private static final int GREEN_SHIFT = 8;
	private static final int BLUE_SHIFT = 0;

	public static final int NUMBER_OF_BANDS = 3;

	private Buffer pixelBuffer;

	public Image(String path, int width, int height) {
		this.width = width;
		this.height = height;

		BufferedImage image = readImage(path);
		image = scale(image);

		byte[] pixels = getPixels(image);
		pixelBuffer = ByteBuffer.wrap(pixels);
	}

	private BufferedImage scale(BufferedImage image) {
		int type = BufferedImage.TYPE_INT_ARGB;
		BufferedImage scaledImage = new BufferedImage(width, height, type);

		double scaleX = (double) width / image.getWidth();
		double scaleY = (double) height / image.getHeight();
		AffineTransform xform = getScaleInstance(scaleX, scaleY);

		Graphics2D graphics = scaledImage.createGraphics();
		setRenderingHints(graphics);

		graphics.drawRenderedImage(image, xform);
		graphics.dispose();

		return scaledImage;
	}

	private void setRenderingHints(Graphics2D graphics) {
		graphics.setRenderingHint(KEY_INTERPOLATION,
				VALUE_INTERPOLATION_BICUBIC);
		graphics.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
		graphics.setRenderingHint(KEY_RENDERING, VALUE_RENDER_QUALITY);
		graphics.setRenderingHint(KEY_COLOR_RENDERING,
				VALUE_COLOR_RENDER_QUALITY);
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
		return (byte) (pixel >> RED_SHIFT);
	}

	private byte getGreen(int pixel) {
		return (byte) (pixel >> GREEN_SHIFT);
	}

	private byte getBlue(int pixel) {
		return (byte) (pixel >> BLUE_SHIFT);
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

	public Buffer getPixelBuffer() {
		return pixelBuffer;
	}

}
