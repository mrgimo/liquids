package ch.hsr.ifs.liquids.controller.listeners;

import ch.hsr.ifs.liquids.controller.EventListener;
import ch.hsr.ifs.liquids.controller.events.KeyboardEvent;

public interface KeyboardListener extends EventListener {

	public void keyPressed(KeyboardEvent event);

	public void keyReleased(KeyboardEvent event);

}
