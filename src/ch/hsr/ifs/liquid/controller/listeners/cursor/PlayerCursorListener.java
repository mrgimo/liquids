package ch.hsr.ifs.liquid.controller.listeners.cursor;

import ch.hsr.ifs.liquid.controller.core.events.CursorEvent;
import ch.hsr.ifs.liquid.controller.core.listeners.CursorListener;
import ch.hsr.ifs.liquid.model.game.Player;

public class PlayerCursorListener implements CursorListener {

	private Player player;

	public PlayerCursorListener(Player player) {
		this.player = player;
	}

	public void cursorClicked(CursorEvent event) {
		// TODO Auto-generated method stub

	}

	public void cursorDragged(CursorEvent event) {
		// TODO Auto-generated method stub

	}

	public void cursorMoved(CursorEvent event) {
		player.cursor.position.x = event.getX();
		player.cursor.position.y = event.getY();
	}

	public void cursorPressed(CursorEvent event) {
		// TODO Auto-generated method stub

	}

	public void cursorReleased(CursorEvent event) {
		// TODO Auto-generated method stub

	}

}
