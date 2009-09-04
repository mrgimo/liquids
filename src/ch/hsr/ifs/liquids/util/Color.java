package ch.hsr.ifs.liquids.util;

import java.util.HashMap;
import java.util.Map;

public class Color {

	private static final Byte[] BLACK = { (byte) 0, (byte) 0, (byte) 0 };
	private static final Byte[] WHITE = { (byte) 255, (byte) 255, (byte) 255 };
	private static final Byte[] RED = { (byte) 255, (byte) 0, (byte) 0 };
	private static final Byte[] GREEN = { (byte) 0, (byte) 255, (byte) 0 };
	private static final Byte[] BLUE = { (byte) 0, (byte) 0, (byte) 255 };
	private static final Byte[] YELLOW = { (byte) 255, (byte) 255, (byte) 0 };
	private static final Byte[] CYAN = { (byte) 0, (byte) 255, (byte) 255 };
	private static final Byte[] MAGENTA = { (byte) 255, (byte) 0, (byte) 255 };

	private static Map<String, Byte[]> defaultColors;

	static {
		defaultColors = new HashMap<String, Byte[]>();

		defaultColors.put("black", BLACK);
		defaultColors.put("white", WHITE);
		defaultColors.put("red", RED);
		defaultColors.put("green", GREEN);
		defaultColors.put("blue", BLUE);
		defaultColors.put("yellow", YELLOW);
		defaultColors.put("cyan", CYAN);
		defaultColors.put("magenta", MAGENTA);
	}

	public byte red;
	public byte green;
	public byte blue;

	public Color() {
		red = green = blue = 0x00;
	}

	public Color(int red, int green, int blue) {
		this.red = (byte) red;
		this.green = (byte) green;
		this.blue = (byte) blue;
	}

	public static Color getColorByName(String name) {
		Byte[] color = defaultColors.get(name);
		return new Color(color[0], color[1], color[2]);
	}

}
