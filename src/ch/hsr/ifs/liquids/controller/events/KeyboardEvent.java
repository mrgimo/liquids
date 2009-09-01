package ch.hsr.ifs.liquids.controller.events;

import ch.hsr.ifs.liquids.controller.Event;
import ch.hsr.ifs.liquids.util.list.List;

public class KeyboardEvent extends Event {

	private List<Character> pressedKeys = new List<Character>();
	private List<Character> releasedKeys = new List<Character>();

	public KeyboardEvent(List<Character> pressedKeys,
			List<Character> releasedKeys) {
		if (pressedKeys != null)
			this.pressedKeys = pressedKeys;

		if (releasedKeys != null)
			this.releasedKeys = releasedKeys;
	}

	public List<Character> getPressedKeys() {
		return pressedKeys;
	}

	public List<Character> getReleasedKeys() {
		return releasedKeys;
	}

}
