package ch.hsr.ifs.liquids.controller;

import java.util.Iterator;

import ch.hsr.ifs.liquids.common.Event;
import ch.hsr.ifs.liquids.common.Listener;
import ch.hsr.ifs.liquids.util.list.List;

public class EventHandler {

	private static List<Listener<?>> listeners = new List<Listener<?>>();

	public static <E extends Event> void handle(E event) {
		
	}

	public static void addListener(Listener<?> listener) {
		listeners.add(listener);
	}

	public static void removeListener(Listener<?> listener) {
		Iterator<Listener<?>> iterator = listeners.iterator();
		while (iterator.hasNext()) {
			if (listener.equals(iterator.next())) {
				iterator.remove();
			}
		}
	}

}
