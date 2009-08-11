package ch.hsr.ifs.liquids.game.map;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.FloatBuffer;

import javax.imageio.ImageIO;
import javax.media.opengl.GL;

import ch.hsr.ifs.liquids.common.Renderable;

public class Map implements Renderable {

	private String name;

	private int numberOfPlayers;
	private int numberOfFighters;

	private Buffer map;

	public void render(GL gl) {
		gl.glDrawPixels(7, 6, 5, 5, map);
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
		File mapFile = new File(mapPath);

		BufferedImage image = null;
		try {
			image = ImageIO.read(mapFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		int width = image.getWidth();
		int height = image.getHeight();

		float[] pixels = new float[width * height];
		pixels = image.getData().getPixels(0, 0, width, height, pixels);

		map = FloatBuffer.wrap(pixels, width, pixels.length);
	}

	public void setTexture(String texturePath) {
		// TODO: init Texture
	}

	public void setThumbnail(String thumbnailPath) {
		// TODO: init Thumbnail
	}

	public void setGrid(float gridSize) {
		// TODO: init Grid
	}

}
