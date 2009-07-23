package ch.hsr.ifs.liquid.controller.listeners.input;

import ch.hsr.ifs.liquid.controller.core.events.InputEvent;
import ch.hsr.ifs.liquid.controller.core.listeners.InputListener;
import ch.hsr.ifs.liquid.model.game.Player;

public class PlayerInputListener implements InputListener {

	private static final int SPEED = 10;

	private Player player;

	private char up;
	private char down;
	private char left;
	private char right;

	public PlayerInputListener(Player player, char up, char down, char left,
			char right) {
		this.player = player;

		this.up = up;
		this.down = down;
		this.left = left;
		this.right = right;
	}

	public void inputReceived(InputEvent event) {
		char input = event.getInput();

		if (input == up) {
			player.cursor.position.y -= SPEED;
		} else if (input == down) {
			player.cursor.position.y += SPEED;
		} else if (input == left) {
			player.cursor.position.x -= SPEED;
		} else if (input == right) {
			player.cursor.position.x += SPEED;
		}
	}
}
