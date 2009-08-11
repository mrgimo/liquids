package ch.hsr.ifs.liquids.common;

import ch.hsr.ifs.liquids.controller.EventHandler;

public abstract class Listener<E extends Event> {

	public abstract void respond(E event);

	public void listen() {
		EventHandler.addListener(this);
	}

}
