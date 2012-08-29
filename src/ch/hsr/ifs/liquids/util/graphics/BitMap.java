package ch.hsr.ifs.liquids.util.graphics;

import static java.awt.geom.AffineTransform.getScaleInstance;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.jogamp.opengl.util.awt.ImageUtil;

public class BitMap {

	private static final int IMAGE_TYPE = BufferedImage.TYPE_INT_ARGB;

	public enum Bit {
		BLACK, WHITE;
	}

	public final Bit[][] bits;

	public BitMap(File file, int width, int height) {
		BufferedImage image = readImage(file);
		image = scale(image, width, height);

		ImageUtil.flipImageVertically(image);

		bits = initBits(image);
	}

	private BufferedImage readImage(File file) {
		try {
			return ImageIO.read(file);
		} catch (Exception e) {
			String message = "reading image at '" + file.getAbsolutePath()
					+ "' has failed";
			throw new RuntimeException(message, e);
		}
	}

	private BufferedImage scale(BufferedImage image, int width, int height) {
		BufferedImage scaledImage = new BufferedImage(width, height, IMAGE_TYPE);

		double scaleX = (double) width / image.getWidth();
		double scaleY = (double) height / image.getHeight();
		AffineTransform xform = getScaleInstance(scaleX, scaleY);

		Graphics2D graphics = scaledImage.createGraphics();
		graphics.drawRenderedImage(image, xform);
		graphics.dispose();

		return scaledImage;
	}

	private Bit[][] initBits(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();

		Bit[][] bits = new Bit[width][height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if ((image.getRGB(x, y) & 0x00ffffff) == 0){
					bits[x][y] = Bit.BLACK;
				} else {
					bits[x][y] = Bit.WHITE;
				}
			}
		}
		
		return bits;
	}

}
