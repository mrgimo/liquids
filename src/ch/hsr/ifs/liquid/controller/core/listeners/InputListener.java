package ch.hsr.ifs.liquid.controller.core.listeners;

import ch.hsr.ifs.liquid.controller.core.events.InputEvent;

public interface InputListener {
	public void inputReceived(InputEvent event);
}
