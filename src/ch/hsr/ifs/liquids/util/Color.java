package ch.hsr.ifs.liquids.util;

public class Color {

	private static final int NUMBER_OF_COLORS = 255;
	
	public float red;
	public float green;
	public float blue;

	public Color() {
		red = green = blue = 0x00;
	}

	public Color(int red, int green, int blue) {
		this.red = red / NUMBER_OF_COLORS;
		this.green = green / NUMBER_OF_COLORS;
		this.blue = blue / NUMBER_OF_COLORS;
	}

}
