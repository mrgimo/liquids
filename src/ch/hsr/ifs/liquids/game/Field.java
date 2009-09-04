package ch.hsr.ifs.liquids.game;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Map;

import ch.hsr.ifs.liquids.widgets.Image;

public class Field {

	protected Image texture;
	protected byte[] bounds;

	public static Field creatField() {
		String mapName = (String) Config.map.get("name");
		Map<String, String> config = Config.maps.get(mapName);

		String texturePath = config.get("texture");
		String boundsPath = config.get("bounds");

		int gridSize = (Integer) Config.map.get("gridSize");

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = screenSize.width - screenSize.width % gridSize;
		int height = screenSize.height - screenSize.height % gridSize;

		Image texture = new Image(texturePath, width, height);
		Image bounds = new Image(boundsPath, width, height);

		Field field = new Field();
		field.texture = texture;
		field.bounds = createBounds(bounds, gridSize);

		return field;
	}

	private static byte[] createBounds(Image image, int gridSize) {
		int boundsWidth = image.getWidth() / gridSize;
		int boundsHeight = image.getHeight() / gridSize;

		byte[] bounds = new byte[boundsWidth * boundsHeight];

		for (int i = 0; i < bounds.length; i++) {

		}

		return null;
	}

}
