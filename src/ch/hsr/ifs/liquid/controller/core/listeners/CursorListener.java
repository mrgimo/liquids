package ch.hsr.ifs.liquid.controller.core.listeners;

import ch.hsr.ifs.liquid.controller.core.events.CursorEvent;

public interface CursorListener {
	public void cursorClicked(CursorEvent event);

	public void cursorPressed(CursorEvent event);

	public void cursorReleased(CursorEvent event);

	public void cursorDragged(CursorEvent event);

	public void cursorMoved(CursorEvent event);
}
