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

	public static void renderTexture(Vector position, float width,
			float height, GL gl) {
		float x = position.getX();
		float y = position.getY();

		float w = width / 2;
		float h = height / 2;

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
