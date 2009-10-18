package ch.hsr.ifs.liquids.helpers.factories;

import ch.hsr.ifs.liquids.util.Color;

public class ColorFactory {

	private enum DefaultColor {

		BLACK(0, 0, 0),
		WHITE(255, 255, 255),
		RED(255, 0, 0),
		GREEN(0, 255, 0),
		BLUE(0, 0, 255),
		YELLOW(255, 255, 0),
		CYAN(0, 255, 255),
		MAGENTA(255, 0, 255);

		private int r;
		private int g;
		private int b;

		private DefaultColor(int r, int g, int b) {
			this.r = r;
			this.g = g;
			this.b = b;
		}

		public Color getInstance() {
			return new Color(r, g, b);
		}

	}

	public static Color createColor(String colorName) {
		colorName = formatInput(colorName);

		for (DefaultColor color : DefaultColor.values()) {
			if (colorName.equals(color.name())) {
				return color.getInstance();
			}
		}

		return DefaultColor.WHITE.getInstance();
	}

	private static String formatInput(String input) {
		return input.toUpperCase().trim();
	}

}
