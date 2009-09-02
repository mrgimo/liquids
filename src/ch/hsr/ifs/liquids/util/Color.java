package ch.hsr.ifs.liquids.util;

public class Color {

	public byte red, green, blue, alpha;

	public Color() {
		red = green = blue = alpha = 0;
	}

	public Color(byte red, byte green, byte blue) {
		this(red, green, blue, (byte) 0);
	}

	public Color(byte red, byte green, byte blue, byte alpha) {
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.alpha = alpha;
	}
}
