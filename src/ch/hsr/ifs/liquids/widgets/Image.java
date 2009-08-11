package ch.hsr.ifs.liquids.widgets;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.color.ColorSpace;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.media.opengl.GL;

import ch.hsr.ifs.liquids.common.Renderable;

public class Image implements Renderable {

	public byte[] data;

	public int width;
	public int height;

	public Image(String path) {
		File file = new File(path);

		BufferedImage image = null;
		try {
			image = ImageIO.read(file);
		} catch (Exception e) {
			// TODO: handle exception
		}

		width = image.getWidth();
		height = image.getHeight();

		WritableRaster raster = createRaster();
		ColorModel colorModel = createColorModel();

		image = createImage(colorModel, raster);

		Graphics2D g = image.createGraphics();
		AffineTransform gt = new AffineTransform();
		gt.translate(0, height);
		gt.scale(1, -1d);
		g.transform(gt);
		g.drawImage(image, null, null);

		DataBufferByte buffer = (DataBufferByte) raster.getDataBuffer();

		data = buffer.getData();
	}

	private WritableRaster createRaster() {
		int dataType = DataBuffer.TYPE_BYTE;
		int bands = 4;

		Point location = null;

		return Raster.createInterleavedRaster(dataType, width, height, bands,
				location);
	}

	private ColorModel createColorModel() {
		ColorSpace colorSpace = ColorSpace.getInstance(ColorSpace.CS_sRGB);

		int[] bits = new int[] { 8, 8, 8, 8 };

		boolean hasAlpha = true;
		boolean isAlphaPremultiplied = false;

		int transparency = ComponentColorModel.TRANSLUCENT;
		int transferType = DataBuffer.TYPE_BYTE;

		return new ComponentColorModel(colorSpace, bits, hasAlpha,
				isAlphaPremultiplied, transparency, transferType);
	}

	private BufferedImage createImage(ColorModel colorModel,
			WritableRaster raster) {
		boolean isRasterPremultiplied = false;
		Hashtable<?, ?> properties = null;

		return new BufferedImage(colorModel, raster, isRasterPremultiplied,
				properties);
	}

	public void render(GL gl) {
		gl.glDrawPixels(width, height, GL.GL_RGBA, GL.GL_UNSIGNED_BYTE, new DataBufferByte(3));
	}

}
