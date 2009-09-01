package ch.hsr.ifs.liquids.util;


public class Color {

	public float red, green, blue, alpha;

	public Color() {
		this(0, 0, 0, 0);
	}

	public Color(float red, float green, float blue) {
		this(red, green, blue, 0);
	}

	public Color(float red, float green, float blue, float alpha) {
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.alpha = alpha;
	}

}
