package ch.hsr.ifs.liquids.util.graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.media.opengl.GL;

import ch.hsr.ifs.liquids.util.Vector;

import com.sun.opengl.util.ImageUtil;
import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;

public class TextureUtil {

	public static Texture loadTexture(File file) throws IOException {
		BufferedImage image = ImageIO.read(file);
		ImageUtil.flipImageVertically(image);

		Texture texture = TextureIO.newTexture(image, true);
		texture.enable();

		return texture;
	}

	public final static void renderTexture(final Vector position,
			final Vector size, final GL gl) {
		final float x = position.getX();
		final float y = position.getY();

		renderTexture(x, y, size, gl);
	}

	public static void renderTexture(float x, float y, Vector size, GL gl) {
		final float w = size.getX() / 2;
		final float h = size.getY() / 2;

		gl.glBegin(GL.GL_TRIANGLE_STRIP);

		gl.glTexCoord2f(1, 1);
		gl.glVertex3f(x + w, y + h, 0);

		gl.glTexCoord2f(0, 1);
		gl.glVertex3f(x - w, y + h, 0);

		gl.glTexCoord2f(1, 0);
		gl.glVertex3f(x + w, y - h, 0);

		gl.glTexCoord2f(0, 0);
		gl.glVertex3f(x - w, y - h, 0);

		gl.glEnd();
	}

}
