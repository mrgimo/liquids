package ch.hsr.ifs.liquids.game;

import java.util.Map;

import javax.media.opengl.GL;

import ch.hsr.ifs.liquids.common.Renderable;
import ch.hsr.ifs.liquids.widgets.Image;

public class PlayingField implements Renderable {

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
		int width = bounds.width * bounds.gridSize;
		int height = bounds.height * bounds.gridSize;
		return new Image(path, width, height);
	}

	private Bounds initBounds(Map<String, String> config) {
		String path = config.get("bounds");
		return new Bounds(path);
	}

	public void render(GL gl) {
		texture.render(gl);
	}


}
