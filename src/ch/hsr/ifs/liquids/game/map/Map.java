package ch.hsr.ifs.liquids.game.map;

import javax.media.opengl.GL;

import ch.hsr.ifs.liquids.common.Renderable;

public class Map implements Renderable {

	private String name;

	private int numberOfPlayers;
	private int numberOfFighters;

	public void render(GL gl) {
		// TODO Auto-generated method stub
		
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setNumberOfPlayers(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}
	
	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}
	
	public void setNumberOfFighters(int numberOfFighters) {
		this.numberOfFighters = numberOfFighters;
	}
	
	public int getNumberOfFighters() {
		return numberOfFighters;
	}
	
	public void setMap(String mapPath) {
		//TODO: init Map
	}
	
	public void setTexture(String texturePath) {
		//TODO: init Texture
	}
	
	public void setThumbnail(String thumbnailPath) {
		//TODO: init Thumbnail
	}
	
	public void setGrid(float gridSize) {
		//TODO: init Grid
	}

}
