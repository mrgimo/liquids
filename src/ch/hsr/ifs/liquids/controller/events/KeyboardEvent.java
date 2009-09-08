package ch.hsr.ifs.liquids.controller.events;

import java.awt.AWTEvent;
import java.awt.event.KeyEvent;

import ch.hsr.ifs.liquids.controller.Event;
import ch.hsr.ifs.liquids.util.list.List;

public class KeyboardEvent extends Event {

	public static KeyboardEvent createKeyboardEvent(AWTEvent event) {
		KeyEvent keyEvent = (KeyEvent) event;

		int keyCode = keyEvent.getKeyCode();
		return new KeyboardEvent(keyCode);
	}

	private List<Character> pressedKeys = new List<Character>();

	public KeyboardEvent(int changedKey) {
		if (changedKey == KeyEvent.VK_UNDEFINED)
			return;

		System.out.println(changedKey);
	}

	public List<Character> getPressedKeys() {
		return pressedKeys;
	}

}
