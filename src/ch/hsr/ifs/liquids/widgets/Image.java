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

	public static final int NUMBER_OF_BANDS = 4;

	private int width;
	private int height;

	private Buffer pixelBuffer;

	public Image(String path) {
		BufferedImage image = readImage(path);

		width = image.getWidth();
		height = image.getHeight();

		byte[] pixels = getPixelsAsByteArray(image);

		pixelBuffer = ByteBuffer.wrap(pixels);
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

	protected byte[] getPixelsAsByteArray(BufferedImage image) {
		byte[] pixels = new byte[width * height * NUMBER_OF_BANDS];
		
		int i = 0;
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				int[] pixel = image.getData().getPixel(x, y, new int[4]);
				ByteInterleavedRaster raster = (ByteInterleavedRaster) image.getData();

				for(int sample : pixel){
					pixels[i++] = (byte) sample;
				}
			}
		}
		
		return pixels;
	}

	public void render(GL gl) {
		gl.glDrawPixels(width, height, GL.GL_RGBA, GL.GL_UNSIGNED_BYTE, pixelBuffer);
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
