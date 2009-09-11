package ch.hsr.ifs.liquids.game;

import java.util.Map;

import ch.hsr.ifs.liquids.widgets.Image;

public class PlayingField {

	protected Image texture;
	protected Bounds bounds;

	public PlayingField() {
		Map<String, String> config = getConfig();
		
		bounds = initBounds(config);
		texture = initTexture(config);
	}

	private Map<String, String> getConfig() {
		String mapName = (String) Config.map.get("name");
		return Config.maps.get(mapName);
	}

	private Image initTexture(Map<String, String> config) {
		String path = config.get("texture");
		return new Image(path, bounds.width, bounds.height);
	}

	private Bounds initBounds(Map<String, String> config) {
		String path = config.get("bounds");
		Image image = new Image(path);

		return new Bounds(image);
	}


}
