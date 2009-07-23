package ch.hsr.ifs.liquid.model.game;

import processing.core.PApplet;
import processing.core.PImage;
import ch.hsr.ifs.liquid.model.game.warrior.Warrior;
import ch.hsr.ifs.liquid.util.Vector;
import ch.hsr.ifs.liquid.view.core.Element;

public class Map extends Element {
	private static final String EXAMPLE_MAP = "../data/images/maps/map.png";

	private static final int ALPHA_MASK = 0xff000000;

	private static final int ACCESSIBLE = 0xffffffff;
	private static final int INACCESSIBLE = 0xfffffffe;

	private PImage image;

	protected int[] grid;
	protected int gridWidth;

	public Map(PApplet applet) {
		image = createImage(applet);
		grid = createGrid(image);
	}

	private PImage createImage(PApplet applet) {
		PImage image = applet.loadImage(EXAMPLE_MAP);

		int horizontalBorder = applet.width % Warrior.SIZE;
		int verticalBorder = applet.height % Warrior.SIZE;

		int imageWidth = applet.width - horizontalBorder;
		int imageHeight = applet.height - verticalBorder;

		position.x = horizontalBorder / 2;
		position.y = verticalBorder / 2;

		image.resize(imageWidth, imageHeight);

		return image;
	}

	private int[] createGrid(PImage image) {
		gridWidth = image.width / Warrior.SIZE;
		int gridHeight = image.height / Warrior.SIZE;

		int[] grid = new int[gridWidth * gridHeight];

		for (int i = 0; i < grid.length; ++i) {
			grid[i] = ACCESSIBLE;
		}

		return grid;
	}

	protected boolean hasAlphaChannel(int pixel) {
		return 0x000000 != (pixel & ALPHA_MASK);
	}

	public void setAccessible(int gridIndex) {
		if(isValidIndex(gridIndex)){
			grid[gridIndex] = ACCESSIBLE;
		}
	}

	public void setWarrior(int gridIndex, int warriorIndex) {
		if(isValidIndex(gridIndex)){
			grid[gridIndex] = warriorIndex;
		}
	}

	public int getWarriorIndex(int gridIndex) {
		if(!isValidIndex(gridIndex)){
			return -1;
		}

		return grid[gridIndex];
	}
	
	public boolean isAccessible(int gridIndex) {
		return isValidIndex(gridIndex) && grid[gridIndex] == ACCESSIBLE;
	}

	public boolean isWarrior(int gridIndex) {
		return isValidIndex(gridIndex) && grid[gridIndex] != ACCESSIBLE
				&& grid[gridIndex] != INACCESSIBLE;
	}

	protected int positionToIndex(Vector position) {
		int x = (int) (position.x - this.position.x);
		int y = (int) (position.y - this.position.y);

		return x / Warrior.SIZE + y / Warrior.SIZE * gridWidth;
	}

	private boolean isValidIndex(int gridIndex) {
		return gridIndex >= 0 && gridIndex < grid.length;
	}

	public int getIndexOf(Vector step) {
		int index = positionToIndex(step);
		return isValidIndex(index) ? index : -1;
	}
	
	@Override
	public void drawOn(PApplet applet) {
		
	}
}
