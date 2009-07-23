package ch.hsr.ifs.liquid.model.game;

import ch.hsr.ifs.liquid.view.core.elements.Cursor;

public class Player {
	protected static final int ALPHA_CHANNEL = 0xff000000;
	
	private static byte NUMBER_OF_PLAYERS = 0;
	private static int colors[] = {0xffaa00bb, 0xffff0000, 0xff00ff00, 0xff00ffff, 0xff0000ff};
	private static int colorOffset = 0;

	public byte player;
	
	public Team team = new Team();
	public Cursor cursor = new Cursor();

	public Player() {
		player = NUMBER_OF_PLAYERS++;
		cursor.colour = createRandomColour();
	}

	protected int createRandomColour() {
		return colors[colorOffset++];
	}
}
