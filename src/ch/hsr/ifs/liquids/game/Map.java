package ch.hsr.ifs.liquids.game;

import ch.hsr.ifs.liquids.widgets.Image;

public class Map {

	protected Image map;
	protected Image texture;
	
	public void setMap(String map) {
		this.map = new Image(map);
	}
	
	public void setTexture(String texture) {
		this.texture = new Image(texture);
	}
	
}
