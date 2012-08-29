package ch.hsr.ifs.liquids.util.graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLProfile;

import ch.hsr.ifs.liquids.util.Vector;

import com.jogamp.opengl.util.awt.ImageUtil;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.awt.AWTTextureIO;

public class TextureUtil {

	public static Texture initTexture(GL2 gl, File file) throws IOException {
		BufferedImage image = ImageIO.read(file);
		ImageUtil.flipImageVertically(image);

		Texture texture = AWTTextureIO.newTexture(GLProfile.getDefault(), image, false);
		texture.enable(gl);

		return texture;
	}

	public final static void renderTexture(final Vector position,
			final Vector size, final GL2 gl) {
		final float x = position.getX();
		final float y = position.getY();

		renderTexture(x, y, size, gl);
	}

	public static void renderTexture(float x, float y, Vector size, GL2 gl) {
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
