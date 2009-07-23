package ch.hsr.ifs.liquid.util;

/**
 * pulls together static method for manipulating int color values.
 * 
 * @author kungfoo
 * 
 */
public class Color {
	/**
	 * Interpolate between two colors. the amount value is, where between the
	 * two values the new color will be.<br>
	 * 0.0f is completely c1, 1.0f completely c2. individual color components of
	 * a color supplied as an int value.
	 */
	public static final int lerpColor(int c1, int c2, float amt) {
		float a1 = ((c1 >> 24) & 0xff);
		float r1 = (c1 >> 16) & 0xff;
		float g1 = (c1 >> 8) & 0xff;
		float b1 = c1 & 0xff;
		float a2 = (c2 >> 24) & 0xff;
		float r2 = (c2 >> 16) & 0xff;
		float g2 = (c2 >> 8) & 0xff;
		float b2 = c2 & 0xff;

		return (((int) (a1 + (a2 - a1) * amt) << 24) | ((int) (r1 + (r2 - r1) * amt) << 16)
				| ((int) (g1 + (g2 - g1) * amt) << 8) | ((int) (b1 + (b2 - b1) * amt)));
	}
}
