package ch.hsr.ifs.liquids.common;

import ch.hsr.ifs.liquids.controller.EventHandler;

public abstract class Listener<E extends Event> {

	public abstract void respond(E event);

	public void listen() {
		EventHandler.addListener(this);
	}

	public static void main(String[] args) throws SecurityException, NoSuchMethodException {
		Listener<Event> listener = new Listener<Event>() {

			@Override
			public void respond(Event event) {

			}

		};
		
		System.out.println(listener.getClass().);
	}

}
